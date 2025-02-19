// convex_hull.kt point.kt

val EPS = 1e-8

fun pc_unit(a: pt, b: pt, o: pt): Double = (a-o).unit() % (b-o).unit()

fun chull_test_1(){
    val ps = listOf(pt(0.0,0.0), pt(1.0,0.0), pt(0.0,1.0), pt(1.0,1.0), pt(0.5,0.5))
    val ch = chull(ps)
    val expected = listOf(pt(0.0,0.0), pt(1.0,0.0), pt(1.0,1.0), pt(0.0,1.0))
    assert(ch == expected) { "Prueba 1.1 fallida:\n$ch\n$expected" }
}

fun chull_test_2(){
    val n_casos = 10
    val n = 2_000_000
    val time_limit = 2000L
    for (caso in 0 until n_casos){
        val ps = (0 until n).map { pt((0 until 1000000000).random().toDouble(), 
            (0 until 1000000000).random().toDouble()) }
        val start = System.currentTimeMillis()
        val ch = chull(ps)
        val end = System.currentTimeMillis()
        assert(end - start <= time_limit) { "Prueba 2.$caso fallida TLE ${end-start} ms" }

        for(i in 0 until ch.size){
            val a = ch[i]
            val b = ch[(i+1)%ch.size]
            val c = ch[(i+2)%ch.size]
            assert(pc_unit(b,c,a) > -EPS) { "Prueba 2.$caso fallida: El resultado no es un poligono convexo ($a $b $c ${pc_unit(b,c,a)})" }
        }
        println("Prueba 2.$caso exitosa ${end-start} ms")
    } 
}

fun chull_test_3(){
    val n_casos = 1000
    val n = 2_000
    val time_limit = 2000L
    for (caso in 0 until n_casos){
        val ps = (0 until n).map { pt((0 until 1000000000).random().toDouble(), 
            (0 until 1000000000).random().toDouble()) }
        val start = System.currentTimeMillis()
        val ch = chull(ps)
        val end = System.currentTimeMillis()
        assert(end - start <= time_limit) { "Prueba 3.$caso fallida TLE ${end-start} ms" }

        for(i in 0 until ch.size){
            val a = ch[i]
            val b = ch[(i+1)%ch.size]
            val c = ch[(i+2)%ch.size]
            assert(pc_unit(b,c,a) > -EPS) { "Prueba 3.$caso fallida: El resultado no es un poligono convexo" }
        }

        for (p in ps){
            for (i in 0 until ch.size){
                val a = ch[i]
                val b = ch[(i+1)%ch.size]
                assert(pc_unit(a,b,p) > - EPS) { "Prueba 3.$caso fallida: El punto $p no esta dentro de la convex hull: $a $b ${pc_unit(a,b,p)}" }
            }
        }
    } 
}


fun main(){
    chull_test_1()
    chull_test_2()
    chull_test_3()
}