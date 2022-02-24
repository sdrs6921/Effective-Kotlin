package item3

interface NullRepository {
    fun getNull() = NullGenerator().value
}