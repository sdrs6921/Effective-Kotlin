package item1

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MutableLearningTest {

    @Test
    @DisplayName("프로퍼티에 10을 할당하여 10을 반환한다")
    fun allocate_property_return_value() {
        //given
        val expected = 10

        //when
        var given = 10

        //then
        assertThat(given).isEqualTo(expected)
    }

    @Test
    @DisplayName("가변 콜렉션에 값을 집어넣으면 값의 순서대로 값이 할당된다")
    fun mutable_collection_add_element() {
        //given
        val expectedElement1 = 1;
        val expectedElement2 = 2;
        val expectedElement3 = 3;
        var given: MutableList<Int> = mutableListOf();

        //when
        given.add(1)
        given.add(2)
        given.add(3)

        //then
        assertThat(given).containsExactly(expectedElement1, expectedElement2, expectedElement3)
    }

}