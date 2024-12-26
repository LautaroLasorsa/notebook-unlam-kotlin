// funcion_z.kt

fun z_test_1(){
    val z = z("abacaba")
    val expected = listOf(0, 0, 1, 0, 3, 0, 1)
    assert(z == expected) { "Prueba 1.1 fallida" }
    
    val z2 = z("aaaaa")
    val expected2 = listOf(0, 4, 3, 2, 1)
    assert(z2 == expected2) { "Prueba 1.2 fallida" }
}

fun z_test_2(){
    val n_casos = 1000
    val n = 100

    fun lcp(s: String, t: String) : Int{
        var i = 0
        while (i < s.length && i < t.length && s[i] == t[i]) i++
        return i
    }

    for (caso in 0 until n_casos){
        val s = (0 until n).map { ('a'..'z').random() }.joinToString("")
        val z = z(s)
        for (i in 1 until n){
            assert(z[i] == lcp(s, s.substring(i))) { "Prueba 2.$caso.$i fallida" }
        }
    }
}

fun main(){
    z_test_1()
    z_test_2()
}