class LCA(
    arbol: List<List<Int>>,
    raiz: Int
){
    var K: Int
    var padre: MutableList<MutableList<Int>>
    var prof: MutableList<Int>
    init {
        val n = arbol.size
        K = 1
        while ((1 shl K) < n) K++
        padre = MutableList(K) { MutableList(n) { -1 } }
        prof = MutableList(n) { -1 }

        fun dfs(u: Int, p: Int){
            padre[0][u] = p
            for (v in arbol[u]){
                if (v == p) continue
                prof[v] = prof[u] + 1
                dfs(v, u)
            }
        }

        dfs(raiz, -1)
        prof[raiz] = 0

        for (k in 1 until K){
            for (u in 0 until n){
                if (padre[k-1][u] != -1){
                    padre[k][u] = padre[k-1][padre[k-1][u]]
                }
            }
        }
    }
    
    
    fun lca(uu: Int, vv: Int) : Int {
        var u = uu
        var v = vv
        
        if (prof[u] < prof[v]) return lca(v, u)
        for (k in K-1 downTo 0){
            if (prof[u] - (1 shl k) >= prof[v]){
                u = padre[k][u]
            }
        }
        if (u == v) return u
        for (k in K-1 downTo 0){
            if (padre[k][u] != padre[k][v]){
                u = padre[k][u]
                v = padre[k][v]
            }
        }
        return padre[0][u]
    }
}