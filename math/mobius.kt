fun Mobius(n: Int): List<Int>{
    var mobius = MutableList(n+1){1}
    mobius[0] = 0
    for(i in 2..n){
        if(mobius[i]!=0){
            for(j in (i+i)..n step i){
                mobius[j] -= mobius[i];
            }
        }
    }
    return mobius
}