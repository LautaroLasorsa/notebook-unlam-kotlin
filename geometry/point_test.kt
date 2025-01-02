// point.kt
import kotlin.math.*

const val EPS = 1e-8

fun point_test_1(){
    val p = pt(3.0,4.0)
    assert(abs(p.norm() - 5.0) < EPS) { "Prueba 1.1 fallida ${p.norm()}" }

    val q = pt(1.0,1.0)
    val r = pt(1.0,0.0)

    assert(abs(q.angle(r) - PI / 4) < EPS) { "Prueba 1.2 fallida ${q.angle(r)}" }

    assert(p.rot(r) == pt(-4.0,3.0)) { "Prueba 1.3 fallida ${p.rot(r)}" }

    val u = pt(0.0,0.0)

    val s = pt(0.0,1.0)
    assert(u.left(r,s)) { "Prueba 1.4 fallida ${u.left(r,s)}" }

    val t = pt(0.0,-1.0)
    assert(!u.left(r,t)) { "Prueba 1.5 fallida ${u.left(r,t)}" }

    assert(p * u == 0.0) { "Prueba 1.6 fallida ${p * u}" }

    val v = pt(1.0,1.0)
    assert(p % v == -1.0) { "Prueba 1.7 fallida ${p % v}" }

    val w = pt(1.0,1.0)
    assert(!(p == w)) { "Prueba 1.8 fallida" }
    assert(p == pt(3.0,4.0)) { "Prueba 1.9 fallida" }
}

fun main(){
    point_test_1()
}