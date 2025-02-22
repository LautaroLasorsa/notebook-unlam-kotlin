// segment_tree.kt

fun segment_tree_test_1() {
    val st = SegmentTree(
        5,
        ::minOf,
        Int.MAX_VALUE
        ) // Creamos el segmento de tamaño 5
    val data = List<Int>(5) { it + 1 } // Creamos una lista con los valores [1, 2, 3, 4, 5]
    st.init(data) // Inicializamos el árbol con los datos

    // Prueba 1: Verificamos que el mínimo de 0 a 4 es 1
    val result1 = st.query(0, 5)
    println("Prueba 1.1: Mínimo entre [0, 4]: $result1")
    assert(result1 == 1) { "Prueba 1 fallida" }

    // Prueba 2: Verificamos que el mínimo de 1 a 3 es 2
    val result2 = st.query(1, 4)
    println("Prueba 1.2: Mínimo entre [1, 3]: $result2")
    assert(result2 == 2) { "Prueba 2 fallida" }

    // Prueba 3: Actualizamos el valor de la posición 2 a 0 y verificamos el mínimo de 0 a 4
    st.update(2, 0)
    val result3 = st.query(0, 5)
    println("Prueba 1.3: Mínimo entre [0, 4] después de actualización: $result3")
    assert(result3 == 0) { "Prueba 3 fallida" }
}

fun segment_tree_test_2(){
    val largo:Int = 1 shl 20

    val st = SegmentTree<Int>(
        largo,
        Int::plus,
        0
    )

    val data = List<Int>(1 shl 20){(-100..100).random()}
    st.init(data)

    for (q_i in 0 until 1000){
        val l = (0 until ((largo)-2000)).random()
        val r = (l+1 until (l+1200)).random()
        val result = st.query(l, r)
        val expected = data.slice(l until r).sum()
        assert(result == expected) { "Prueba 2.$q_i fallida" }
    }
}


fun segment_tree_test_3(){
    val n_casos = 10
    val n = 1 shl 20
    val q = 1 shl 20
    val time_limit = 2000L

    for (caso in 0 until n_casos){
        val start = System.currentTimeMillis()
        var st = SegmentTree(n, Int::plus, 0)
        for (i in 0 until q){
            if ((0..2).random() == 0){
                val l = (0 until n).random()
                val r = (l until n).random()
                @Suppress("UNUSED_VARIABLE")
                val _result = st.query(l, r)
            }else{
                val index = (0 until n).random()
                val value = (0 until 100).random()
                st.update(index + 1, value)
            }
        }
        val end = System.currentTimeMillis()
        assert(end - start <= time_limit) { "Prueba 3.$caso fallida ${end-start} ms" }
        println("Prueba 3.$caso exitosa ${end-start} ms")
    }
    
}

fun main() {
    segment_tree_test_1()
    segment_tree_test_2()
    segment_tree_test_3()
}
