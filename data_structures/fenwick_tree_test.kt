// fenwick_tree.kt

fun fenwick_tree_test_1(){
    val n = 10
    val ft = Fenwick(n, Int::plus, 0)
    val arr = (0 until n).map { it + 1 }
    for (i in 0 until n) {
        ft.update(i + 1, arr[i])
    }
    val expected = arr.sum()
    val result = ft.query(n)
    assert(result == expected) { "Prueba 1 fallida: $result != $expected" }
}

fun fenwick_tree_test_2(){
    val n_casos = 1000
    val n = 1000
    val q = 5000
    
    for (caso in 0 until n_casos){
        val arr = MutableList(n) { 0 }
        val ft = Fenwick(n, Int::plus, 0)
        for (i in 0 until q){
            if ((0..2).random() == 0){
                val l = (0 until n).random()
                val r = (l until n).random()
                val expected = arr.slice(l..r).sum()
                val result = ft.query(r + 1) - ft.query(l)
                assert(result == expected) { "Prueba 2.$caso.$i fallida: $result != $expected" }
            }else{
                val index = (0 until n).random()
                val value = (0 until 100).random()
                arr[index] += value
                ft.update(index + 1, value)
            }
        }
    }
}

fun fenwick_tree_test_3(){
    val n_casos = 10
    val n = 1 shl 20
    val q = 1 shl 20
    val time_limit = 2000L

    for (caso in 0 until n_casos){
        val start = System.currentTimeMillis()
        var ft = Fenwick(n, Int::plus, 0)
        for (i in 0 until q){
            if ((0..2).random() == 0){
                val l = (0 until n).random()
                val r = (l until n).random()
                @Suppress("UNUSED_VARIABLE")
                val _result = ft.query(r + 1) - ft.query(l)
            }else{
                val index = (0 until n).random()
                val value = (0 until 100).random()
                ft.update(index + 1, value)
            }
        }
        val end = System.currentTimeMillis()
        assert(end - start <= time_limit) { "Prueba 3.$caso fallida ${end-start} ms" }
        println("Prueba 3.$caso exitosa ${end-start} ms")
    }

}

fun main(){
    fenwick_tree_test_1()
    fenwick_tree_test_2()
    fenwick_tree_test_3()
}