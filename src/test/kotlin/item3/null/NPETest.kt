package item3.`null`

import item3.NullGenerator
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class NPETest {

    @Test
    @DisplayName("Java의 null을 받아 NPE를 발생시킨다 - statedType")
    fun throw_npe_with_stated_type() {
        //given
        val nullGenerator = NullGenerator()


        //when //then
        assertThatThrownBy { val actual: String = nullGenerator.value }
            .isInstanceOf(NullPointerException::class.java)
    }

    @Test
    @DisplayName("Java의 null을 받아 NPE를 발생시킨다 - platformType")
    fun throw_npe_with_platformType_type() {
        //given
        val nullGenerator = NullGenerator()

        //when
        val actual = nullGenerator.value

        //then
        assertThatThrownBy { actual.length }
            .isInstanceOf(NullPointerException::class.java)
    }
}