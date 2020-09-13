package com.spring.sample.order

import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/orders")
class OrderApi(
    private val orderRepository: OrderRepository
) {

    @GetMapping
    fun getOrders(pageable: Pageable) = orderRepository.findAll(pageable)
}