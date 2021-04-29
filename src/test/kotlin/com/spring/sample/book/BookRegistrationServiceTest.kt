//package com.spring.sample.book
//
//import com.spring.sample.SpringTestSupport
//import org.assertj.core.api.BDDAssertions.then
//import org.junit.jupiter.api.Test
//import org.springframework.transaction.annotation.Transactional
//import java.math.BigDecimal
//
//@Transactional
//internal class BookRegistrationServiceTest(
//    private val bookRegistrationService: BookRegistrationService
//) : SpringTestSupport() {
//
//    @Test
//    internal fun `register`() {
//        //given
//        val dto = BookRegistrationRequest(
//            title = "title",
//            writer = "writer",
//            publisher = "publisher",
//            price = BigDecimal.TEN
//        )
//
//        //when
//        val book = bookRegistrationService.register(dto)
//
//        //then
//        then(book.title).isEqualTo("title")
//        then(book.writer).isEqualTo("writer")
//        then(book.publisher).isEqualTo("publisher")
//        then(book.price).isEqualTo(BigDecimal.TEN)
//    }
//}