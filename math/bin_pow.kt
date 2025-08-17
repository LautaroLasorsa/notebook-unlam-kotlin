fun binPow(b : Long, e : Long, mod : Long): Long{
    var res = 1L
    b %= mod
    while (e > 0) {
        if (e % 2 == 1L)  res = (res * b) % mod
        b = (b * b) % mod
        exp /= 2
    }
    return res
}

fun generic_bin_pow<T>(b: T, e : Long, c : T): T{ // c * b^e
    while(e > 0){
        if (e % 2 == 1L)  c *= b
        b *= b
        e /= 2
    }
    return c
}

