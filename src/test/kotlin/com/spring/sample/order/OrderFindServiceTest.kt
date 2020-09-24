package com.spring.sample.order

import com.spring.sample.SpringTestSupport
import com.spring.sample.buildBook
import com.spring.sample.buildOrder
import org.assertj.core.api.BDDAssertions.then
import org.assertj.core.api.BDDAssertions.thenThrownBy
import org.junit.jupiter.api.Test
import org.springframework.data.domain.PageRequest
import org.springframework.transaction.annotation.Transactional

@Transactional
internal class OrderFindServiceTest(
    private val orderFindService: OrderFindService
) : SpringTestSupport() {

    @Test
    internal fun `findById 조회가능한 경우`() {
        //given
        val orderId = save(buildOrder(books = listOf(save(buildBook())))).id!!

        //when
        val order = orderFindService.findById(orderId)

        //then
        then(order.id).isNotNull()
    }

    @Test
    internal fun `findById 조회가 없는 경우 예외가 발생한다`() {
        //when & then
        thenThrownBy {
            orderFindService.findById(0L)
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    internal fun `findPageBy 테스트`() {
        //given
        (1..5).map {
            buildOrder(books = listOf(save(buildBook())))
        }.also {
            saveAll(it)
        }

        val pageRequest = PageRequest.of(0, 3)


        //when
        val pageOrder = orderFindService.findPageBy(pageRequest)

        //then
        then(pageOrder.isFirst).isTrue()
        then(pageOrder.content).hasSize(3)
    }
}