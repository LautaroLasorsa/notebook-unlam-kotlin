// lca.kt

fun LCA_test_1(){
    val arbol = listOf(
        listOf(1, 2),
        listOf(0, 3),
        listOf(0, 4),
        listOf(1),
        listOf(2)
    )
    val lca = LCA(arbol, 0)

    assert(lca.lca(1, 2) == 0) { "Prueba 1.1 fallida" }
    assert(lca.lca(1, 3) == 1) { "Prueba 1.2 fallida" }
    assert(lca.lca(1, 4) == 0) { "Prueba 1.3 fallida" }
    assert(lca.lca(3, 4) == 0) { "Prueba 1.4 fallida" }
    assert(lca.lca(2, 4) == 2) { "Prueba 1.5 fallida" }
}

fun main(){
    LCA_test_1()
}