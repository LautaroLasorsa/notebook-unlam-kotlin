// suffix_array.kt

fun suffix_array_test_1(){
    val s = "abracadabra"
    val sa = suffix_array(s)
    val expected = listOf(11, 10, 7, 0, 3, 5, 8, 1, 4, 6, 9, 2)
    assert(sa == expected) { "Prueba 1.1 fallida" }
}

fun suffix_array_test_2(){
    val n_casos = 10000
    val n = 500
    for (caso in 0 until n_casos){
        val s = (0 until n).map { ('a'..'z').random() }.joinToString("")
        val sa = suffix_array(s)
        for (i in 0 until n-1){
            val suf1 = s.substring(sa[i])
            val suf2 = s.substring(sa[i+1])
            assert(suf1 <= suf2) { "Prueba 2.$caso.$i fallida" }
        }
    }
}

fun main(){
    suffix_array_test_1()
    suffix_array_test_2()
}