package item7

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe

internal class PersonTest : AnnotationSpec() {

    @Test
    fun `이름과 나이로 Person을 초기화한다`() {
        //given
        val expectedName = "엄희상"
        val expectedAge = 10
        val actualPerson = Person(expectedName, expectedAge)

        //when
        val actualName = actualPerson.name
        val actualAge = actualPerson.age

        //then
        assertSoftly {
            actualName shouldBe expectedName
            actualAge shouldBe expectedAge
        }
    }
}