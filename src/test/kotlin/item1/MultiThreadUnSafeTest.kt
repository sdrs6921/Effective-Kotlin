package item1

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.concurrent.thread

class MultiThreadUnSafeTest {

    @Test
    @DisplayName("멀티 스레드 환경에서 공유 변수에 자원에 덧셈을 진행할 경우 원래보다 적게 더해진다")
    fun multi_thread_unsafe_test() {
        //given
        var actual = 0
        val expected = 1000

        //when
        for (i in 1..expected) {
            thread {
                Thread.sleep(10)
                actual += 1
            }
        }

        Thread.sleep(5000)

        //then
        assertThat(actual).isLessThan(expected)
     }
}