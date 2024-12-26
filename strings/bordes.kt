fun bordes(s: String): List<Int>{
    val n = s.length
    val b = MutableList(n+1) { -1 }
    var j = -1;
    for (i in 0 until n){
        while (j >= 0 && s[i] != s[j]){
            j = b[j]
        }
        b[i+1] = j
    }
    return b
}