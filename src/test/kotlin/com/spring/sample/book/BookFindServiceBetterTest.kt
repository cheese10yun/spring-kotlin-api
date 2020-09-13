package com.spring.sample.book

import com.spring.sample.SpringTestSupport
import org.assertj.core.api.BDDAssertions.then
import org.assertj.core.api.BDDAssertions.thenThrownBy
import org.junit.jupiter.api.Test
import org.springframework.data.domain.PageRequest
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

    @Test
    internal fun `findByIds`() {
        //given
        val bookIds = (1..5).map {
            bookRepository.save(
                Book(
                    title = "title",
                    writer = "writer",
                    publisher = "publisher",
                    price = BigDecimal.TEN
                )
            )

        }.map {
            it.id!!
        }

        //when
        val books = bookFindService.findByIds(bookIds)

        //then
        then(books).hasSize(5)
    }

    @Test
    internal fun `findPageable`() {
        //given
        (1..5).map {
            Book(
                title = "title",
                writer = "writer",
                publisher = "publisher",
                price = BigDecimal.TEN
            )
        }.also {
            bookRepository.saveAll(it)
        }

        val pageRequest = PageRequest.of(0, 5)

        //when
        val page = bookFindService.findPageable(pageRequest)

        //then
        then(page.size).isEqualTo(5)
    }
}

@Transactional
internal class BookFindServiceBetterTest(
    private val bookFindService: BookFindService
) : SpringTestSupport() {

    @Test
    internal fun `findById 존재하는 경우`() {
        //given
        val bookId = save(
            buildBook(
                title = "title",
                writer = "writer",
                publisher = "publisher",
                price = BigDecimal.TEN
            )
        ).id!!

        //when
        val book = bookFindService.findById(bookId)

        //then
        then(book.title).isEqualTo("title")
        then(book.writer).isEqualTo("writer")
        then(book.publisher).isEqualTo("publisher")
        then(book.price).isEqualByComparingTo(BigDecimal.TEN)
    }

    @Test
    internal fun `findById가 존재하지 않은 경우`() {
        thenThrownBy { bookFindService.findById(0L) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    internal fun `findByIds`() {
        //given
        val bookIds = (1..5).map {
            save(
                Book(
                    title = "title",
                    writer = "writer",
                    publisher = "publisher",
                    price = BigDecimal.TEN
                )
            )

        }.map {
            it.id!!
        }

        //when
        val books = bookFindService.findByIds(bookIds)

        //then
        then(books).hasSize(5)
    }

    @Test
    internal fun `findPageable`() {
        //given
        (1..5).map {
            Book(
                title = "title",
                writer = "writer",
                publisher = "publisher",
                price = BigDecimal.TEN
            )
        }.also {
            saveAll(it)
        }

        val pageRequest = PageRequest.of(0, 5)

        //when
        val page = bookFindService.findPageable(pageRequest)

        //then
        then(page.size).isEqualTo(5)
    }
}