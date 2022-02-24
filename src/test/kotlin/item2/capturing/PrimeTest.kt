package item2.capturing

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class PrimeTest {

    @Test
    @DisplayName("단순 구현 소수 리스트 생성")
    fun generate_prime_simple_implementation() {
        //given
        var numbers = (2..100).toList()
        val primes = mutableListOf<Int>()

        //when
        while (numbers.isNotEmpty()) {
            val prime = numbers.first()
            primes.add(prime)
            numbers = numbers.filter { it % prime != 0 }
        }

        //then
        assertThat(primes).containsExactly(
            2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31,
            37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97
        )
    }

    @Test
    @DisplayName("시퀀스를 활용한 소수 리스트 생성")
    fun generate_prime_using_sequence() {
        //given
        val primes: Sequence<Int> = sequence {
            var numbers = generateSequence(2) { it + 1 }

            while (true) {
                val prime = numbers.first()
                yield(prime)
                numbers = numbers.drop(1)
                    .filter { it % prime != 0 }
            }
        }

        //when
        val actual = primes.take(25).toList()

        //then
        assertThat(actual).containsExactly(
            2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31,
            37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97
        )
    }

    @Test
    @DisplayName("시퀀스를 활용하여 소수 리스트 생성 시 capturing이 발생하면 원하는 값이 나오지 않는다")
    fun generate_prime_fail_with_capturing() {
        //given
        val primes: Sequence<Int> = sequence {
            var numbers = generateSequence(2) { it + 1 }

            var prime: Int
            while (true) {
                prime = numbers.first()
                yield(prime)
                numbers = numbers.drop(1)
                    .filter { it % prime != 0 }
            }
        }

        //when
        val actual = primes.take(11).toList()

        //then
        assertThat(actual).containsExactly(
            2, 3, 5, 6, 7, 8, 9, 10, 11, 12, 13
        )
    }
}