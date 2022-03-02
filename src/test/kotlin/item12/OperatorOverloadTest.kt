package item12

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe

internal class OperatorOverloadTest : AnnotationSpec() {
    fun Int.factorial(): Int = (1..this).product()
    fun Iterable<Int>.product(): Int = fold(1) { acc, i ->
        acc * i
    }

    @Test
    fun `팩토리얼 확장 함수 테스트`() {
        //given
        val expected = 720

        //when
        val actual = 6.factorial()

        //then
        actual shouldBe expected
    }

    @Test
    fun `팩토리얼 연산자 ! 오버로딩 테스트`() {
        //given
        operator fun Int.not() = factorial()
        val expected = 720

        //when
        val actual = !6

        //then
        actual shouldBe expected
    }

    @Test
    fun `곱하기 연산자 반복하여 생성하는 확장 함수`() {
        //given
        operator fun Int.times(operation: () -> Unit): () -> Unit = {
            repeat(this) { operation() }
        }

        //when
        val tripledHello = 3 * { println("Hello") }


        //then
        tripledHello()
    }

    @Test
    fun `곱하기 연산자를 반복하여 호출하는 확장 함수`() {
        //given
        operator fun Int.times(operation: () -> Unit) {
            repeat(this) { operation() }
        }

        //when //then
        3 * { println() }
    }

    @Test
    fun `곱하기 연산자를 infix를 활용한 확장 함수`() {
        //given
        infix fun Int.timesRepeated(operation: () -> Unit): () -> Unit = {
            repeat(this) { operation() }
        }

        //when
        val tripledHello = 3 timesRepeated { println("Hello") }

        //then
        tripledHello()
    }
}