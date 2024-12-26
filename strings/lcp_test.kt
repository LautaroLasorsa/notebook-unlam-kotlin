// lcp.kt suffix_array.kt

fun lcp_test_1(){
    val s = "abacaba"
    val sa = suffix_array(s)
    val lcp = computar_lcp(s, sa)
    val expected = listOf(0, 1, 3, 0, 2, 0, 1)
    assert(lcp == expected) { "Prueba 1.1 fallida" }
}

fun lcp_test_2(){
    val n_casos = 1000
    val n = 100

    fun calcular_lcp(s: String, t: String) : Int{
        var i = 0
        while (i < s.length && i < t.length && s[i] == t[i]) i++
        return i
    }

    for (caso in 0 until n_casos){
        val s = (0 until n).map { ('a'..'z').random() }.joinToString("")
        val sa = suffix_array(s)
        val lcp = computar_lcp(s, sa)
        for (i in 1 until n){
            assert(lcp[i] == calcular_lcp(
                s.substring(sa[i]),
                s.substring(sa[i-1])
            )) { "Prueba 2.$caso.$i fallida" }
        }
    }
}

fun main(){
    lcp_test_1()
    lcp_test_2()
}