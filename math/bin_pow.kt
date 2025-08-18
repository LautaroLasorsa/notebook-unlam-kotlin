fun binPow(b : Long, e : Long, mod : Long): Long{
    var res = 1L
    var b = b%mod
    var e = e 
    while (e > 0) {
        if (e % 2 == 1L)  res = (res * b) % mod
        b = (b * b) % mod
        e /= 2
    }
    return res
}

interface Ring<T> {
    operator fun T.times(other: T): T
    val one: T
}

fun<T> generic_bin_pow(b: T, e : Long, ops : Ring<T>): T{ // c * b^e
    var b = b
    var e = e
    var c = ops.one
    with(ops){
        while(e > 0){
            if (e % 2 == 1L)  c *= b
            b *= b
            e /= 2
        }
    }
    return c
}

