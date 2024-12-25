fun BellmanFord(
    grafo: List<List<Pair<Int,Int>>>,
    inicio: Int,
    largo: Int
) : List<List<Int>> {
    val n = grafo.size
    var dist = MutableList(largo+1) { MutableList(n) { Int.MAX_VALUE } }
    dist[0][inicio] = 0
    for (k in 0 until largo-1){
        for (u in 0 until n){
            for ((v, w) in grafo[u]){
                dist[k+1][v] = minOf(dist[k+1][v], dist[k][u] + w)
            }
        }
    }
    return dist
}

