package com.spring.sample.order

import com.spring.sample.SpringWebTestSupport
import com.spring.sample.buildBook
import com.spring.sample.buildOrder
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.transaction.annotation.Transactional

@Transactional
internal class OrderApiTest : SpringWebTestSupport() {

    private val URI = "/api/orders"

    @Test
    internal fun `order 페이징 조회`() {
        //given
        (1..10).map {
            buildOrder(books = listOf(save(buildBook())))
        }.also {
            saveAll(it)
        }

        //when & then
        mockMvc.get(URI) {
            contentType = MediaType.APPLICATION_JSON
            param("page", "0")
            param("size", "7")
        }.andExpect {
            status { isOk }
            jsonPath("$.numberOfElements") { value(7) }
            jsonPath("$.size") { value(7) }
            jsonPath("$.number") { value(0) }
        }
    }

    @Test
    internal fun `order 단일 조회`() {
        //given
        val orderId = save(buildOrder(
            books = listOf(save(buildBook())))
        ).id!!

        //when
        mockMvc.get("$URI/{orderId}", orderId) {
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk }
            jsonPath("$.price") { value(10.00) }
        }

        //then
    }

    @Test
    internal fun `order 등록`() {
        //given
        val bookIds = (1..5)
            .map { buildBook() }
            .also { saveAll(it) }
            .map { it.id!! }
            .toLongArray()

        //when & then
        mockMvc.post(URI) {
            contentType = MediaType.APPLICATION_JSON
            content = """
                {
                  "orderer": {
                    "email": "string@asd.com",
                    "name": "string"
                  },
                  "bookIds": [${bookIds.joinToString(",")}]
                }
            """.trimIndent()
        }.andExpect {
            status { isOk }
        }
    }
}