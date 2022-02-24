package item4.infererered.library

val DEFAULT_CAR = Granduer()

interface CarFactory {
    fun produce() = DEFAULT_CAR
}