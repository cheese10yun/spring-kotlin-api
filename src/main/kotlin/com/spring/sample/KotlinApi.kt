package com.spring.sample

import com.spring.sample.order.book.OrderBook
import com.spring.sample.order.order.Order
import com.spring.sample.order.order.OrderRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component
import java.util.UUID

@SpringBootApplication
class KotlinApi

fun main(args: Array<String>) {
    runApplication<KotlinApi>(*args)
}

@Component
class KotlinApiRunner(
    private val orderRepository: OrderRepository
) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        val order = (1..10)
            .map {
                Order(
                    (1..10)
                        .map { OrderBook("title", 100.toBigDecimal(), UUID.randomUUID().toString()) }
                        .toMutableList(), 1000.toBigDecimal())
            }

        orderRepository.saveAll(order)
    }
}