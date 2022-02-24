package item1.immutableobject.dataclass

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class UserTest {

    @Test
    @DisplayName("성과 이름을 인자로 받아 생성한다")
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
    @DisplayName("특정 프로퍼티 변경 시, 복사한 값을 변경하여 반환한다")
    fun copy() {
        //given
        val user = User("희상", "엄")
        val expectedSurname = "김"

        //when
        val actualUser = user.copy(surname = expectedSurname)
        val actualSurname = actualUser.surname

        //then
        assertThat(actualSurname).isEqualTo(expectedSurname)
    }
}