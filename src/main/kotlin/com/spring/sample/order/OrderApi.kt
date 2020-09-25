package com.spring.sample.order

import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/orders")
class OrderApi(
    private val orderFindService: OrderFindService,
    private val orderService: OrderService
) {

    @GetMapping
    fun getOrders(@PageableDefault(sort = ["id"], direction = Sort.Direction.DESC) pageable: Pageable) = orderFindService.findPageBy(pageable)

    @GetMapping("/{orderId}")
    fun getOrder(@PathVariable orderId: Long) = orderFindService.findById(orderId)

    @PostMapping
    fun order(@RequestBody dto: OrderRequest) {
        orderService.order(dto)
    }
}

class OrderRequest(
    val orderer: Orderer,
    val booksIds: List<Long>
)