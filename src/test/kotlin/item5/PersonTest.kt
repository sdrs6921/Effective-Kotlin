package item5

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class PersonTest {

    @Test
    @DisplayName("Person을 생성한다")
    fun create() {
        //given
        val expected = "엄희상"
        val person = Person(expected)

        //when
        val actual = person.getName()

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    @DisplayName("이름이 null이면 예외를 발생시킨다")
    fun create_throw_exception_when_name_is_null() {
        //given //when //then
        assertThatIllegalArgumentException().isThrownBy { Person(null) }
    }

    @Test
    @DisplayName("이름을 변경한다")
    fun change_name() {
        //given
        val person = Person("엄희상")

        //when
        person.changeName("엄휘상")

        //then
        assertThat(person.getName()).isEqualTo("엄휘상")
    }

    @Test
    @DisplayName("이름 변경시, 이름이 null이면 예외를 발생시킨다")
    fun change_name_throw_exception_with_name_is_null() {
        //given
        val person = Person("엄희상")

        // when //then
        assertThatIllegalArgumentException().isThrownBy { person.changeName(null) }
    }

    @Test
    @DisplayName("elvis 연산자로 이름을 변경한다")
    fun change_name_with_elvis() {
        //given
        val person = Person("엄희상")
        val expected = "엄휘상"
        person.changeNameWithElvis(expected)

        //when
        val actual = person.getName()

        //then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    @DisplayName("elvis 연산자로 이름을 변경시 null 값이면 예외를 발생시킨다")
    fun change_name_with_elvis_throw_exception() {
        //given
        val person = Person("엄희상")

        //when //then
        assertThatIllegalArgumentException().isThrownBy { person.changeNameWithElvis(null) }
    }
}