package com.spring.sample.order

import com.spring.sample.SpringTestSupport
import com.spring.sample.buildBook
import com.spring.sample.buildOrderer
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Transactional
internal class OrderServiceTest(
    private val orderService: OrderService
) : SpringTestSupport() {

    @Test
    internal fun `order test`() {

        //given
        val bookIds = (1..5).map {
            save(buildBook(price = BigDecimal.ONE))
        }.map {
            it.id!!
        }

        val dto = OrderRequest(
            orderer = buildOrderer(),
            booksIds = bookIds
        )

        //when
        val order = orderService.order(dto)

        //then
        then(order.price).isEqualByComparingTo(5.toBigDecimal())
        then(order.orderBooks).hasSize(5)
        then(order.orderBooks).allSatisfy {
            then(it.price).isEqualByComparingTo(BigDecimal.ONE)
        }
    }
}