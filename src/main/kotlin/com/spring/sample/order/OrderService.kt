package com.spring.sample.order

import com.spring.sample.book.BookFindService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val bookFindService: BookFindService
) {

    @Transactional
    fun order(dto: OrderRequest): Order {
        val books = bookFindService.findByIds(dto.bookIds)
        return orderRepository.save(Order(dto.orderer, books))
    }
}

data class OrderRequest(
    val orderer: Orderer,
    val bookIds: List<Long>
)

@Service
@Transactional(readOnly = true)
class OrderFindService(
    private val orderRepository: OrderRepository
) {

    fun findById(orderId: Long): Order {
        return orderRepository.findById(orderId).orElseThrow { throw IllegalArgumentException("$orderId 를 찾지못했습니다.") }
    }


    fun findPageBy(pageable: Pageable): Page<Order> {
        return orderRepository.findAll(pageable)
    }
}