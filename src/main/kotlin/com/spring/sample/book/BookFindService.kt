package com.spring.sample.book

import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class BookFindService(
    private val bookRepository: BookRepository
) {

    fun findById(bookId: Long): Book {
        return bookRepository.findById(bookId).orElseThrow { throw IllegalArgumentException("$bookId 는 존재하지 않습니다") }
    }

    fun findPageable(pageable: Pageable) = bookRepository.findAll(pageable)

}