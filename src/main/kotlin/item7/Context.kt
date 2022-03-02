package item7

sealed class Result<out T>
class Success<T>(val result: T) : Result<T>()
class Failure(val throwable: Throwable) : Result<Nothing>()
class JsonParsingException : Exception()

class Context<T>(private val context: T, private val incorrectSign: Boolean) {

    fun getOrNull(): T? {
        if (incorrectSign) {
            return null
        }

        return context
    }

    fun get(): Result<T> {
        if (incorrectSign) {
            return Failure(JsonParsingException())
        }

        return Success(context)
    }
}