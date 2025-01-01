import kotlin.math.round

data class Complex(val r: Double, val i: Double){
    operator fun plus(x: Complex) = Complex(r + x.r, i + x.i)
    operator fun minus(x: Complex) = Complex(r - x.r, i - x.i)
    operator fun times(x: Complex) = Complex(r*x.r - i*x.i, r*x.i + i*x.r)
    operator fun div(x: Double) = Complex(r/x, i/x)
}

class FFT(lg0: Int){
    val lg = lg0+1
    val n = 1 shl lg
    val w = MutableList(n+1){Complex(0.0, 0.0)}

    init {
        val ang0 = 2.0 * Math.PI / n.toDouble()
        for (i in 0 until n+1){
            val ang = ang0 * i
            w[i] = Complex(Math.cos(ang), Math.sin(ang))
        }
    }

    fun fft(a: List<Complex>, inv: Boolean = false) : List<Complex>{
        val p = MutableList(n) { a[Integer.reverse(it) ushr (32 - lg)] }

        var len = 2
        while (len <= n) {
            val step = n / len
            for (i in 0 until n step len) {
                for (j in 0 until len / 2) {
                    val u = p[i + j]
                    val v = p[i + j + len / 2] * if (inv) w[n - j * step] else w[j * step]
                    p[i + j] = u + v
                    p[i + j + len / 2] = u - v
                }
            }
            len *= 2
        }

        if (inv) {
            for (i in 0 until n) {
                p[i] = p[i] / n.toDouble()
            }
        }

        return p
    }

    fun multiply(a: List<Long>, b: List<Long>) : List<Long>{
        val a_c = a.map { Complex(it.toDouble(), 0.0) } + MutableList(n-a.size){Complex(0.0, 0.0)}
        val b_c = b.map { Complex(it.toDouble(), 0.0) } + MutableList(n-b.size){Complex(0.0, 0.0)}
        val fa = fft(a_c)
        val fb = fft(b_c)
        val fc = MutableList(n){fa[it] * fb[it]}
        val c = fft(fc, true)
        return c.map { round(it.r).toLong() }
    }
}