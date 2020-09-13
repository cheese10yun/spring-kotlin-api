package com.spring.sample.book

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BookRegistrationService(
    private val bookRepository: BookRepository
) {

    @Transactional
    fun register(dto: BookRegistrationRequest): Book {
        return bookRepository.save(dto.toEntity())
    }
}

@Service
class BookModificationService(
    private val bookFindService: BookFindService
) {

    @Transactional
    fun modify(bookId: Long, dto: BookModification) {
        bookFindService.findById(bookId).apply {
            this.title = dto.title
            this.writer = dto.writer
            this.publisher = dto.publisher
            this.price = dto.price
        }
    }
}