package item8

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.mockk.*
import kotlin.properties.Delegates

internal class UserControllerTest : AnnotationSpec() {
    private var daoWithNotNullAssertion: UserDao? = null
    private var controllerWithNotNullAssertion: UserController? = null

    private lateinit var daoWithLateInit: UserDao
    private lateinit var controllerWithLateInit: UserController

    @Test
    fun `not null assertion dao 테스트`() {
        //given
        val expectedExecuteTimes = 1
        daoWithNotNullAssertion = mockk()
        every { daoWithNotNullAssertion!!.doSomething() } just Runs
        controllerWithNotNullAssertion = UserController(daoWithNotNullAssertion!!)


        //when
        controllerWithNotNullAssertion!!.doSomething()

        // then
        verify(exactly = expectedExecuteTimes) { daoWithNotNullAssertion!!.doSomething() }
    }

    @Test
    fun `late init dao 테스트`() {
        //given
        val expectedExecuteTimes = 1
        daoWithLateInit = mockk()
        every { daoWithLateInit.doSomething() } just Runs
        controllerWithLateInit = UserController(daoWithLateInit)

        //when
        controllerWithLateInit.doSomething()

        //then
        verify(exactly = expectedExecuteTimes) { daoWithLateInit.doSomething() }
    }

    @Test
    fun `late init 사용 전 초기화 안 할 경우 예외를 발생시킨다`() {
        //given
        lateinit var controller: UserController

        //when //then
        shouldThrow<UninitializedPropertyAccessException> {
            controller.doSomething()
        }
    }

    @Test
    fun `Delegate의 notNull을 활용하여 초기화 지연`() {
        //given
        var doctorId: Int by Delegates.notNull()
        val expected = 1
        doctorId = 1

        //when
        val actual = doctorId

        //then
        actual shouldBe expected
    }

    @Test
    fun `Delegate의 notNull을 사용할 떄, 초기화되지 않은 상태에서 접근할 경우 예외를 발생시킨다`() {
        //given
        var doctorId: Int by Delegates.notNull()

        //when //then
        shouldThrow<java.lang.IllegalStateException> {
            println(doctorId)
        }
    }
}