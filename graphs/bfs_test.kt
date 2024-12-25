// bfs.kt

fun bfs_test_1(){
    val graph = listOf(
        listOf(1, 2),
        listOf(0, 2, 3),
        listOf(0, 1, 4),
        listOf(1, 4),
        listOf(2, 3)
    )
    val min_dist = bfs(graph, 0)
    val expected = listOf(0, 1, 1, 2, 2)
    assert(min_dist == expected) { "Prueba 1.1 fallida" }

    val min_dist2 = bfs(graph, 3)
    val expected2 = listOf(2, 1, 2, 0, 1)
    assert(min_dist2 == expected2) { "Prueba 1.2 fallida" }
}

fun main(){
    bfs_test_1()
}
