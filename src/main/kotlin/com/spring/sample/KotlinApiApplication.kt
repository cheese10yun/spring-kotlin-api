package com.spring.sample

import com.spring.sample.order.order.Order
import com.spring.sample.order.order.OrderBook
import com.spring.sample.order.order.OrderRepository
import com.spring.sample.order.order.Orderer
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component
import java.util.UUID

@SpringBootApplication
class KotlinApiApplication

fun main(args: Array<String>) {
    runApplication<KotlinApiApplication>(*args)
}

@Component
class KotlinApiRunner(
    private val orderRepository: OrderRepository
) : ApplicationRunner {

    override fun run(args: ApplicationArguments) {



    }
}