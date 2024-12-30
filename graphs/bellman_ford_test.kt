// bellman_ford.kt

fun BellmanFord_test_1(){
    val grafo = listOf(
        listOf(Pair(1,1),Pair(2,2)),
        listOf(Pair(0,5)),
        listOf(),
    )
    val dist = BellmanFord(grafo, 0, 2)
    val expected = listOf(
        listOf(0, Int.MAX_VALUE, Int.MAX_VALUE),
        listOf(Int.MAX_VALUE, 1, 2),
        listOf(6, Int.MAX_VALUE, Int.MAX_VALUE)
    )

    assert(dist == expected) { "Prueba 1.1 fallida\n $dist \n $expected \n" }
}

fun BellmanFord_test_2(){
    val grafo = listOf(
        listOf(Pair(1,-1)),
        listOf(Pair(0,-1)),
        listOf(),
    )

    val dist = BellmanFord(grafo, 0, 4)
    val expected = listOf(
        listOf(0, Int.MAX_VALUE, Int.MAX_VALUE),
        listOf(Int.MAX_VALUE, -1, Int.MAX_VALUE),
        listOf(-2, Int.MAX_VALUE, Int.MAX_VALUE),
        listOf(Int.MAX_VALUE,-3, Int.MAX_VALUE),
        listOf(-4, Int.MAX_VALUE, Int.MAX_VALUE)
    )
    assert(dist == expected) { "Prueba 2.1 fallida\n $dist \n $expected \n" }
}

fun main(){
    BellmanFord_test_1()
    BellmanFord_test_2()
}