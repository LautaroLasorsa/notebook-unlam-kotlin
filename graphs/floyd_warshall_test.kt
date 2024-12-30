// floyd_warshall.kt bellman_ford.kt

fun FloydWarshall_test_1(){
    val matriz = listOf(
        listOf(0, 1, Int.MAX_VALUE/2),
        listOf(Int.MAX_VALUE/2, 0, 1),
        listOf(1, Int.MAX_VALUE/2, 0)
    )

    val dist = FloydWarshall(matriz)

    val expected = listOf(
        listOf(0, 1, 2),
        listOf(2, 0, 1),
        listOf(1, 2, 0)
    )

    assert(dist == expected) { "Prueba 1.1 fallida" }
}

fun FloydWarshall_test_2(){
    val n = 50
    val casos = 1000

    for (caso in 0 until casos){
        val matriz = MutableList(n) { i -> MutableList(n) { j -> if (i != j) Int.MAX_VALUE/2 else 0 } }
        val grafo = MutableList(n) { mutableListOf<Pair<Int, Int>>() }

        for (i in 0 until n){
            for (j in 0 until n){
                if ((i==j) or ((0..2).random()>0)) continue
                matriz[i][j] = (-100..100).random()
                grafo[i].add(Pair(j, matriz[i][j]))
            }
        }

        val dist = FloydWarshall(matriz)
        val bf_dist = BellmanFord(grafo, 0, 2 * n)
        for (i in 0 until n){
            if (dist[i][i] < 0){
                val min_parte_1 = (1..n).map { bf_dist[it][i] }.minOrNull() ?: Int.MAX_VALUE
                val min_parte_2 = (n+1..2*n).map { bf_dist[it][i] }.minOrNull() ?: Int.MAX_VALUE
                assert(min_parte_2 < min_parte_1) { "Prueba 2.$caso.$i fallida"}
            }
        }

    }
}

fun main(){
    FloydWarshall_test_1()
    FloydWarshall_test_2()
}