// karatsuba.kt

fun trim_list(a : List<Long>) : List<Long>{
    var i = a.size - 1
    while (i >= 0 && a[i] == 0.toLong()){
        i--
    }
    return a.subList(0, i+1)
}

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
        val c_trim = trim_list(c)
        val expected_trim = trim_list(expected)
        assert(c_trim == expected_trim) { "Prueba 1.$caso fallida\n $c_trim\n$expected_trim" }
    }
}

fun karatsuba_test_2(){
    val n_casos = 10
    val n = 35_000
    var peor_tiempo = 0L
    for (caso in 0 until n_casos){
        val a = (0 until n).map { (0..100).random().toLong() }
        val b = (0 until n).map { (0..100).random().toLong() }
        
        val t0 = System.currentTimeMillis()
        val c = Karatsuba(a, b)
        val t1 = System.currentTimeMillis()
        val tiempo_ejecucion = t1 - t0
        println("Tiempo de ejecución para el caso $caso: $tiempo_ejecucion ms")
        assert(
            tiempo_ejecucion < 2000L
        ){ "Prueba 2.$caso fallida TLE: ${t1-t0}" }
        peor_tiempo = maxOf(peor_tiempo, tiempo_ejecucion)
    }
    println("Peor tiempo: $peor_tiempo ms")
}

fun main(){
    karatsuba_test_1()
    karatsuba_test_2()
}