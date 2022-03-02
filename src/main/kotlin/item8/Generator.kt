package item8

class Generator {

    fun generateString(nullable: Boolean): String? {
        if (nullable) {
            return null
        }

        return "String"
    }

    fun generateCollection(nullable: Boolean): Collection<Int>? {
        if (nullable) {
            return null
        }

        return listOf(1, 2, 3)
    }
}