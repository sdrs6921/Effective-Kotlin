package item2.scope

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class InitializeTest {

    @ParameterizedTest
    @CsvSource(value = ["true, 1", "false, 0"])
    @DisplayName("if를 활용하여 변수를 초기화 하는 나쁜 예")
    fun if_init_worst_example(hasValue: Boolean, expected: Int) {
        //given
        val actual: Int

        if (hasValue) {
            actual = 1
        } else {
            actual = 0
        }

        //when //then
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource(value = ["true, 1", "false, 0"])
    @DisplayName("if를 활용하여 변수를 초기화 하는 좋은 예")
    fun if_init_advanced_example(hasValue: Boolean, expected: Int) {
        //given
        val actual: Int = if (hasValue) {
            1
        } else {
            0
        }

        //when //then
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource(value = ["4,cold,Blue", "22,mild,Yellow", "23,hot,Red"])
    @DisplayName("구조 분해 선언 나쁜예예")
    fun destructing_declaration_worst_example(degree: Int, expectedDescription: String, expectedColor: String) {
        //given
        val actualDescription: String
        val actualColor: String

        if (degree < 5) {
            actualDescription = "cold"
            actualColor = "Blue"
        } else if (degree < 23) {
            actualDescription = "mild"
            actualColor = "Yellow"
        } else {
            actualDescription = "hot"
            actualColor = "Red"
        }

        //when //then
        assertAll(
            { assertThat(actualDescription).isEqualTo(expectedDescription) },
            { assertThat(actualColor).isEqualTo(expectedColor) }
        )
    }

    @ParameterizedTest
    @CsvSource(value = ["4,cold,Blue", "22,mild,Yellow", "23,hot,Red"])
    @DisplayName("구조 분해 선언 나쁜예예")
    fun destructing_declaration_advanced_example(degree: Int, expectedDescription: String, expectedColor: String) {
        //given
        val (actualDescription, actualColor) = when {
            degree < 5 -> "cold" to "Blue"
            degree < 23 -> "mild" to "Yellow"
            else -> "hot" to "Red"
        }

        //when //then
        assertAll(
            { assertThat(actualDescription).isEqualTo(expectedDescription) },
            { assertThat(actualColor).isEqualTo(expectedColor) }
        )
    }
}