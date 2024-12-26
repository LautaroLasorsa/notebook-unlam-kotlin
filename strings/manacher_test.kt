// manacher.kt

fun Manacher_test_1(){
    val s = "aabbaacaabbaa"
    val (d1, d2) = Manacher(s)
    val expected_d1 = listOf(1,1,1,1,1,1,7,1,1,1,1,1,1)
    val expected_d2 = listOf(0,1,0,3,0,1,0,0,1,0,3,0,1)

    assert(d1 == expected_d1) { "Prueba 1.1 fallida" }
    assert(d2 == expected_d2) { "Prueba 1.2 fallida" }
}

fun Manacher_test_2(){
    val n_casos = 1000
    val n = 100
    for (caso in 0 until n_casos){
        val s = (0 until n).map { ('a'..'z').random() }.joinToString("")
        val (d1, d2) = Manacher(s)
        for (i in 0 until n){
            var lar = 0
            while (i-lar >= 0 && i+lar < n && s[i-lar] == s[i+lar]){
                lar++
            }
            assert(d1[i] == lar) { "Prueba 2.$caso.$i fallida" }
        }
        for (i in 0 until n-1){
            var lar = 0
            while (i-lar >= 0 && i+lar+1 < n && s[i-lar] == s[i+lar+1]){
                lar++
            }
            assert(d2[i] == lar) { "Prueba 2.$caso.${i+n} fallida" }
        }
    }
}

fun main(){
    Manacher_test_1()
    Manacher_test_2()
}