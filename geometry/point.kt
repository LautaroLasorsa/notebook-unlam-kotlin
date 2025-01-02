import kotlin.math.*

class pt(x: Double, y: Double){
    val x = x
    val y = y
    operator fun plus(p: pt) = pt(x + p.x, y + p.y)
    operator fun minus(p: pt) = pt(x - p.x, y - p.y)
    operator fun times(k: Double) = pt(x * k, y * k)
    operator fun div(k: Double) = pt(x / k, y / k)
    operator fun times(p: pt) = x * p.x + y * p.y
    operator fun rem(p: pt) = x * p.y - y * p.x
    fun angle(p: pt) = acos((this * p) / (this.norm() * p.norm()))
    fun norm2() = x * x + y * y
    fun norm() = sqrt(norm2())
    fun unit() = this / norm()
    fun rot(r: pt) = pt(this % r, this * r)
    fun rot(a: Double) = this.rot(pt(cos(a), sin(a)))
    fun left(p: pt, q: pt) = (q - p).unit() % (this - p).unit() > EPS

    operator fun  compareTo(p: pt) = when {
        this.x != p.x -> this.x.compareTo(p.x)
        else -> this.y.compareTo(p.y)
    }

    override fun equals(other: Any?) = other is pt && x == other.x && y == other.y
    override fun toString() = "($x, $y)"
}

val ccw90 = pt(1.0,0.0)
val cw90 = pt(-1.0,0.0)