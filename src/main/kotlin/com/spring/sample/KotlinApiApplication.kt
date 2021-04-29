package com.spring.sample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinApiApplication

fun main(args: Array<String>) {
    runApplication<KotlinApiApplication>(*args)
}

//@Component
//class KotlinApiRunner(
//    private val orderRepository: OrderRepository,
//    private val bookRepository: BookRepository
//) : ApplicationRunner {
//
//    override fun run(args: ApplicationArguments) {
//        val books = (1..10).map {
//            Book("title-$it", "writer-$it", "publisher-$it", price = (it * 1000).toBigDecimal())
//        }
//        bookRepository.saveAll(books)
//        val orders = (1..10).map {
//            Order(Orderer("name", "asd@asd.com"), books)
//        }
//        orderRepository.saveAll(orders)
//    }
//}