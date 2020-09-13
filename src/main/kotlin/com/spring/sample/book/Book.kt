package com.spring.sample.book

import com.spring.sample.AuditingEntity
import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "book")
class Book(

    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "writer", nullable = false)
    var writer: String,

    @Column(name = "publisher", nullable = false)
    var publisher: String,

    @Column(name = "price", nullable = false)
    var price: BigDecimal,

    @Column(name = "book_number", nullable = false, unique = true, updatable = false)
    val bookNumber: String

) : AuditingEntity() {

}