fun Kruskal(g: List<Triple<Int,Int,Int>>, n : Int) : Pair<Int, List<Int>>{
    var uf = MutableList(n){i -> i}
    fun find(x: Int) : Int{
        if (uf[x] == x) return x
        uf[x] = find(uf[x])
        return uf[x]
    }
    
    fun union(x: Int, y: Int){
        uf[find(x)] = find(y)
    }
    
    val aristas = MutableList(n){i -> i}.sortedBy{g[it].third}

    var valor = 0
    var arbol = mutableListOf<Int>()
    for (ar in aristas){
        val (u,v,c) = g[ar]
        if (find(u) != find(v)){
            union(u,v)
            valor += c
            arbol.add(ar)
        }
    }

    return Pair(valor, arbol)
}