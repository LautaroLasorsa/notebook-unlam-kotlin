import java.util.PriorityQueue

fun Dijkstra(
    grafo: List<List<Pair<Int,Int>>>,
    inicio: Int,
) : Pair<List<Int>, List<Int>> {
    val n = grafo.size
    var dist = MutableList(n) { Int.MAX_VALUE }
    var padre = MutableList(n) { -1 }
    dist[inicio] = 0
    val pq = PriorityQueue<Pair<Int,Int>>(compareBy { -it.second })
    pq.add(Pair(inicio, 0))
    while (pq.isNotEmpty()){
        val (u, d) = pq.poll()
        if (d > dist[u]) continue
        for ((v, w) in grafo[u]){
            if (dist[u] + w < dist[v]){
                dist[v] = dist[u] + w
                padre[v] = u
                pq.add(Pair(v, dist[v]))
            }
        }
    }
    return Pair(dist, padre)
}