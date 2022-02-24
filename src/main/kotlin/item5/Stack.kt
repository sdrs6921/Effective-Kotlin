package item5

class Stack<E> {

    private var elements: List<E> = mutableListOf()
    private var size: Int = 0
    private var isOpen: Boolean = false

    fun add(e: E) {
        elements = elements + e
        size++
    }

    fun size(): Int {
        return size
    }

    fun open() {
        isOpen = true
    }

    fun pop(num: Int = 1): List<E> {
        require(num <= size) {
            "현재 스택의 크기보다 더 많은 크기를 pop 할 수 없습니다"
        }

        check(isOpen) {
            "닫힌 스택에서 pop 할 수 없습니다"
        }

        val ret = elements.take(num)
        assert(ret.size == num)
        return ret
    }
}