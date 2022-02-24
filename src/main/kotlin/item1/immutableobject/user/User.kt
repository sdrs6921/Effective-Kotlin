package item1.immutableobject.user

class User(val name: String, val surname: String) {

    fun withSurname(surname: String) = User(name, surname)
}
