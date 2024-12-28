fun computar_lcp(s0: String, sa: List<Int>): MutableList<Int>{
    val s = s0 + '\u0000'
    val n = s.length
    var L = 0
    var lcp = MutableList(n){0}
    var plcp = MutableList(n){0}
    var phi = MutableList(n){0}
    
    phi[sa[0]] = -1
    for (i in 1 until n) phi[sa[i]] = sa[i-1]
    for (i in 0 until n){
        if (phi[i] == -1){
            plcp[i] = 0
            continue
        }
        while (s[i+L] == s[phi[i]+L]) L++
        plcp[i] = L
        L = maxOf(L-1,0)
    }
    for (i in 0 until n) lcp[i] = plcp[sa[i]]
    return lcp
}