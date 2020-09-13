package com.spring.sample.order.order

import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class OrderBook(

    @Column(name = "book_id", nullable = false, updatable = false)
    var bookId: Long,

    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "price", nullable = false)
    var price: BigDecimal
)