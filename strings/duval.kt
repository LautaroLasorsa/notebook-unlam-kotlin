// Dada una string $s$ devuelve la Lyndon decomposition en tiempo
// lineal usando el algoritmo de Duval. Factoriza $s$ como
// $s_1 s_2 \ldots s_k$ con $s_1 \geqq s_2 \geqq \cdots \geqq s_k$
// y tal que $s_i$ es Lyndon, esto es, es su menor rotación.
fun Duval(s: String) : List<String>{
    val n = s.length
    var i = 0
    val ans = mutableListOf<String>()
    while (i < n){
        var j = i + 1
        var k = i
        while (j < n && s[k] <= s[j]){
            if (s[k] < s[j]) k = i
            else k++
            j++
        }
        while (i <= k){
            ans.add(s.substring(i until i+j-k))
            i += j-k
        }
    }
    return ans
}
// Obtener la mínima rotaciónn de $s$: en la descomposición de
// Lyndon de $s^2$ es el último $i<|s|$ con el que empieza una
// Lyndon.