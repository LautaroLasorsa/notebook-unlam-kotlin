fun z(s: String): List<Int>{ // z[i] = max k: s[0,k) == s[i,i+k)
    val n = s.length
    val z = MutableList(n) { 0 } 
    var l = 0
    var r = 0
    for (i in 1 until n){
        if (i <= r) z[i] = minOf(r-i+1, z[i-l])
        while (i+z[i] < n && s[z[i]] == s[i+z[i]]) z[i]++
        if (i+z[i]-1 > r){
            l = i
            r = i+z[i]-1
        }
    }
    return z
}