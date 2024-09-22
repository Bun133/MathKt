package linear

class Mat<M : Dims, N : Dims>(val values: DimValues<DimValues<Double, M>, N>) {
    companion object {
        fun <M : Dims, N : Dims> fromList(m: M, n: N, list: List<List<Double>>): Mat<M, N> {
            require(list.size == m.i)
            return gen(m, n) { M: Int, N: Int -> list[N][M] }
        }

        fun <M : Dims, N : Dims> zero(m: M, n: N): Mat<M, N> {
            return Mat(DimValues.fill(n, DimValues.fill(m, 0.0)))
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

    val n = values.dim
    val m = values[0]!!.dim

    operator fun plus(other: Mat<M, N>): Mat<M, N> {
        return gen(m, n) { M: Int, N: Int -> this[M, N]!! + other[M, N]!! }
    }

    operator fun minus(other: Mat<M, N>): Mat<M, N> {
        return gen(m, n) { M: Int, N: Int -> this[M, N]!! - other[M, N]!! }
    }

    operator fun set(m: Int, n: Int, v: Double) {
        require(0 <= n && n < values.size)
        val e = values[n]!!
        require(0 <= m && m < e.size)
        e[m] = v
    }

    operator fun get(m: Int, n: Int): Double? {
        require(0 <= n && n < values.size)
        val e = values[n]!!
        require(0 <= m && m < e.size)
        return e[m]
    }
}