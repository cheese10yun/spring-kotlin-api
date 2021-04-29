//package com.spring.sample.book
//
//import com.spring.sample.SpringWebTestSupport
//import com.spring.sample.buildBook
//import org.assertj.core.api.BDDAssertions.then
//import org.hamcrest.CoreMatchers.`is`
//import org.junit.jupiter.api.Test
//import org.springframework.http.MediaType
//import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get
//import org.springframework.test.web.servlet.get
//import org.springframework.test.web.servlet.post
//import org.springframework.test.web.servlet.put
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
//import org.springframework.transaction.annotation.Transactional
//
//
//@Transactional
//internal class BookApiTest : SpringWebTestSupport() {
//
//    private val URL = "/api/books"
//
//    @Test
//    internal fun `registerBook`() {
//        mockMvc.perform(
//            post(URL)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(
//                    """
//                        {
//                          "title":  "title",
//                          "writer": "writer",
//                          "publisher":  "publisher",
//                          "price": 100.00
//                        }
//                    """.trimIndent()
//                )
//        ).andExpect(status().isOk)
//    }
//
//    @Test
//    internal fun `book 단일 조회`() {
//        val book = save(buildBook())
//
//        mockMvc.perform(
//            get("$URL/{bookId}", book.id!!)
//                .contentType(MediaType.APPLICATION_JSON)
//        )
//            .andExpect(status().isOk())
//            .andExpect(jsonPath("$.title", `is`(book.title)))
//            .andExpect(jsonPath("$.writer", `is`(book.writer)))
//            .andExpect(jsonPath("$.publisher", `is`(book.publisher)))
//    }
//}
//
//@Transactional
//internal class BookApiTestBetter : SpringWebTestSupport() {
//
//    private val URL = "/api/books"
//
//    @Test
//    internal fun `registerBook`() {
//        mockMvc.post(URL) {
//            contentType = MediaType.APPLICATION_JSON
//            content = """
//                {
//                    "title":  "title",
//                    "writer": "writer",
//                    "publisher":  "publisher",
//                    "price": 100.00
//                }
//
//            """.trimIndent()
//        }.andExpect {
//            status { isOk }
//        }
//    }
//
//    @Test
//    internal fun `book 페이징 조회`() {
//
//        (1..10).map {
//            buildBook()
//        }.apply {
//            saveAll(this)
//        }
//
//        mockMvc.get(URL) {
//            contentType = MediaType.APPLICATION_JSON
//            param("size", "5")
//            param("page", "0")
//        }.andExpect {
//            status { isOk }
//            jsonPath("$.numberOfElements") { value(5) }
//            jsonPath("$.size") { value(5) }
//            jsonPath("$.number") { value(0) }
//        }
//    }
//
//
//    @Test
//    internal fun `book 수정`() {
//        //given
//        val bookid = save(buildBook(title = "old-title")).id!!
//
//        //when
//        mockMvc.put("$URL/{bookId}", bookid) {
//            contentType = MediaType.APPLICATION_JSON
//            content = """
//                {
//                  "title": "new-modify",
//                  "writer": "writer-modify",
//                  "publisher": "publisher-modify",
//                  "price": 7000.0
//                }
//            """.trimIndent()
//        }.andExpect {
//            status { isOk }
//        }
//
//        //then
//        val book = query.selectFrom(QBook.book).where(QBook.book.id.eq(bookid)).fetchOne()!!
//
//        then(book.title).isEqualTo("new-modify")
//    }
//
//    @Test
//    internal fun `book 단일 조회`() {
//        //given
//        val book = save(buildBook())
//
//        //when & then
//        mockMvc.get("$URL//{bookId}", book.id!!) {
//            contentType = MediaType.APPLICATION_JSON
//            content =
//                """
//                    {
//                      "title":  "title",
//                      "writer": "writer",
//                      "publisher":  "publisher",
//                      "price": 100.00
//                    }
//                """.trimIndent()
//
//        }.andExpect {
//            status { isOk }
//            jsonPath("$.title") { value(book.title) }
//            jsonPath("$.price") { value(10.0) }
//            jsonPath("$.publisher") { value(book.publisher) }
//            jsonPath("$.writer") { value(book.writer) }
//        }
//    }
//}
//
