// convex_hull.kt point.kt
// CODEFORCES 166 B
// https://codeforces.com/problemset/problem/166/B

import java.util.Scanner

fun main(){
    val scanner = Scanner(System.`in`)

    val n = scanner.nextInt()
    val A = Array(n){ pt(scanner.nextInt(), scanner.nextInt()) }
    val m = scanner.nextInt()
    val B = Array(m){ pt(scanner.nextInt(), scanner.nextInt()) }
    val chull = convex_hull(A + B)
    if (chull == A) println("YES")
    else println("NO")
}
