// dinic.kt

fun Dinic_test_1(){
    val dinic = Dinic(3)
    dinic.addEdge(0, 1, 3)
    dinic.addEdge(1, 2, 2)
    dinic.addEdge(0, 2, 2)
    val max_flow = dinic.maxFlow(0, 2)
    assert(max_flow == 4L) { "Prueba 1.1 fallida $max_flow" }
}


fun main(){
    Dinic_test_1()
}