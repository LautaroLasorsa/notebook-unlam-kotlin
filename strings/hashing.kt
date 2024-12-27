const val P: Long = 1777771
val MOD: List<Long> = listOf(999727999, 1070777777)
val PI: List<Long> = listOf(325255434, 10018302)
    
class Hashing(c: Char) {
    val h: MutableList<Long>
    val p: MutableList<Long>
    val pi: MutableList<Long>
    init {
        h = MutableList(PI.size) { i -> c.code * P % MOD[i]}
        p = MutableList(PI.size) { P } 
        pi = PI.toMutableList()
    }

    // Agrega un prefijo : H(s1) + H(s2) = H(s2s1)
    operator fun plus(h2: Hashing) : Hashing {
        val ans = Hashing('a')
        for (i in 0 until PI.size){
            ans.h[i] = (h[i] * h2.p[i] + h2.h[i]) % MOD[i]
            ans.p[i] = p[i] * h2.p[i] % MOD[i]
            ans.pi[i] = pi[i] * h2.pi[i] % MOD[i] 
        }
        return ans
    }

    // Elimina un prefijo
    operator fun minus(h2: Hashing) : Hashing {
        val ans = Hashing('a')
        for (i in 0 until PI.size){
            ans.h[i] = (h[i] - h2.h[i] + MOD[i]) % MOD[i] * h2.pi[i] % MOD[i]
            ans.p[i] = p[i] * h2.pi[i] % MOD[i]
            ans.pi[i] = pi[i] * h2.p[i] % MOD[i]
        }
        return ans
    }
}

fun hash_neutro() : Hashing{
    var ans = Hashing('a')
    for (i in 0 until PI.size){
        ans.h[i] = 0
        ans.p[i] = 1
        ans.pi[i] = 1
    }
    return ans
}

class StringHasher(s: String){
    val h: MutableList<Hashing>
    init {
        h = MutableList(s.length+1) {
            if (it == 0) hash_neutro()
            else Hashing(s[it-1])
        }
        for (i in 1 until s.length+1){
            h[i] = h[i] + h[i-1]
        }
    }

    // Hash de s[l,r)
    fun hash(l: Int, r: Int) : Hashing {
        return h[r] - h[l]
    }
}