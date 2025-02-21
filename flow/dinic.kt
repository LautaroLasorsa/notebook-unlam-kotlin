import kotlin.math.min

class Dinic(nodes: Int) {
    var dist: MutableList<Int>
    var q: MutableList<Int>
    var work: MutableList<Int>
    var src: Int = 0
    var dst: Int = 0

    class Edge(val to: Int, var cap: Long, var rev: Int)

    var g: MutableList<MutableList<Edge>>

    init {
        dist = MutableList(nodes) { -1 }
        q = MutableList(nodes) { 0 }
        work = MutableList(nodes) { 0 }
        g = MutableList(nodes) { mutableListOf<Edge>() }
    }

    fun addEdge(from: Int, to: Int, cap: Long) {
        g[from].add(Edge(to, cap, g[to].size))
        g[to].add(Edge(from, 0L, g[from].size - 1)) // Agregar arista inversa con capacidad 0
    }

    fun dinicBFS(): Boolean {
        dist.fill(-1)
        dist[src] = 0
        var qt = 0
        var qh = 0
        q[qt++] = src
        while(qh < qt) {
            val u = q[qh]
            for (e in g[u]) {
                if (dist[e.to] < 0 && e.cap > 0) {
                    dist[e.to] = dist[u] + 1
                    q[qt++] = e.to
                }
            }
            qh++
        }
        return dist[dst] >= 0
    }

    fun dinicDFS(u: Int, f: Long): Long {
        if (u == dst) return f
        while (work[u] < g[u].size) {
            val e = g[u][work[u]++]
            if (e.cap > 0 && dist[e.to] == dist[u] + 1) {
                val df = dinicDFS(e.to, min(f, e.cap))
                if (df > 0) {
                    e.cap -= df
                    g[e.to][e.rev].cap += df
                    return df
                }
            }
        }
        return 0
    }

    fun maxFlow(src: Int, dst: Int): Long {
        this.src = src
        this.dst = dst
        var result = 0L
        while (dinicBFS()) {
            work.fill(0)
            while (true) {
                val delta = dinicDFS(src, Long.MAX_VALUE)
                if (delta == 0L) break
                result += delta
            }
        }
        return result
    }
}
