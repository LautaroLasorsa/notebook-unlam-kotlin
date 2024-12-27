// karatsuba.kt

fun karatsuba_test_1(){
    val n_casos = 1000
    val n = 100
    for (caso in 0 until n_casos){
        val a = (0 until n).map { (0..100).random().toLong() }
        val b = (0 until n).map { (0..100).random().toLong() }
        val c = Karatsuba(a, b)
        val expected = MutableList(2*n){0.toLong()}
        for (i in 0 until n){
            for (j in 0 until n){
                expected[i+j] += (a[i]) * (b[j])
            }
        }
        assert(c == expected) { "Prueba 1.$caso fallida" }
    }
}

fun karatsuba_test_2(){
    val n_casos = 10
    val n = 200_000
    var peor_tiempo = 0L
    for (caso in 0 until n_casos){
        val a = (0 until n).map { (0..100).random().toLong() }
        val b = (0 until n).map { (0..100).random().toLong() }
        
        val t0 = System.currentTimeMillis()
        val c = Karatsuba(a, b)
        val t1 = System.currentTimeMillis()
        assert(
            t1 - t0 < 2000
        ){ "Prueba 2.$caso fallida TLE: ${t1-t0}" }
        peor_tiempo = maxOf(peor_tiempo, t1-t0)
    }
    println("Peor tiempo: $peor_tiempo ms")
}

fun main(){
    karatsuba_test_1()
    karatsuba_test_2()
}