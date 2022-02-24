package item1.mutablelist

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.concurrent.thread

class MutableListTest {

    @Test
    @DisplayName("가변 리스트를 활용한 리스트 상태 변경")
    fun mutable_list_test() {
        //given
        val list = mutableListOf(1, 2, 3)

        //when
        list += 4

        //then
        assertThat(list).containsExactly(1, 2, 3, 4)
    }

    @Test
    @DisplayName("불변 리스트와 var를 활용한 리스트 상태 변경")
    fun immutable_list_change_with_var() {
        //given
        var list = listOf(1, 2, 3)

        //when
        list += 4

        //then
        assertThat(list).containsExactly(1, 2, 3, 4)
    }

    @Test
    @DisplayName("멀티 스레드 환경에서 리스트 변경시 충돌이 발생한다")
    fun immutable_list_in_multi_thread() {
        //given
        var list = listOf<Int>()
        val expected = 1000
        for (i in 1..expected) {
            thread {
                list += i
            }
        }

        Thread.sleep(1000)

        //when
        val actual = list.size

        //then
        assertThat(actual).isLessThan(expected)
    }
}