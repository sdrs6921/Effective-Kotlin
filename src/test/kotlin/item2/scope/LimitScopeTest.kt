package item2.scope

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class LimitScopeTest {

    val a = 1
    fun fizz() {
        val b = 2
        println(a + b)
    }

    val buzz = {
        val c = 3
        println(a + c)
    }

    @Test
    @DisplayName("변수 스코프에 관한 테스트")
    fun minimum_scope_test() {
        fizz()
        buzz()
    }

    val nums = listOf(1, 2, 3)

    @Test
    @DisplayName("변수 스코프를 제한하지 못하는 나쁜 예시")
    fun limit_scope_bad_case_example() {
        //given //when //then
        var num: Int

        for (i in nums.indices) {
            num = nums[i]
            println("number at $i is $num")
        }
    }

    @Test
    @DisplayName("변수의 스코프 제한을 조금 더 개선한 예시")
    fun limit_scope_advanced_example() {
        //given //when //then
        for (i in nums.indices) {
            val num = nums[i]
            println("number at $i is $num")
        }
    }

    @Test
    @DisplayName("변수의 스코프를 제한하는 제일 좋은 예시")
    fun limit_scope_best_exampl() {
        //given //when //then
        for ((i, num) in nums.withIndex()) {
            println("number at $i is $num")
        }
    }

}