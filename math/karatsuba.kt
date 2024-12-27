fun Karatsuba(a : List<Long>, b : List<Long>) : List<Long>{
    val m = maxOf(a.size, b.size)
    val n = 1 shl (32 - Integer.numberOfLeadingZeros(m - 1))
    val aa = a + MutableList(n - a.size) { 0.toLong() }
    val bb = b + MutableList(n - b.size) { 0.toLong() }
    return karatsuba(aa, bb)
}


fun karatsuba(a : List<Long>, b: List<Long>) : List<Long>{
    if (a.size <= 32){
        val c : MutableList<Long> = MutableList(2*a.size - 1) { 0 }
        for (i in 0 until a.size){
            for (j in 0 until b.size){
                c[i+j] += a[i]*b[j]
            }
        }
        return c
    }

    val n = a.size
    val k = n/2
    val a0 = a.subList(0, k)
    val a1 = a.subList(k, n)
    val b0 = b.subList(0, k)
    val b1 = b.subList(k, n)

    val z2 = karatsuba(a1, b1)
    val z0 = karatsuba(a0, b0)
    val inter = karatsuba(
        MutableList(k) { a0[it] + a1[it] },
        MutableList(k) { b0[it] + b1[it] }
    )
    val z1 = MutableList(n-1) { inter[it] - z0[it] - z2[it] }

    val c = MutableList(2*n-1) { 0.toLong() }
    for (i in 0 until n-1){
        c[i] += z0[i]
        c[i+k] += z1[i]
        c[i+2*k] += z2[i]
    }
    return c
}