// bordes.kt

fun bordes_test_1(){
    val s = "abacaba"
    val b = bordes(s)
    val expected = listOf(-1, 0, 0, 1, 0, 1, 2, 3)
    assert(b == expected) { "Prueba 1.1 fallida" }
}

fun bordes_test_2(){
    val n_casos = 1000
    val n = 100

    fun get_borde(s: String) : Int{
        for (lar in s.length-1 downTo 0){
            var ok = true
            for (i in 0 until lar){
                if (s[i] != s[s.length-lar+i]){
                    ok = false
                    break
                }
            }
            if (ok) return lar
        }
        return 0
    }

    for (caso in 0 until n_casos){
        val s = (0 until n).map { ('a'..'z').random() }.joinToString("")
        val b = bordes(s)
        for (i in 1 until n+1){
            assert(b[i] == get_borde(s.substring(0, i))) { "Prueba 2.$caso.$i fallida" }
        }
    }
}

fun main(){
    bordes_test_1()
    bordes_test_2()
}