// dijkstra.kt

fun Dijkstra_test_1(){
    val graph = listOf(
        listOf(Pair(1, 1), Pair(2, 2)),
        listOf(Pair(0, 1), Pair(2, 1), Pair(3, 1)),
        listOf(Pair(0, 2), Pair(1, 1), Pair(4, 1)),
        listOf(Pair(1, 1), Pair(4, 1)),
        listOf(Pair(2, 1), Pair(3, 1))
    )
    val (min_dist, padre) = Dijkstra(graph, 0)
    val expected = listOf(0, 1, 2, 2, 3)
    assert(min_dist == expected) { "Prueba 1.1 fallida" }
}

fun Dijkstra_test_2(){
    val n = 500
    val m = 10000
    val inf = Int.MAX_VALUE/2;
    val matriz = MutableList(n) { MutableList(n) { inf } }
    val grafo = MutableList(n) { mutableListOf<Pair<Int, Int>>() }

    for (e_i in 0 until m){
        val u = (0 until n).random()
        val v = (0 until n).random()
        val w = (0 until 10000).random()
        matriz[u][v] = minOf(matriz[u][v], w)
        grafo[u].add(Pair(v, w))
    }

    for (k in 0 until n){
        for (i in 0 until n){
            for (j in 0 until n){
                matriz[i][j] = minOf(matriz[i][j], matriz[i][k] + matriz[k][j])
            }
        }
    }

    for (u in 0 until n){
        val (min_dist, padre) = Dijkstra(grafo, u)
        for (v in 0 until n){
            assert(min_dist[v] == matriz[u][v]) { "Prueba 2.$u.$v fallida" }
            if (padre[v] != -1){
                var x = v
                while (x != u){
                    val p = padre[x]
                    assert(matriz[u][v] == matriz[u][p] + matriz[p][v]) { "Prueba 2.$u.$v.$p fallida" }
                    x = p
                }
            }
        }
    }
}

fun main(){
    Dijkstra_test_1()
    Dijkstra_test_2()
}