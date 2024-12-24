fun bfs(
    graph: List<List<Int>>,
    start: Int
) : List<Int> {
    val n = graph.size
    val min_dist = MutableList(n) { -1 }
    val q = mutableListOf<Int>()
    q.add(start)
    min_dist[start] = 0
    var qi = 0
    while (qi < q.size){
        val u = q[qi]
        qi++
        for (v in graph[u]){
            if (min_dist[v] == -1){
                min_dist[v] = min_dist[u] + 1
                q.add(v)
            }
        }
    }
    return min_dist
}