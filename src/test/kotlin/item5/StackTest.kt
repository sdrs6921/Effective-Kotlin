package item5

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class StackTest {

    @Test
    @DisplayName("요소를 추가한다")
    fun add() {
        //given
        val stack: Stack<Int> = Stack()
        val expected = 1
        stack.add(expected)

        //when
        stack.open()
        val actual = stack.pop()

        //then
        assertThat(actual).containsExactly(expected)
    }

    @Test
    @DisplayName("스택의 사이즈를 반환한다")
    fun size() {
        //given
        val stack: Stack<Int> = Stack()
        val size: Int = stack.size()
        stack.add(1)
        val expected: Int = size + 1

        //when
        val actual = stack.size()

        //then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    @DisplayName("스택의 크기보다 큰 값을 pop 하면 예외를 발생시킨다")
    fun pop_throw_exception_when_exceed_stack_size() {
        //given
        val stack: Stack<Int> = Stack()

        //when //then
        assertThatThrownBy { stack.pop() }
            .isInstanceOf(IllegalArgumentException::class.java)
            .withFailMessage("현재 스택의 크기보다 더 많은 크기를 pop 할 수 없습니다")
    }

    @Test
    @DisplayName("스택의 크기보다 큰 값을 pop 하면 예외를 발생시킨다")
    fun pop_throw_exception_when_exceed_stack_is_closed() {
        //given
        val stack: Stack<Int> = Stack()
        stack.add(1)

        //when //then
        assertThatThrownBy { stack.pop() }
            .isInstanceOf(IllegalStateException::class.java)
            .withFailMessage("닫힌 스택에서 pop 할 수 없습니다")
    }
}