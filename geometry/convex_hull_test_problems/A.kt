// ../convex_hull.kt ../point.kt
// CODEFORCES 166 B
// https://codeforces.com/problemset/problem/166/B

import java.util.Scanner
import kotlin.math.*

val EPS = 1e-19

class pt(x: Double, y: Double): Comparable<pt>{
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
    fun unit() = if (norm() > 0) this / norm() else pt(0.0, 0.0)
    fun rot(r: pt) = pt(this % r, this * r)
    fun rot(a: Double) = this.rot(pt(cos(a), sin(a)))
    fun left(p: pt, q: pt) = (q - p).unit() % (this - p).unit() > EPS

    operator override fun compareTo(p: pt): Int = when {
        abs(this.x - p.x) > EPS -> this.x.compareTo(p.x)
        else -> this.y.compareTo(p.y)
    }

    override fun equals(other: Any?) = other is pt && abs(x - other.x)<EPS && abs(y - other.y)<EPS
    override fun toString() = "($x, $y)"
}

val ccw90 = pt(1.0,0.0)
val cw90 = pt(-1.0,0.0)

fun chull(ps: List<pt>) : List<pt>{
    if(ps.size < 3) return ps
    val p = ps.sorted()
    val ch = mutableListOf<pt>()
    for(pi in p){
        while(ch.size > 1 && ch[ch.size - 1].left(ch[ch.size - 2], pi)) ch.removeAt(ch.size - 1)
        ch.add(pi)
    }
    ch.removeAt(ch.size - 1)
    val t = ch.size
    for(pi in p.reversed()){
        while(ch.size > t+1 && ch[ch.size - 1].left(ch[ch.size - 2], pi)) ch.removeAt(ch.size - 1)
        ch.add(pi)
    }
    ch.removeAt(ch.size - 1)
    return ch
}

fun main(){
    val scanner = Scanner(System.`in`)
    val n = scanner.nextInt()
    val A = List(n){ pt(scanner.nextInt().toDouble(), scanner.nextInt().toDouble()) }
    val m = scanner.nextInt()
    val B = List(m){ pt(scanner.nextInt().toDouble(), scanner.nextInt().toDouble()) }
    val convex_hull = chull(A + B)
    
    if (convex_hull.toSet().intersect(B.toSet()).isEmpty()) println("YES")
    else{
        println("NO")
    // println(convex_hull)
    // println(A)
    }
}