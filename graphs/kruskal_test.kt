// kruskal.kt

fun Kruskal_test_1(){
    val g = listOf(
        Triple(0,1,1),
        Triple(1,2,2),
        Triple(2,3,3),
        Triple(3,0,4),
        Triple(0,2,5),
        Triple(1,3,6)
    )
    val n = 4
    val (valor, aristas) = Kruskal(g, n)
    val expected = 6
    val expected_aristas = listOf(0,1,2)
    assert(valor == expected) { "Prueba 1.1 fallida:\n$valor\n$expected" }
    assert(aristas == expected_aristas) { "Prueba 1.2 fallida:\n$aristas\n$expected_aristas" }
}

fun Kruskal_test_2(){
    val n_casos = 10
    val n = 200_000
    val time_limit = 2000L
    val m = 5 * n 
    
    for (caso in 0 until n_casos){
        val g = (0 until m).map {
            Triple((0 until n).random(), (0 until n).random(), (0 until 1000).random())
        }
        val start = System.currentTimeMillis()
        @Suppress("UNUSED_VARIABLE")
        val _result = Kruskal(g, n)
        val end = System.currentTimeMillis()
        assert(end - start <= time_limit) { "Prueba 2.$caso fallida ${end-start} ms" }
        println("Prueba 2.$caso exitosa ${end-start} ms")
    } 
}

fun main(){
    Kruskal_test_1()
    Kruskal_test_2()
}