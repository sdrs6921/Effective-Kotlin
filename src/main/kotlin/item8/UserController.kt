package item8

class UserController(private val userDao: UserDao) {

    fun doSomething() {
        userDao.doSomething()
    }
}