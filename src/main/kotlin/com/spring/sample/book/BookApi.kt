package com.spring.sample.book

import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/books")
class BookApi(
    private val bookRepository: BookRepository
) {

    @GetMapping
    fun getBooks(pageable: Pageable) = bookRepository.findAll(pageable)
}