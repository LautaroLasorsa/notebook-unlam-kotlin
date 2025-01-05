import kotlin.math.*

class Segment(val f: pt, val s: pt) {
    fun length(): Double {
        val dx = f.x - s.x
        val dy = f.y - s.y
        return sqrt(dx * dx + dy * dy)
    }
}

fun pc(a: pt, b: pt, o: pt): Double = (a-o) % (b-o) 
fun pe(a: pt, b: pt, o: pt): Double = (a-o) * (b-o)

fun intersect(a: Segment, b: Segment): Boolean{
    val fb = 0.compareTo(pc(a.f, a.s, b.f))
    val sb = 0.compareTo(pc(a.f, a.s, b.s))
    val fa = 0.compareTo(pc(b.f, b.s, a.f))
    val sa = 0.compareTo(pc(b.f, b.s, a.s))
    if ((fb * sb < 0) && (fa * sa<0)) return true
    if ((fb==0 && pe(a.f, a.s, b.f)<=0) || (sb==0 && pe(a.f, a.s,b.s)<=0)) return true;
    if ((fa==0 && pe(b.f, b.s, a.f)<=0) || (sa==0 && pe(b.f, b.s, a.s)<=0)) return true;
    return false
}

fun dist(p: pt, s: Segment): Double{
    val a = abs(pc(s.f,s.s,p))
    val b = hypot(s.f.x - s.s.x, s.f.y - s.s.y)
    val h = a/b
    val c = hypot(b,h)
    val d1 = (s.f-p).norm()
    val d2 = (s.s-p).norm()
    if(b<EPS || c<= d1 || c<= d2) return minOf(d1,d2)
    return h
}

fun dist(a: Segment, b : Segment) : Double{
    if(intersect(a,b)) return 0.0
    return minOf(
        minOf(dist(a.f,b),dist(a.s,b)),
        min(dist(b.f,a),dist(b.s,a))
    )
}