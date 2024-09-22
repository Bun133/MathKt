package linear

class Mat<M : Dims, N : Dims>(val values: DimValues<DimValues<Double, N>, M>) {
    companion object {
        fun <M : Dims, N : Dims> fromList(m: M, n: N, list: List<List<Double>>): Mat<M, N> {
            require(list.size == m.i)
            return gen(m, n) { M: Int, N: Int -> list[M][N] }
        }

        fun <M : Dims, N : Dims> zero(m: M, n: N): Mat<M, N> {
            return Mat(DimValues.fill(m, DimValues.fill(n, 0.0)))
        }

        fun <M : Dims, N : Dims> gen(m: M, n: N, f: (m: Int, n: Int) -> Double): Mat<M, N> {
            val e = zero(m, n)
            for (N in 0 until n.i) {
                for (M in 0 until m.i) {
                    e[M, N] = f(M, N)
                }
            }
            return e
        }
    }

    val m = values.dim
    val n = values[0]!!.dim

    operator fun plus(other: Mat<M, N>): Mat<M, N> {
        return gen(m, n) { M: Int, N: Int -> this[M, N]!! + other[M, N]!! }
    }

    operator fun minus(other: Mat<M, N>): Mat<M, N> {
        return gen(m, n) { M: Int, N: Int -> this[M, N]!! - other[M, N]!! }
    }

    operator fun <R : Dims> times(other: Mat<N, R>): Mat<M, R> {
        return gen(m, other.n) { M, R ->
            val row = this.getRow(M)
            val column = other.getColumn(R)

            assert(row.dim.i == column.dim.i)

            var sum = 0.0
            for (i in 0 until row.dim.i) {
                sum += row[i]!! * column[i]!!
            }

            sum
        }
    }

    operator fun set(m: Int, n: Int, v: Double) {
        require(0 <= m && m < this.m.i)
        val e = values[m]!!
        require(0 <= n && n < this.n.i)
        e[n] = v
    }

    operator fun get(m: Int, n: Int): Double? {
        require(0 <= m && m < this.m.i)
        val e = values[m]!!
        require(0 <= n && n < this.n.i)
        return e[n]
    }

    fun getRow(m: Int): DimValues<Double, N> {
        require(0 <= m && m < this.m.i)
        return values[m]!!.clone()
    }

    fun getColumn(n: Int): DimValues<Double, M> {
        val e = DimValues.fill(this.m, 0.0)
        for (i in 0 until this.m.i) {
            e[i] = this[i, n]!!
        }
        return e
    }

    override fun toString(): String {
        return "[${values.map { v -> v.toString() }.joinToString("\n")}]"
    }
}