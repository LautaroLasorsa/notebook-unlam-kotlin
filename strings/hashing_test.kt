// hashing.kt

fun hashing_test_1(){
    val a = Hashing('a')
    val a2 = Hashing('a')
    assert(a.h == a2.h) { "Prueba 1.1 fallida" }

    val b = Hashing('b')
    assert(a.h != b.h) { "Prueba 1.2 fallida" }

    val ab = a + b
    val ab2 = a2 + b
    assert(ab.h == ab2.h) { "Prueba 1.3 fallida" }

    val hcasa = StringHasher("casa")
    val hsa = StringHasher("sa")
    assert(hcasa.hash(2,4).h == hsa.hash(0,2).h) { "Prueba 1.4 fallida ${hcasa.hash(2,4).h} ${hsa.hash(0,2).h}" }
}

fun hashing_test_2(){
    val n_casos = 1000
    val n = 50
    for (caso in 0 until n_casos){
        val s = (0 until n).map { ('a'..'z').random() }.joinToString("")
        val h = StringHasher(s)
        for (i in 0 until n){
            for(j in i until n){
                val sub = s.substring(i,j+1)
                val h_sub = StringHasher(sub)
                assert(h_sub.hash(0, sub.length) == h.hash(i, j+1)) { "Prueba 2.$caso.$i.$j fallida" }
            }
        }
    }
}

fun hashing_test_3(){
    val n_casos = 100
    val n = 30
    for (caso in 0 until n_casos){
        val s = (0 until n).map { ('a'..'b').random() }.joinToString("")
        val h = StringHasher(s)
        for (i1 in 0 until n){
            for (j1 in i1 until n){
                val sub1 = s.substring(i1,j1+1)
                for (i2 in 0 until n){
                    for (j2 in i2 until n){
                        val sub2 = s.substring(i2,j2+1)
                        val string_eq = (sub1 == sub2)
                        val hash_eq = (h.hash(i1,j1+1) == h.hash(i2, j2+1))
                        assert(string_eq == hash_eq) { "Prueba 3.$caso.$i1.$j1.$i2.$j2 fallida: $string_eq $hash_eq"}
                    }
                }
            }
        }
    }
}

fun hashing_test_4(){
    val n_casos = 100
    for (caso in 0 until n_casos){
        val n = (1..(1 shl 20)).random()
        val c = ('a'..'z').random()
        val s = String(CharArray(n) { c })
        
        var base = Hashing(c)
        var pot = hash_neutro()
        var e = n
        while (e>0){
            if (e%2 == 1) pot = pot + base
            base = base + base
            e /= 2
        }
        val h = StringHasher(s)
        assert(h.hash(0, n) == pot) { "Prueba 4.$caso fallida" }
    }
}

fun main(){
    hashing_test_1()
    hashing_test_2()
    hashing_test_3()
}