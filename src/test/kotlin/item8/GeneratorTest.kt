package item8

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.throwable.shouldHaveMessage

internal class GeneratorTest : AnnotationSpec() {

    private val generator = Generator()

    @Test
    fun `null 안전 호출 및 스마트 캐스팅 처리 테스트`() {
        //given
        val actual = generator.generateString(true)

        //when
        val expectedLength = -1
        val actualLength = if (actual != null) {
            actual.length
        } else expectedLength

        //then
        actualLength shouldBe expectedLength
    }

    @Test
    fun `null elvis 연산자 처리 테스트`() {
        //given
        val actual = generator.generateString(true)

        //when
        val expectedLength = -1
        val actualLength = actual?.length ?: expectedLength

        //then
        actualLength shouldBe expectedLength
    }

    @Test
    fun `null elvis 연산자 처리 return 테스트`() {
        //given
        val actual = generator.generateString(true)

        //when //then
        shouldNotThrow<Exception> {
            actual?.length ?: return
        }
    }

    @Test
    fun `null 예외 발생 처리 테스트`() {
        //given
        val actual = generator.generateString(true)

        //when //then
        shouldThrow<IllegalArgumentException> {
            actual?.length ?: throw IllegalArgumentException("null 포인트 exception 발생")
        } shouldHaveMessage "null 포인트 exception 발생"
    }

    @Test
    fun `Collection null 처리 테스트`() {
        //given
        val actual = generator.generateCollection(true)

        //when
        val actualCollection = actual.orEmpty()

        //then
        actualCollection shouldNotBe null
    }

    @Test
    fun `코틀린 규약 기능을 활용한 일반 스마트 캐스팅`() {
        //given
        val actual = generator.generateString(false)

        //when
        val expectedLength = "string".length
        val actualLength = if (!actual.isNullOrBlank()) {
            actual.length
        } else 0

        //then
        actualLength shouldBe expectedLength
    }

    @Test
    fun `코틀린 규약 기능을 활용한 컬렉션 스마트 캐스팅`() {
        //given
        val actual = generator.generateCollection(false)

        //when
        val expectedSize = 3
        val actualSize = if (!actual.isNullOrEmpty()) {
            actual.size
        } else 0

        //then
        actualSize shouldBe expectedSize
    }

    @Test
    fun `require을 활용한 null 처리`() {
        //given
        val actual = generator.generateString(true)

        //when //then
        shouldThrow<IllegalArgumentException> {
            requireNotNull(actual)
        }
    }

    @Test
    fun `check를 활용한 null 처리`() {
        //given
        val actual = generator.generateString(true)

        //when //then
        shouldThrow<IllegalStateException> {
            checkNotNull(actual)
        }
    }

    @Test
    fun `not null assertion 활용하기`() {
        //given
        val actual = generator.generateString(false)!!

        //when
        val expectedLength = "string".length
        val actualLength = actual.length

        //then
        actualLength shouldBe expectedLength
    }
}