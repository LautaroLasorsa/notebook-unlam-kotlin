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