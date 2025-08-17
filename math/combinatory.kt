class Combinatory(n: Int, mod: Long) {
    var f: LongArray = LongArray(n + 1) { 0 }
    var inv: LongArray = LongArray(n + 1) { 0 }
    var fi: LongArray = LongArray(n + 1) { 0 }
    var mod: Long = mod

    init {  
        f[0] = 1
        fi[0] = 1
        inv[0] = 0
        for(i in 1..n){
            f[i] = i * f[i-1]%mod
            inv[i] = mod - (mod / i) * inv[mod.toLong() % i] % mod
            fi[i] = fi[i-1] * inv[i] % mod
        }
    }

    fun Comb(n: Int, k: Int): Long {
        if (k < 0 || k > n) return 0
        return f[n] * fi[k] % mod * fi[n - k] % mod
    }

    fun Perm(n: Int, k: Int): Long {
        if (k < 0 || k > n) return 0
        return f[n] * fi[n - k] % mod
    }

    fun PermRepetidos(ns : List<Int>): Long {
        var res = f[ns.sum()]
        for (n in ns) {
            res = res * fi[n] % mod
        }
        return res
    }
}