class Criba(n: Int){
    var criba = MutableList(n+1){-1}
    init {
        for (i in 2..n){
            if (criba[i] == -1){
                if (n/i>=i) for (j in i*i until (n+1) step i){
                    if (criba[j] == -1) criba[j] = i
                }
            }
        }
    }

    fun fact(n: Int) : MutableMap<Int,Int> {
        var res = mutableMapOf<Int,Int>()
        var x = n
        while(criba[x] != -1){
            res[criba[x]] = res.getOrDefault(criba[x], 0) + 1
            x /= criba[x]
        }
        if(x != 1) res[x] = res.getOrDefault(x, 0) + 1
        return res
    }
}