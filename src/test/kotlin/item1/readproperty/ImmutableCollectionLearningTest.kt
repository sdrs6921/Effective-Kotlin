package item1.readproperty

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ImmutableCollectionLearningTest {

    @Test
    @DisplayName("불변 컬렉션을 가변 컬렉션으로 다운캐스팅해서 사용하면 예외가 발생한다")
    fun immutable_list_throw_exception() {
        //given
        val nums = listOf(1, 2, 3)

        //when //then
        assertThatThrownBy {
            if (nums is MutableList) {
                nums.add(4)
            }
        }.isInstanceOf(UnsupportedOperationException::class.java)
    }
}