package com.spring.sample.order.book

import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class OrderBook(

    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "price", nullable = false)
    var price: BigDecimal,

    @Column(name = "book_number", nullable = false, unique = true, updatable = false)
    val bookNumber: String
)