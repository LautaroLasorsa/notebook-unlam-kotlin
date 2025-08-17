// mobius.kt criba_eratostenes.kt

fun Mobius_test_1(){
    val n = 1000
    val mob = Mobius(n)
    val criba = Criba(n)
    for (i in 2..n){
        val factores = criba.fact(i)
        var expected = -1
        for ((_,v) in factores){
            if (v > 1){
                expected = 0
                break
            }
            expected *= -1
        }    

        assert(mob[i] == expected) { "Prueba 1.$i fallida\n ${mob[i]}\n$expected" }
    }
}

fun Mobius_test_2(){
    val start = System.currentTimeMillis()
    @Suppress("UNUSED_VARIABLE")
    val mob = Mobius(10_000_000)
    val end = System.currentTimeMillis()
    assert(end - start < 2000) { "Prueba 2 fallida TLE: ${end-start} ms" }
    println("Prueba 2 exitosa ${end-start} ms")
}

fun main(){
    Mobius_test_1()
    Mobius_test_2()
}