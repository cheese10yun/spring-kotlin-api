package com.spring.sample.order.order

import com.spring.sample.AuditingEntity
import org.hibernate.annotations.BatchSize
import java.math.BigDecimal
import javax.persistence.AttributeOverride
import javax.persistence.AttributeOverrides
import javax.persistence.CollectionTable
import javax.persistence.Column
import javax.persistence.ElementCollection
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.Table

@Entity
@Table(name = "orders")
class Order(

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name = "order_book",
        joinColumns = [JoinColumn(name = "orders_id", nullable = false, updatable = false)]
    )
    @BatchSize(size = 2)
    // row = 10
    // batchSize = 2 *(?)  = 10
    var books: MutableList<OrderBook> = mutableListOf(),

    @Embedded
    @AttributeOverrides(
        AttributeOverride(name = "name", column = Column(name = "name", nullable = false)),
        AttributeOverride(name = "email", column = Column(name = "email", nullable = false))
    )
    var orderder: Orderer,


    @Column(name = "price", nullable = false)
    var price: BigDecimal

) : AuditingEntity() {


    init {
        this.price = books.stream()
            .map { it.price }
            .reduce { a, b -> a.add(b) }
            .get()
    }
}