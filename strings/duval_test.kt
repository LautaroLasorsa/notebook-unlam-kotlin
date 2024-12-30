// duval.kt

fun Duval_test_1(){
    val s = "abacaba"
    val ans = Duval(s)
    val expected = listOf("abac","ab", "a")
    assert(ans == expected) { "Prueba 1.1 fallida:\n$ans\n$expected" }
}

fun Duval_test_2(){
    val n_casos = 1000
    val n = 100
    for (caso in 0 until n_casos){
        val s = (0 until n).map { ('a'..'z').random() }.joinToString("")
        val ans = Duval(s)
        var i = 0
        while (i < ans.size-1){
            assert(ans[i] >= ans[i+1]) { "Prueba 2.$caso.$i fallida" }
            i++
        }

        for (sk in ans){
            for (i in 0 until sk.length){
                assert(sk <= sk.substring(i) + sk.substring(0, i)) { "Prueba 2.$caso.$i fallida" }
            }
        }
    }
}

fun main(){
    Duval_test_1()
    Duval_test_2()
}