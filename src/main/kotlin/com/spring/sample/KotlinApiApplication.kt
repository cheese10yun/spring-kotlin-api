package com.spring.sample

import com.spring.sample.book.Book
import com.spring.sample.book.BookRepository
import com.spring.sample.order.order.Order
import com.spring.sample.order.order.OrderRepository
import com.spring.sample.order.order.Orderer
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component

@SpringBootApplication
class KotlinApiApplication

fun main(args: Array<String>) {
    runApplication<KotlinApiApplication>(*args)
}

@Component
class KotlinApiRunner(
    private val orderRepository: OrderRepository,
    private val bookRepository: BookRepository
) : ApplicationRunner {

    override fun run(args: ApplicationArguments) {

        val books = (1..10).map {
            Book("title-$it", "writer-$it", "publisher-$it", price = (it * 1000).toBigDecimal())
        }

        bookRepository.saveAll(books)


        val orders = (1..10).map {
            Order(Orderer("name", "asd@asd.com"), books)
        }

        orderRepository.saveAll(orders)



    }
}