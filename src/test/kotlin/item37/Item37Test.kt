package item37

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe

internal class Item37Test : AnnotationSpec() {

    data class Player(
        val id: Int,
        val name: String,
        val points: Int
    )

    enum class Color {
        BLUE, YELLOW, RED
    }

    data class FullName(val firstName: String, val lastName: String)

    @Test
    fun `data 클래스는 toString 메서드르 가진다`() {
        //given
        val expected = "Player(id=0, name=희상, points=9999)"
        val player = Player(0, "희상", 9999)

        //when
        val actual = player.toString()

        //then
        actual shouldBe expected;
    }

    @Test
    fun `data 클래스는 equals와 hashCode 메서드를 가진다`() {
        //given
        val player = Player(0, "희상", 9999)
        val other = Player(0, "희상", 9999)

        //when
        val actualEqualsHashCode = player.hashCode() == other.hashCode()

        //then
        player shouldBe other
        actualEqualsHashCode shouldBe true
    }

    @Test
    fun `data 클래스는 copy 메서드를 가진다`() {
        //given
        val expectedId = 1
        val player = Player(0, "희상", 9999)
        val actual = player.copy(id = expectedId)

        //when
        val actualId = actual.id
        val actualName = actual.name
        val actualPoints = actual.points

        //then
        actualId shouldBe expectedId
        actualName shouldBe player.name
        actualPoints shouldBe player.points
    }

    @Test
    fun `data 클래스는 객체 해체 시, componentN 메서드를 가진다`() {
        //given
        val player = Player(0, "희상", 9999)
        val expectedId = player.component1()
        val expectedName = player.component2()
        val expectedPoints = player.component3()

        //when
        val (actualId, actualName, actualPoints) = player


        //then
        actualId shouldBe expectedId
        actualName shouldBe expectedName
        actualPoints shouldBe expectedPoints
    }

    @Test
    fun `list 클래스는 객체의 해체가 가능하다`() {
        //given
        val expectedFirst = "first"
        val expectedSecond = "second"
        val expectedThird = "third"
        val numbers = listOf(expectedFirst, expectedSecond, expectedThird)

        //when
        val (actualFirst, actualSecond, actualThird) = numbers


        //then
        actualFirst shouldBe expectedFirst
        actualSecond shouldBe expectedSecond
        actualThird shouldBe expectedThird
    }

    @Test
    fun `list 클래스가 최대 인덱스 보다 초괴해서 객체를 해제 할 시, 예외를 발생시킨다`() {
        //given
        val numbers = listOf(1, 2, 3)

        //when //them
        shouldThrow<ArrayIndexOutOfBoundsException>(
            {
                val (one, two, three, four) = numbers
            }::invoke
        )
    }

    @Test
    fun `map 클래스는 객체를 직접 해제할 수 있다`() {
        //given
        val dept = listOf("김포", "서울")
        val dest = listOf("제주도", "강릉")

        val trips = mapOf(
            dept[0] to dest[0],
            dept[1] to dest[1]
        )

        //whe //then
        var index = 0

        for ((actualDept, actualDest) in trips) {
            actualDept shouldBe dept[index]
            actualDest shouldBe dest[index]
            index++
        }
    }

    @Test
    fun `값을 하나만 갖는 데이터 클래스 객체 해제 람다 표현식 예제`() {
        //given
        data class User(val name: String)

        val expected = "희상"

        val user = User(expected)

        //when
        val actual = user.let { (u) -> u }

        //then
        actual shouldBe expected
    }

    @Test
    fun `값에 간단하게 이름을 붙일 때 튜플을 사용`() {
        val degree = 17

        val (description, color) = when {
            degree < 5 -> "cold" to Color.BLUE
            degree < 23 -> "mild" to Color.YELLOW
            else -> "hot" to Color.RED
        }

        description shouldBe "mild"
        color shouldBe Color.YELLOW
    }

    @Test
    fun `미리 알 수 없는 집합을 표현할 때`() {
        //given
        val numbers = listOf(1, 2, 3, 4)

        //when
        val (odd, even) = numbers.partition { it % 2 == 1 }
        val map = mapOf(1 to "한국", 2 to "미국")

        //then
        odd shouldBe listOf(1, 3)
        even shouldBe listOf(2, 4)
        map[1] shouldBe "한국"
        map[2] shouldBe "미국"
    }

    @Test
    fun `튜플을 사용하여 FullName 표현`() {
        //given
        fun String.parseName(): Pair<String, String>? {
            val indexOfLastSpace = this.trim().lastIndexOf(' ')
            if (indexOfLastSpace < 0) return null
            val firstName = this.take(indexOfLastSpace)
            val lastName = this.drop(indexOfLastSpace + 1)
            return Pair(firstName, lastName)
        }

        val fullName = "희상 엄"

        //when
        val (firstName, lastName) = fullName.parseName() ?: return

        //then
        lastName shouldBe "엄"
        firstName shouldBe "희상"
    }

    @Test
    fun `데이터 클래스를 사용하여 FullName 표현`() {
        //given
        fun String.parseName(): FullName? {
            val indexOfLastSpace = this.trim().lastIndexOf(' ')
            if (indexOfLastSpace < 0) return null
            val firstName = this.take(indexOfLastSpace)
            val lastName = this.drop(indexOfLastSpace + 1)
            return FullName(firstName, lastName)
        }

        val fullName = "희상 엄"

        //when
        val (firstName, lastName) = fullName.parseName() ?: return

        //then
        lastName shouldBe "엄"
        firstName shouldBe "희상"
    }
}

