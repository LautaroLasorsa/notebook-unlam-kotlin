fun Karatsuba(a : List<Long>, b : List<Long>) : List<Long>{
    val m = maxOf(a.size, b.size)
    val n = 1 shl (32 - Integer.numberOfLeadingZeros(m - 1))
    val aa = a + MutableList(n - a.size) { 0.toLong() }
    val bb = b + MutableList(n - b.size) { 0.toLong() }
    return karatsuba(aa, bb)
}


fun karatsuba(a: List<Long>, b: List<Long>): List<Long> {
    if (a.size <= 16) { // Reducir el tamaño de la condición base
        val c = MutableList(2 * a.size - 1) { 0L }
        for (i in a.indices) {
            for (j in b.indices) {
                c[i + j] += a[i] * b[j]
            }
        }
        return c
    }

    val n = a.size
    val k = n / 2
    val a0 = a.subList(0, k)
    val a1 = a.subList(k, n)
    val b0 = b.subList(0, k)
    val b1 = b.subList(k, n)

    val z2 = karatsuba(a1, b1)
    val z0 = karatsuba(a0, b0)
    val a0a1 = List(k) { a0[it] + a1[it] }
    val b0b1 = List(k) { b0[it] + b1[it] }
    val z1 = karatsuba(a0a1, b0b1)

    val result = MutableList(2 * n) { 0L }
    for (i in z0.indices) result[i] += z0[i]
    for (i in z2.indices) result[i + n] += z2[i]
    for (i in z1.indices) result[i + k] += z1[i] - z0.getOrElse(i) { 0L } - z2.getOrElse(i) { 0L }

    return result
}