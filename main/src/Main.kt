import linear.Dims
import linear.Mat

fun main() {
    val m1: Mat<Dims.Dim2, Dims.Dim3> =
        Mat.fromList(Dims.Dim2, Dims.Dim3, listOf(listOf(1.0, 2.0, 3.0), listOf(4.0, 5.0, 6.0)))
    println(m1)
    val m2: Mat<Dims.Dim3, Dims.Dim1> =
        Mat.fromList(Dims.Dim3, Dims.Dim1, listOf(listOf(2.0), listOf(4.0), listOf(6.0)))
    println(m2)

    println(m1 * m2)
}