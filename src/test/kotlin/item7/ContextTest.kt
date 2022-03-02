package item7

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.beInstanceOf

internal class ContextTest : AnnotationSpec() {

    @Test
    fun `incorrectSign이 참이면 null을 반환한다`() {
        //given
        val name = "엄희상"
        val age = 10
        val person = Person(name, age)
        val incorrectSign = true
        val context = Context(person, incorrectSign)

        //when
        val actual = context.getOrNull()

        //then
        actual shouldBe null
    }

    @Test
    fun `incorrectSign이 거짓이면 null이 아닌 값을 반환한다`() {
        //given
        val name = "엄희상"
        val age = 10
        val person = Person(name, age)
        val incorrectSign = false
        val context = Context(person, incorrectSign)

        //when
        val actual = context.getOrNull()

        //then
        actual shouldNotBe null
    }

    @Test
    fun `incorrectSign이 참이면 Failure을 반환한다`() {
        //given
        val name = "엄희상"
        val age = 10
        val person = Person(name, age)
        val incorrectSign = true
        val context = Context(person, incorrectSign)

        //when
        val actual = context.get()

        //then
        actual should beInstanceOf<Failure>()
    }

    @Test
    fun `incorrectSign이 거짓이면 Success를 반환한다`() {
        //given
        val name = "엄희상"
        val age = 10
        val person = Person(name, age)
        val incorrectSign = false
        val context = Context(person, incorrectSign)

        //when
        val actual = context.get()

        //then
        actual should beInstanceOf<Success<Person>>()
    }

    @Test
    fun `getOrNull 안전 호출 예제`() {
        //given
        val name = "엄희상"
        val age = 10
        val person = Person(name, age)
        val incorrectSign = true
        val context = Context(person, incorrectSign)

        //when
        val actual = context.getOrNull()
        val expected = 10
        val actualAge = if (actual == null) {
            10
        } else {
            0
        }

        //then
        actualAge shouldBe expected
    }

    @Test
    fun `getOrNull elvis 연산자 예제`() {
        //given
        val name = "엄희상"
        val age = 10
        val person = Person(name, age)
        val incorrectSign = true
        val context = Context(person, incorrectSign)

        //when
        val expected = -1
        val actual = context.getOrNull()?.age ?: expected

        //then
        actual shouldBe expected
    }

    @Test
    fun `when 표현식으로 Success 처리 예제`() {
        //given
        val name = "엄희상"
        val age = 10
        val person = Person(name, age)
        val incorrectSign = true
        val context = Context(person, incorrectSign)

        //when
        val actual = context.get()
        val expectedAge = -1
        val actualAge = when (actual) {
            is Success -> actual.result.age
            is Failure -> expectedAge
        }

        //then
        actualAge shouldBe expectedAge
    }

    @Test
    fun `when 표현식으로 Fail 처리 예제`() {
        //given
        val name = "엄희상"
        val expected = 10
        val person = Person(name, expected)
        val incorrectSign = false
        val context = Context(person, incorrectSign)

        //when
        val actual = context.get()
        val actualAge = when (actual) {
            is Success -> actual.result.age
            is Failure -> -1
        }

        //then
        actualAge shouldBe expected
    }
}