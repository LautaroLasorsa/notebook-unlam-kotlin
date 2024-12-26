fun Manacher(s: String): Pair<List<Int>, List<Int>>{ 
    // (d1, d2) = (impares, pares) palindromes
    val n = s.length
    val d1 = MutableList(n) { 0 }
    val d2 = MutableList(n) { 0 }
    var l = 0
    var r = -1
    for (i in 0 until n){
        var k = if (i > r) 1 else minOf(d1[l+r-i], r-i+1)
        while (i-k >= 0 && i+k < n && s[i-k] == s[i+k]) k++
        d1[i] = k--
        if (i+k > r){
            l = i-k
            r = i+k
        }
    }
    l = 0
    r = -1
    for (i in 0 until n){
        var k = if (i > r) 0 else minOf(d2[l+r-i+1], r-i+1)
        while (i-k-1 >= 0 && i+k < n && s[i-k-1] == s[i+k]) k++
        d2[i] = k--
        if (i+k > r){
            l = i-k-1
            r = i+k
        }
    }
    return Pair(d1, d2)
}