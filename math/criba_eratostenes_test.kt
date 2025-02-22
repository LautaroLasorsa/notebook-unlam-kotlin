// criba_eratostenes.kt

fun Criba_test_1(){
    val criba = Criba(1001)
    for (i in 2..1001){
        if (criba.criba[i] == -1){
            for (j in 2 until i){
                assert(i % j != 0) { "Prueba 1.$i fallida" }
            }
        } else{
            assert(i % criba.criba[i] == 0) { "Prueba 1.$i fallida: $i ${criba.criba[i]}" }
        }
    }
}

fun Criba_test_2(){
    val criba = Criba(10000)
    for (i in 2..10000){
        val f = criba.fact(i)
        var x = i
        for ((p, e) in f){
            repeat(e) { x /= p }
        }
        assert(x == 1) { "Prueba 2.$i fallida" }
    }
}

fun Criba_test_3(){
    val start = System.currentTimeMillis()
    @Suppress("UNUSED_VARIABLE")
    val _criba = Criba(10_000_000)
    val end = System.currentTimeMillis()
    assert(end - start < 2000) { "Prueba 3 fallida TLE: ${end-start} ms" }
    println("Prueba 3 exitosa ${end-start} ms")
}

fun main(){
    Criba_test_1()
    Criba_test_2()
    Criba_test_3()
}