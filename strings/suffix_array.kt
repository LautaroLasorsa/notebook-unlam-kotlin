fun RB(x : Int, n : Int, r: List<Int>) : Int{
    if(x < n) return r[x]
    else return 0
}

fun csort(sa: MutableList<Int>, r: MutableList<Int>, k : Int){
    val n = sa.size
    var f = MutableList(maxOf(255,n)){0}
    var t = MutableList(n){0}
    for (i in 0 until n) f[RB(i+k,n,r)]++
    var sum = 0
    for (i in 0 until maxOf(255,n)){
        var v = f[i]
        f[i] = sum
        sum += v
    }
    for (i in 0 until n){
        t[f[RB(sa[i]+k,n,r)]] = sa[i]
        f[RB(sa[i]+k,n,r)]++
    }
    
    for (i in 0 until n) sa[i] = t[i]
}


fun suffix_array(s: String): List<Int>{
    val n = s.length
    var rank = 0
    var sa = MutableList(n){it}
    var r = MutableList(n){it}
    var t = MutableList(n){0}
    var k = 1
    while (k<n){
        csort(sa,r,k)
        csort(sa,r,0)
        t[sa[0]] = 0
        for (i in 1 until n){
            if(r[sa[i]] != r[sa[i-1]] || RB(sa[i]+k,n,r) != RB(sa[i-1]+k,n,r)) rank++
            t[sa[i]] = rank
        }
        r = t
        if (r[sa[n-1]]==n-1) break
        k *= 2
    }
    return sa
}