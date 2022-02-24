package item4.inferered.general

import item4.Animal
import item4.Zebra
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ZebraTest {

    @Test
    @DisplayName("inferred type 상위 타입으로 설정되지 않는 테스트")
    fun inferred_type_compile_error_when_super_class_type() {
        //given //when //then
        var animal = Zebra()
//        animal = Animal() 컴파일 에러 발생
    }

    @Test
    @DisplayName("제한된 타입 설정 테스트")
    fun inferred_type_type_definition() {
        //given
        var animal: Animal = Zebra()

        //when //then
        animal = Animal()

        assertThat(animal)
            .isInstanceOf(Animal::class.java)
    }
}