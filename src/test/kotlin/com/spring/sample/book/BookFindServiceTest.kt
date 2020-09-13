package com.spring.sample.book

import com.spring.sample.SpringTestSupport
import org.assertj.core.api.BDDAssertions.then
import org.assertj.core.api.BDDAssertions.thenThrownBy
import org.junit.jupiter.api.Test
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Transactional
internal class BookFindServiceTest(
    private val bookRepository: BookRepository,
    private val bookFindService: BookFindService
) : SpringTestSupport() {

    @Test
    internal fun `findById 존재하는 경우`() {
        //given
        val book = bookRepository.save(
            Book(
                title = "title",
                writer = "writer",
                publisher = "publisher",
                price = BigDecimal.TEN
            )
        )

        //when
        val findBook = bookFindService.findById(book.id!!)

        //then
        then(findBook.title).isEqualTo(findBook.title)
        then(findBook.writer).isEqualTo(findBook.writer)
        then(findBook.publisher).isEqualTo(findBook.publisher)
        then(findBook.price).isEqualByComparingTo(findBook.price)
    }

    @Test
    internal fun `findById가 존재하지 않은 경우`() {
        thenThrownBy { bookFindService.findById(0L) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

}