package com.spring.sample.order.order

import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import java.math.BigDecimal

internal class OrderTest {

    @Test
    internal fun name() {
        val orderBooks = (1..10).map {
            OrderBook("title$it", 10.toBigDecimal(), "book-number-$it")
        }
            .toMutableList()


        val order = Order(orderBooks)
        then(order.price).isEqualByComparingTo(100.toBigDecimal())

        order.price = BigDecimal.ZERO
    }
}