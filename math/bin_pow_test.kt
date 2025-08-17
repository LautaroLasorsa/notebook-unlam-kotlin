// bin_pow.kt

fun test_1(){
    assert(binPow(2, 10, 1000) == 24){ "Prueba 1 fallida" }
    assert(generic_bin_pow(2, 10, 1) == 1024){ "Prueba 1 fallida" }

    val mod = 1_000_000_007L
    for(i in 1..100){
        assert(binPow(i.toLong(), 0, mod) == 1L) { "Prueba 1.$i.a fallida" }
        assert(binPow(i.toLong(), mod, mod) == i.toLong()) { "Prueba 1.$i.b fallida" }
    }
}

fun test_2(){
    class ModMatrix(val rows: Int, val cols: Int, val mod: Long) {
        private val data = Array(rows) { LongArray(cols) { 0L } }

        operator fun times(other: ModMatrix): ModMatrix {
            if (cols != other.rows) throw IllegalArgumentException("Incompatible matrix sizes")
            if (mod != other.mod) throw IllegalArgumentException("Incompatible matrix mods")
            val result = ModMatrix(rows, other.cols, mod)
            for (i in 0 until rows) {
                for (j in 0 until other.cols) {
                    for (k in 0 until cols) {
                        result.data[i][j] = (result.data[i][j] + data[i][k] * other.data[k][j]) % mod
                    }
                }
            }
            return result
        }

        operator fun get(i: Int, j: Int): Long = data[i][j]
        operator fun set(i: Int, j: Int, value: Long) {
            data[i][j] = value % mod
        }
        operator fun add(other: ModMatrix): ModMatrix {
            if (rows != other.rows || cols != other.cols) throw IllegalArgumentException("Incompatible matrix sizes")
            if (mod != other.mod) throw IllegalArgumentException("Incompatible matrix mods")
            val result = ModMatrix(rows, cols, mod)
            for (i in 0 until rows) {
                for (j in 0 until cols) {
                    result.data[i][j] = (data[i][j] + other.data[i][j]) % mod
                }
            }
            return result
        }
    }

    var a = 0
    var b = 1
    val mod = 1_000_000_007L
    
    for(n in 0..10000){
        var m = ModMatrix(2, 2, mod)
        m[0,0] = 0
        m[0,1] = 1
        m[1,0] = 1
        m[1,1] = 1
        var i = ModMatrix(2, 2, mod)
        i[0,0] = 1
        i[0,1] = 0
        i[1,0] = 0
        i[1,1] = 1

        val mm = generic_bin_pow(m, n,i)
        assert(a == mm[0,1]){ "Prueba 2.$n fallida" }
        val c = (a+b) % mod
        a = b
        b = c
    }
}

fun main(){
    test_1()
    test_2()
}