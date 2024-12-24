class SegmentTree<T>(
    private val n: Int, 
    private val operation: (T, T) -> T, 
    private val neutral: T
) {
    private var size: Int
    private var st: MutableList<T>

    init {
        size = 1
        while (size <= n) size *= 2
        st = MutableList(2 * size) { neutral }
    }

    // Inicializa el segmento del árbol con los valores dados
    fun init(arr: List<T>) {
        for (i in 0 until n) {
            st[i + size] = arr[i]
        }
        for (i in size - 1 downTo 1) {
            st[i] = operation(st[2 * i], st[2 * i + 1])
        }
    }

    // Actualiza un valor en la posición p
    fun update(p: Int, value: T) {
        var index = p + size
        st[index] = value
        while (index > 1) {
            index /= 2
            st[index] = operation(st[2 * index], st[2 * index + 1])
        }
    }

    // Realiza una consulta sobre el rango [lq, rq)
    fun query(lq: Int, rq: Int): T {
        var left = lq + size
        var right = rq + size
        var lres = neutral
        var rres = neutral

        while (left < right) {
            if (left % 2 == 1) {
                lres = operation(lres, st[left])
                left++
            }
            if (right % 2 == 1) {
                right--
                rres = operation(st[right], rres)
            }
            left /= 2
            right /= 2
        }
        return operation(lres, rres)
    }
}