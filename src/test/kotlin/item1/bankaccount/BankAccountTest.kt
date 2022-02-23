package item1.bankaccount

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.DisplayName

internal class BankAccountTest {

    @Test
    @DisplayName("잔액을 조회한다")
    fun getBalance() {
        //given
        val expected = 0.0
        val bankAccount = BankAccount()

        //when
        val actual = bankAccount.balance

        //then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    @DisplayName("계좌에 이체하면 이체한 이체한 만큼의 잔액을 반환한다")
    fun deposit() {
        //given
        val expected = 50.0
        val bankAccount = BankAccount()

        val depositAmount = 50.0
        bankAccount.deposit(depositAmount)

        //when
        val actual = bankAccount.balance

        //then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    @DisplayName("계좌에서 돈을 꺼내면 꺼내고 남은 잔액을 반환한다")
    fun withdraw() {
        //given
        val expected = 30.0
        val backAccount = BankAccount()

        val depositAmount = 50.0
        backAccount.deposit(depositAmount)
        val withdrawAmount = 20.0
        backAccount.withdraw(withdrawAmount)

        //when
        val actual = backAccount.balance

        //then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    @DisplayName("계좌에 남은 잔액보다 더 많은 금액을 뺴려고 시도하면 예외를 발생시킨다")
    fun withdraw_throw_exception() {
        //given
        val backAccount = BankAccount()
        val withdrawAmount = 50.0

        //when //then
        assertThatThrownBy { backAccount.withdraw(withdrawAmount) }
            .isInstanceOf(InsufficientFunds::class.java)
    }
}