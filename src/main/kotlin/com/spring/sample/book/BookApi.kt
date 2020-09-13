package com.spring.sample.book

import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
@RequestMapping("/api/books")
class BookApi(
    private val bookFindService: BookFindService,
    private val bookRegistrationService: BookRegistrationService,
    private val bookModificationService: BookModificationService
) {

    @GetMapping
    fun getBooks(@PageableDefault(sort = ["id"], direction = Sort.Direction.DESC) pageable: Pageable) = bookFindService.findPageable(pageable)

    @GetMapping("/{bookId}")
    fun getGook(@PathVariable bookId: Long) = bookFindService.findById(bookId)

    @PostMapping
    fun registerBook(@RequestBody dto: BookRegistrationRequest) {
        bookRegistrationService.register(dto)
    }

    @PutMapping("/{bookId}")
    fun modifyBook(@PathVariable bookId: Long, @RequestBody dto: BookModification) {
        bookModificationService.modify(bookId, dto)
    }
}

data class BookRegistrationRequest(
    val title: String,
    val writer: String,
    val publisher: String,
    val price: BigDecimal
) {
    fun toEntity() = Book(
        title = title,
        writer = writer,
        publisher = publisher,
        price = price
    )
}

data class BookModification(
    var title: String,
    var writer: String,
    var publisher: String,
    var price: BigDecimal
)