package item5

class Person(name: String?) {
    private var name = requireNotNull(name)

    fun getName(): String {
        return name
    }

    fun changeName(name: String?) {
        requireNotNull(name)
        this.name = name
    }

    fun changeNameWithElvis(name: String?) {
        this.name = name ?: run {
            println("print log.....")
            throw IllegalArgumentException()
        }
    }
}