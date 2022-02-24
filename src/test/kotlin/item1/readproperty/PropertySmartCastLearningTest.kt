package item1.readproperty

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class PropertySmartCastLearningTest {
    val name: String? = "희상"
    val surname: String = "엄"

    val fullName: String?
        get() = name?.let { "$it $surname" }
    val fullName2: String? = name?.let { "$it $surname" }

    @Test
    @DisplayName("읽기 전용 프로퍼티에 ? 연산자를 사용했을 떄, null이 아니란 것을 if문에서 확인하면 스마트 캐스트를 진행한다")
    fun smart_cast_test() {
        //given
        val expected = 4

        //when
        var actual: Int? = null
        var actual2: Int = 0

        if (fullName != null) {
            // 스마트 캐스트 적용 안됨
            actual = fullName?.length
        }

        if (fullName2 != null) {
            // 스마트 캐스트 적용
            actual2 = fullName2.length
        }

        //then
        assertAll(
            { assertThat(actual).isEqualTo(expected) },
            { assertThat(actual2).isEqualTo(expected) }
        )
    }
}