fun computar_lcp(s: String, sa: List<Int>): List<Int>{
    val n = s.length
    val b = MutableList(n+1) { -1 }
    var j = -1;
    for (i in 0 until n){
        while (j >= 0 && s[i] != s[j]) j = b[j]
        b[i+1] = j
    }
    var lcp = MutableList(n){0}
    j = 0
    for (i in 0 until n){
        while (j >= 0 && s[i] != s[sa[j]]) j = b[j]
        lcp[j] = i
        j++
    }
    return lcp
}