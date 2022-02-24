package item1.readproperty

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


class ReadPropertyLearningTest {

    var name: String = "희상"
    var surname = "엄"
    val fullName
        get() = "$name $surname"

    @Test
    @DisplayName("읽기 전용 프로퍼티에 쓰기 작업을 진행하면 컴파일 에러가 발생한다")
    fun read_property_write_compile_error() {
        //given
        val actual = 10

        //when //then
        // actual = 20 컴파일 에러
    }

    @Test
    @DisplayName("읽기 전용 프로퍼티에 mutable collection 객체를 할당할 수 있다")
    fun read_property_with_mutable_list() {
        //given
        val expectedElement = 1
        val expectedElement2 = 2
        val expectedElement3 = 3
        val expectedElement4 = 4
        val actual = mutableListOf(1, 2, 3)

        //when
        actual.add(4)

        //then
        assertThat(actual).containsExactly(expectedElement, expectedElement2, expectedElement3, expectedElement4)
    }

    @Test
    @DisplayName("가변 프로퍼티에 getter를 읽기 전용 프로퍼티로 사용한다")
    fun mutable_property_read_only_property_getter() {
        //given
        val expected = "희상 엄"

        //when
        val actual = fullName

        //then
        assertThat(actual).isEqualTo(expected)
    }
}