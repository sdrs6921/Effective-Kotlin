package item3

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class NullRepositoryImplTest {

    @Test
    @DisplayName("값을 가져올 때 NPE 예외를 발생시킨다")
    fun get_null_stared_type() {
        val repo: NullRepository = NullRepositoryImpl()

        assertThatThrownBy { val text: String = repo.getNull() }
            .isInstanceOf(NullPointerException::class.java)
    }

    @Test
    @DisplayName("값을 활용할 때 NPE 예외를 발생시킨다")
    fun get_null_platform_type() {
        //given
        val repo: NullRepository = NullRepositoryImpl()

        //when
        val actual = repo.getNull()

        //then
        assertThatThrownBy { actual.length }
            .isInstanceOf(NullPointerException::class.java)
    }
}