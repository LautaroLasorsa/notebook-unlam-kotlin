// manacher.kt

fun Manacher_test_1(){
    val s = "aabbaacaabbaa"
    val (d1, d2) = Manacher(s)
    val expected_d1 = listOf(1,1,1,1,1,1,7,1,1,1,1,1,1)
    val expected_d2 = listOf(0,1,0,3,0,1,0,0,1,0,3,0,1)

    assert(d1 == expected_d1) { "Prueba 1.1 fallida\n$d1\n$expected_d1" }
    assert(d2 == expected_d2) { "Prueba 1.2 fallida\n$d2\n$expected_d2" }
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
            assert(d1[i] == lar) { "Prueba 2.$caso.$i fallida: ${d1[i]} $lar" }
        }
        for (i in 0 until n-1){
            var lar = 0
            while (i-lar-1 >= 0 && i+lar < n && s[i-lar-1] == s[i+lar]){
                lar++
            }
            assert(d2[i] == lar) { "Prueba 2.$caso.${i+n} fallida: ${d2[i]} $lar" }
        }
    }
}

fun main(){
    Manacher_test_1()
    Manacher_test_2()
}