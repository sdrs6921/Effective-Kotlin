package item28

import io.kotest.core.spec.style.AnnotationSpec

internal class NewApiTest : AnnotationSpec() {

    @Test
    fun `experimental_test`() {
        //given
        val newApi = NewApi()

        //when //then
        newApi.experimentalApi()
    }

}