package com.spring.sample.book

import org.springframework.stereotype.Service

@Service
class BookRegistrationService(
    private val bookRepository: BookRepository
) {

    fun register() {
        val book = Book("객체지향", "yun", "자바 출판사", 10000.toBigDecimal())
        bookRepository.save(book)
    }
}