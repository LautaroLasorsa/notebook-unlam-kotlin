class Fenwick<T>(    
    val n: Int, 
    val operation: (T, T) -> T, 
    val neutral: T){
    var ft: MutableList<T>
 
    init {
        ft = MutableList(n + 1) { neutral }
    }

    fun update(p: Int, value: T) {
        var index = p
        while (index <= n) {
            ft[index] = operation(ft[index], value)
            index += index and -index
        }
    }

    fun query(r: Int): T {
        var index = r
        var res = neutral
        while (index > 0) {
            res = operation(res, ft[index])
            index -= index and -index
        }
        return res
    }
}