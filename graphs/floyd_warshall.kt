fun FloydWarshall(
    matriz: List<List<Int>>,
) : MutableList<MutableList<Int>> {
    val n = matriz.size
    var dist = matriz.map{ it.toMutableList() }.toMutableList()
    for (k in 0 until n){
        for (i in 0 until n){
            for (j in 0 until n){
                dist[i][j] = minOf(dist[i][j], dist[i][k] + dist[k][j])
            }
        }
    }
    return dist
} 