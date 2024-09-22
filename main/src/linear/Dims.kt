package linear

/**
 * Sealed class that holds classes that express single dimension each.
 */
sealed class Dims(var i: Int) {


    data object Dim1 : Dims(1)
    data object Dim2 : Dims(2)
    data object Dim3 : Dims(3)
    data object Dim4 : Dims(4)
    data object Dim5 : Dims(5)
    data object Dim6 : Dims(6)
    data object Dim7 : Dims(7)
    data object Dim8 : Dims(8)
    data object Dim9 : Dims(9)
    data object Dim10 : Dims(10)
    data object Dim11 : Dims(11)
    data object Dim12 : Dims(12)
    data object Dim13 : Dims(13)
    data object Dim14 : Dims(14)
    data object Dim15 : Dims(15)

    // And more...? Really?
}

private class DimValuesIterator<V : Any, D : Dims>(val v: DimValues<V, D>) : Iterator<V> {
    private var index = 0

    override fun hasNext(): Boolean {
        return 0 <= index && index < v.dim.i
    }

    override fun next(): V {
        return v[index++]!!
    }
}

/**
 * Sealed class that holds exact amount of values that is specified by dimension.
 */
sealed class DimValues<V : Any, D : Dims>(var dim: D) : Iterable<V> {
    companion object {
        fun <V : Any, D : Dims> fill(d: D, v: V): DimValues<V, D> {
            return when (d) {
                is Dims.Dim1 -> Dim1V(tryClone(v)) as DimValues<V, D>
                is Dims.Dim2 -> Dim2V(tryClone(v), tryClone(v)) as DimValues<V, D>
                is Dims.Dim3 -> Dim3V(tryClone(v), tryClone(v), tryClone(v)) as DimValues<V, D>
                is Dims.Dim4 -> Dim4V(tryClone(v), tryClone(v), tryClone(v), tryClone(v)) as DimValues<V, D>
                is Dims.Dim5 -> Dim5V(tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v)) as DimValues<V, D>
                is Dims.Dim6 -> Dim6V(tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v)) as DimValues<V, D>
                is Dims.Dim7 -> Dim7V(tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v)) as DimValues<V, D>
                is Dims.Dim8 -> Dim8V(tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v)) as DimValues<V, D>
                is Dims.Dim9 -> Dim9V(tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v)) as DimValues<V, D>
                is Dims.Dim10 -> Dim10V(tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v)) as DimValues<V, D>
                is Dims.Dim11 -> Dim11V(tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v)) as DimValues<V, D>
                is Dims.Dim12 -> Dim12V(tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v)) as DimValues<V, D>
                is Dims.Dim13 -> Dim13V(tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v)) as DimValues<V, D>
                is Dims.Dim14 -> Dim14V(tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v)) as DimValues<V, D>
                is Dims.Dim15 -> Dim15V(tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v), tryClone(v)) as DimValues<V, D>
                else -> throw IllegalArgumentException("Unsupported dimension: $d")
            }
        }

        private fun <V : Any> tryClone(v: V): V {
            return when {
                v is DimValues<*, *> -> v.clone() as V
                else -> v
            }
        }
    }

    abstract operator fun get(index: Int): V?
    abstract operator fun set(index: Int, v: V)
    override fun iterator(): Iterator<V> {
        return DimValuesIterator(this)
    }

    val size = dim.i
    abstract fun clone(): DimValues<V, D>

    data class Dim1V<V : Any>(var v1: V) : DimValues<V, Dims.Dim1>(Dims.Dim1) {
        override operator fun get(index: Int): V? = if (index == 0) v1 else null
        override operator fun set(index: Int, v: V) {
            if (index == 0) v1 = v
        }

        override fun clone() = copy()
    }

    data class Dim2V<V : Any>(var v1: V, var v2: V) : DimValues<V, Dims.Dim2>(Dims.Dim2) {
        override operator fun get(index: Int): V? = when (index) {
            0 -> v1
            1 -> v2
            else -> null
        }

        override operator fun set(index: Int, v: V) {
            when (index) {
                0 -> v1 = v
                1 -> v2 = v
            }
        }

        override fun clone() = copy()
    }

    data class Dim3V<V : Any>(var v1: V, var v2: V, var v3: V) : DimValues<V, Dims.Dim3>(Dims.Dim3) {
        override operator fun get(index: Int): V? = when (index) {
            0 -> v1
            1 -> v2
            2 -> v3
            else -> null
        }

        override operator fun set(index: Int, v: V) {
            when (index) {
                0 -> v1 = v
                1 -> v2 = v
                2 -> v3 = v
            }
        }

        override fun clone() = copy()
    }

    data class Dim4V<V : Any>(var v1: V, var v2: V, var v3: V, var v4: V) : DimValues<V, Dims.Dim4>(Dims.Dim4) {
        override operator fun get(index: Int): V? = when (index) {
            0 -> v1
            1 -> v2
            2 -> v3
            3 -> v4
            else -> null
        }

        override operator fun set(index: Int, v: V) {
            when (index) {
                0 -> v1 = v
                1 -> v2 = v
                2 -> v3 = v
                3 -> v4 = v
            }
        }

        override fun clone() = copy()
    }

    data class Dim5V<V : Any>(var v1: V, var v2: V, var v3: V, var v4: V, var v5: V) :
        DimValues<V, Dims.Dim5>(Dims.Dim5) {
        override operator fun get(index: Int): V? = when (index) {
            0 -> v1
            1 -> v2
            2 -> v3
            3 -> v4
            4 -> v5
            else -> null
        }

        override operator fun set(index: Int, v: V) {
            when (index) {
                0 -> v1 = v
                1 -> v2 = v
                2 -> v3 = v
                3 -> v4 = v
                4 -> v5 = v
            }
        }

        override fun clone() = copy()
    }

    data class Dim6V<V : Any>(var v1: V, var v2: V, var v3: V, var v4: V, var v5: V, var v6: V) :
        DimValues<V, Dims.Dim6>(Dims.Dim6) {
        override operator fun get(index: Int): V? = when (index) {
            0 -> v1
            1 -> v2
            2 -> v3
            3 -> v4
            4 -> v5
            5 -> v6
            else -> null
        }

        override operator fun set(index: Int, v: V) {
            when (index) {
                0 -> v1 = v
                1 -> v2 = v
                2 -> v3 = v
                3 -> v4 = v
                4 -> v5 = v
                5 -> v6 = v
            }
        }

        override fun clone() = copy()
    }

    data class Dim7V<V : Any>(var v1: V, var v2: V, var v3: V, var v4: V, var v5: V, var v6: V, var v7: V) :
        DimValues<V, Dims.Dim7>(Dims.Dim7) {
        override operator fun get(index: Int): V? = when (index) {
            0 -> v1
            1 -> v2
            2 -> v3
            3 -> v4
            4 -> v5
            5 -> v6
            6 -> v7
            else -> null
        }

        override operator fun set(index: Int, v: V) {
            when (index) {
                0 -> v1 = v
                1 -> v2 = v
                2 -> v3 = v
                3 -> v4 = v
                4 -> v5 = v
                5 -> v6 = v
                6 -> v7 = v
            }
        }

        override fun clone() = copy()
    }

    data class Dim8V<V : Any>(var v1: V, var v2: V, var v3: V, var v4: V, var v5: V, var v6: V, var v7: V, var v8: V) :
        DimValues<V, Dims.Dim8>(Dims.Dim8) {
        override operator fun get(index: Int): V? = when (index) {
            0 -> v1
            1 -> v2
            2 -> v3
            3 -> v4
            4 -> v5
            5 -> v6
            6 -> v7
            7 -> v8
            else -> null
        }

        override operator fun set(index: Int, v: V) {
            when (index) {
                0 -> v1 = v
                1 -> v2 = v
                2 -> v3 = v
                3 -> v4 = v
                4 -> v5 = v
                5 -> v6 = v
                6 -> v7 = v
                7 -> v8 = v
            }
        }

        override fun clone() = copy()
    }

    data class Dim9V<V : Any>(
        var v1: V,
        var v2: V,
        var v3: V,
        var v4: V,
        var v5: V,
        var v6: V,
        var v7: V,
        var v8: V,
        var v9: V
    ) : DimValues<V, Dims.Dim9>(Dims.Dim9) {
        override operator fun get(index: Int): V? = when (index) {
            0 -> v1
            1 -> v2
            2 -> v3
            3 -> v4
            4 -> v5
            5 -> v6
            6 -> v7
            7 -> v8
            8 -> v9
            else -> null
        }

        override operator fun set(index: Int, v: V) {
            when (index) {
                0 -> v1 = v
                1 -> v2 = v
                2 -> v3 = v
                3 -> v4 = v
                4 -> v5 = v
                5 -> v6 = v
                6 -> v7 = v
                7 -> v8 = v
                8 -> v9 = v
            }
        }

        override fun clone() = copy()
    }

    data class Dim10V<V : Any>(
        var v1: V,
        var v2: V,
        var v3: V,
        var v4: V,
        var v5: V,
        var v6: V,
        var v7: V,
        var v8: V,
        var v9: V,
        var v10: V
    ) : DimValues<V, Dims.Dim10>(Dims.Dim10) {
        override operator fun get(index: Int): V? = when (index) {
            0 -> v1
            1 -> v2
            2 -> v3
            3 -> v4
            4 -> v5
            5 -> v6
            6 -> v7
            7 -> v8
            8 -> v9
            9 -> v10
            else -> null
        }

        override operator fun set(index: Int, v: V) {
            when (index) {
                0 -> v1 = v
                1 -> v2 = v
                2 -> v3 = v
                3 -> v4 = v
                4 -> v5 = v
                5 -> v6 = v
                6 -> v7 = v
                7 -> v8 = v
                8 -> v9 = v
                9 -> v10 = v
            }
        }

        override fun clone() = copy()
    }

    data class Dim11V<V : Any>(
        var v1: V,
        var v2: V,
        var v3: V,
        var v4: V,
        var v5: V,
        var v6: V,
        var v7: V,
        var v8: V,
        var v9: V,
        var v10: V,
        var v11: V
    ) : DimValues<V, Dims.Dim11>(Dims.Dim11) {
        override operator fun get(index: Int): V? = when (index) {
            0 -> v1
            1 -> v2
            2 -> v3
            3 -> v4
            4 -> v5
            5 -> v6
            6 -> v7
            7 -> v8
            8 -> v9
            9 -> v10
            10 -> v11
            else -> null
        }

        override operator fun set(index: Int, v: V) {
            when (index) {
                0 -> v1 = v
                1 -> v2 = v
                2 -> v3 = v
                3 -> v4 = v
                4 -> v5 = v
                5 -> v6 = v
                6 -> v7 = v
                7 -> v8 = v
                8 -> v9 = v
                9 -> v10 = v
                10 -> v11 = v
            }
        }

        override fun clone() = copy()
    }

    data class Dim12V<V : Any>(
        var v1: V,
        var v2: V,
        var v3: V,
        var v4: V,
        var v5: V,
        var v6: V,
        var v7: V,
        var v8: V,
        var v9: V,
        var v10: V,
        var v11: V,
        var v12: V
    ) : DimValues<V, Dims.Dim12>(Dims.Dim12) {
        override operator fun get(index: Int): V? = when (index) {
            0 -> v1
            1 -> v2
            2 -> v3
            3 -> v4
            4 -> v5
            5 -> v6
            6 -> v7
            7 -> v8
            8 -> v9
            9 -> v10
            10 -> v11
            11 -> v12
            else -> null
        }

        override operator fun set(index: Int, v: V) {
            when (index) {
                0 -> v1 = v
                1 -> v2 = v
                2 -> v3 = v
                3 -> v4 = v
                4 -> v5 = v
                5 -> v6 = v
                6 -> v7 = v
                7 -> v8 = v
                8 -> v9 = v
                9 -> v10 = v
                10 -> v11 = v
                11 -> v12 = v
            }
        }

        override fun clone() = copy()
    }

    data class Dim13V<V : Any>(
        var v1: V,
        var v2: V,
        var v3: V,
        var v4: V,
        var v5: V,
        var v6: V,
        var v7: V,
        var v8: V,
        var v9: V,
        var v10: V,
        var v11: V,
        var v12: V,
        var v13: V
    ) : DimValues<V, Dims.Dim13>(Dims.Dim13) {
        override operator fun get(index: Int): V? = when (index) {
            0 -> v1
            1 -> v2
            2 -> v3
            3 -> v4
            4 -> v5
            5 -> v6
            6 -> v7
            7 -> v8
            8 -> v9
            9 -> v10
            10 -> v11
            11 -> v12
            12 -> v13
            else -> null
        }

        override operator fun set(index: Int, v: V) {
            when (index) {
                0 -> v1 = v
                1 -> v2 = v
                2 -> v3 = v
                3 -> v4 = v
                4 -> v5 = v
                5 -> v6 = v
                6 -> v7 = v
                7 -> v8 = v
                8 -> v9 = v
                9 -> v10 = v
                10 -> v11 = v
                11 -> v12 = v
                12 -> v13 = v
            }
        }

        override fun clone() = copy()
    }

    data class Dim14V<V : Any>(
        var v1: V,
        var v2: V,
        var v3: V,
        var v4: V,
        var v5: V,
        var v6: V,
        var v7: V,
        var v8: V,
        var v9: V,
        var v10: V,
        var v11: V,
        var v12: V,
        var v13: V,
        var v14: V
    ) : DimValues<V, Dims.Dim14>(Dims.Dim14) {
        override operator fun get(index: Int): V? = when (index) {
            0 -> v1
            1 -> v2
            2 -> v3
            3 -> v4
            4 -> v5
            5 -> v6
            6 -> v7
            7 -> v8
            8 -> v9
            9 -> v10
            10 -> v11
            11 -> v12
            12 -> v13
            13 -> v14
            else -> null
        }

        override operator fun set(index: Int, v: V) {
            when (index) {
                0 -> v1 = v
                1 -> v2 = v
                2 -> v3 = v
                3 -> v4 = v
                4 -> v5 = v
                5 -> v6 = v
                6 -> v7 = v
                7 -> v8 = v
                8 -> v9 = v
                9 -> v10 = v
                10 -> v11 = v
                11 -> v12 = v
                12 -> v13 = v
                13 -> v14 = v
            }
        }

        override fun clone() = copy()
    }

    data class Dim15V<V : Any>(
        var v1: V,
        var v2: V,
        var v3: V,
        var v4: V,
        var v5: V,
        var v6: V,
        var v7: V,
        var v8: V,
        var v9: V,
        var v10: V,
        var v11: V,
        var v12: V,
        var v13: V,
        var v14: V,
        var v15: V
    ) : DimValues<V, Dims.Dim15>(Dims.Dim15) {
        override operator fun get(index: Int): V? = when (index) {
            0 -> v1
            1 -> v2
            2 -> v3
            3 -> v4
            4 -> v5
            5 -> v6
            6 -> v7
            7 -> v8
            8 -> v9
            9 -> v10
            10 -> v11
            11 -> v12
            12 -> v13
            13 -> v14
            14 -> v15
            else -> null
        }

        override operator fun set(index: Int, v: V) {
            when (index) {
                0 -> v1 = v
                1 -> v2 = v
                2 -> v3 = v
                3 -> v4 = v
                4 -> v5 = v
                5 -> v6 = v
                6 -> v7 = v
                7 -> v8 = v
                8 -> v9 = v
                9 -> v10 = v
                10 -> v11 = v
                11 -> v12 = v
                12 -> v13 = v
                13 -> v14 = v
                14 -> v15 = v
            }
        }

        override fun clone() = copy()
    }
}