// fft.kt

fun trim(a : List<Long>) : List<Long>{
    var i = a.size - 1
    while (i >= 0 && a[i] == 0.toLong()){
        i--
    }
    return a.subList(0, i+1)
}

fun fft_test_1(){
    val a = listOf(1.toLong(), 2, 3, 4)
    val b = listOf(5.toLong(), 6, 7, 8)
    val mult = FFT(2)
    val c = trim(mult.multiply(a, b))
    val expected = listOf(5.toLong(), 16, 34, 60, 61, 52, 32)


    assert(c == expected) { "Prueba 1 fallida\n $c\n$expected" }
}

fun fft_test_2(){
    val n_casos = 100
    val n = 1000
    val mult = FFT(10)
    for (caso in 0 until n_casos){
        val a = (0 until n).map { (0..10000).random().toLong() }
        val b = (0 until n).map { (0..10000).random().toLong() }
        val c = mult.multiply(a, b)
        val expected = MutableList(2*n){0.toLong()}
        for (i in 0 until n){
            for (j in 0 until n){
                expected[i+j] += (a[i]) * (b[j])
            }
        }

        val c_trim = trim(c)
        val expected_trim = trim(expected)
        
        assert(c_trim == expected_trim) { "Prueba 2.$caso fallida\n$c_trim\n$expected_trim" }
    }
}

fun fft_test_3(){
    val n_casos = 10
    val n = 200_000
    var peor_tiempo = 0L
    val time_limit = 2000L

    for (caso in 0 until n_casos){
        val a = (0 until n).map { (0..10000).random().toLong() }
        val b = (0 until n).map { (0..10000).random().toLong() }
        
        val t0 = System.currentTimeMillis()
        val mult = FFT(18)
        val c = mult.multiply(a, b)
        val t1 = System.currentTimeMillis()
        val tiempo_ejecucion = t1 - t0
        println("Tiempo de ejecución para el caso $caso: $tiempo_ejecucion ms")
        assert(
            tiempo_ejecucion < time_limit
        ){ "Prueba 3.$caso fallida TLE: ${t1-t0}" }
        peor_tiempo = maxOf(peor_tiempo, tiempo_ejecucion)
    }
    println("Peor tiempo: $peor_tiempo ms")
}

fun main(){
    fft_test_1()
    fft_test_2()
    fft_test_3()
}