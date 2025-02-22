import kotlin.math.min
import java.util.PriorityQueue


typealias Cost = Long
typealias Flow = Long

var INFFLOW = Int.MAX_VALUE.toLong()
var INFCOST = Int.MAX_VALUE.toLong()

class MCF(n: Int){
    var n = n
    var prio : MutableList<Cost>
    var pot : MutableList<Cost>
    var curflow : MutableList<Flow>
    var prevedge : MutableList<Int>
    var prevnode : MutableList<Int>

    class Edge(val to: Int, var cap: Flow, var flow: Flow, var cost: Cost, var rev: Int)
    var g: MutableList<MutableList<Edge>>
    var q: PriorityQueue<Pair<Cost, Int>> = PriorityQueue(){a,b -> a.first.compareTo(b.first)}

    init {
        prio = MutableList(n) { 0L }
        pot = MutableList(n) { 0L }
        curflow = MutableList(n) { 0L }
        prevedge = MutableList(n) { 0 }
        prevnode = MutableList(n) { 0 }
        g = MutableList(n) { mutableListOf<Edge>() }
    }

    fun addEdge(from: Int, to: Int, cap: Flow, cost: Cost) {
        g[from].add(Edge(to, cap, 0, cost, g[to].size))
        g[to].add(Edge(from, 0, 0, -cost, g[from].size - 1))
    }

    fun minCostFlow(s: Int, t: Int) : Pair<Flow,Cost>{
        var flow: Flow = 0
        var flowcost: Cost = 0

        while(true){
            q.add(Pair(0L,s))
            prio.fill(INFCOST)
            prio[s] = 0
            curflow[s] = INFFLOW
            while( ! q.isEmpty() ){
                val (d, u) = q.poll()
                if(d != prio[u]) continue
                for(i in 0 until g[u].size){
                    val e = g[u][i]
                    val v = e.to
                    if(e.cap <= e.flow) continue
                    val nprio = prio[u] + e.cost + pot[u] - pot[v]
                    if(prio[v] > nprio){
                        prio[v] = nprio
                        q.add(Pair(nprio, v))
                        prevnode[v] = u
                        prevedge[v] = i
                        curflow[v] = min(curflow[u], e.cap - e.flow)
                    }
                }
            }
            if(prio[t] == INFCOST) break
            for(i in 0 until n) pot[i] += prio[i]
            var df = min(curflow[t], INFFLOW - flow)
            flow += df
            var v = t
            while(v != s){
                val e = g[prevnode[v]][prevedge[v]]
                g[prevnode[v]][prevedge[v]].flow += df
                g[v][e.rev].flow -= df
                flowcost += df * e.cost
                v = prevnode[v]
            }
        }

        return Pair(flow, flowcost)
    }
}