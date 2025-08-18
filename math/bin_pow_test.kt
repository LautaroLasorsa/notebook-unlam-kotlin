// bin_pow.kt

fun test_1(){
    val LongRing = object : Ring<Long> {
        override fun Long.times(other: Long): Long = this * other
        override val one: Long = 1L
    }
    
    assert(binPow(2L, 10L, 1000L) == 24L){ "Prueba 1 fallida" }
    assert(generic_bin_pow(2L, 10L, LongRing) == 1024L){ "Prueba 1 fallida" }

    val mod = 1_000_000_007L
    for(i in 1..100){
        assert(binPow(i.toLong(), 0L, mod) == 1L) { "Prueba 1.$i.a fallida" }
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
    }

    var a = 0L
    var b = 1L
    val mod = 1_000_000_007L

    val mat_one = ModMatrix(2, 2, mod)
    mat_one[0, 0] = 1
    mat_one[0, 1] = 0
    mat_one[1, 0] = 0
    mat_one[1, 1] = 1
    val MatrixRing = object : Ring<ModMatrix> {
        override fun ModMatrix.times(other: ModMatrix): ModMatrix = this * other
        override val one: ModMatrix = mat_one
    }


    for(n in 0..10000){
        var m = ModMatrix(2, 2, mod)
        m[0,0] = 0
        m[0,1] = 1
        m[1,0] = 1
        m[1,1] = 1
        
        val mm = generic_bin_pow(m, n.toLong(), MatrixRing)
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