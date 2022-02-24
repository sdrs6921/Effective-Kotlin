package item1.immutableobject.user

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class UserTest {

    @Test
    @DisplayName("이름과 성을 인자로 받아 프로퍼티를 초기화한다")
    fun create() {
        //given
        val expectedName = "희상"
        val expectedSurname = "엄"
        val user: User = User(expectedName, expectedSurname)

        //when
        val actualName = user.name
        val actualSurname = user.surname

        //then
        assertAll(
            { assertThat(actualName).isEqualTo(expectedName) },
            { assertThat(actualSurname).isEqualTo(expectedSurname) }
        )
    }

    @Test
    @DisplayName("성을 바꾸면 프로퍼티가 변경된다")
    fun withSurname() {
        //given
        val user = User("희상", "엄")
        val expectedSurname = "김"

        //when
        val actual = user.withSurname(expectedSurname)
        val actualSurname = actual.surname

        //then
        assertThat(actualSurname).isEqualTo(expectedSurname)
    }
}