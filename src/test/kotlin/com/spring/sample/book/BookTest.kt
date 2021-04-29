package com.spring.sample.book

import com.spring.sample.SpringTestSupport
import javax.persistence.EntityManager
import org.junit.jupiter.api.Test
import org.springframework.data.repository.findByIdOrNull
import org.springframework.transaction.annotation.Transactional

@Transactional
internal class BookTest(
    private val bookRepository: BookRepository,
    private val orderRepository: OrderRepository,
    private val entityManager: EntityManager
) : SpringTestSupport() {

    @Test
    internal fun `lazy loading test`() {
        // 데이터 set up
        val order = orderRepository.save(Order("202012-12"))
        val book = bookRepository.save(Book(title = "title", order = order))

        // 영속성 컨텍스트 초기화
        entityManager.clear()

        // lazy loading 이기 때문에 book에 대한 조회 쿼리는 발생하지 않을 것이라고 예상
        val findBook = bookRepository.findByIdOrNull(book.id!!)!!

        // id 조회까지는 Lazy 로딩이 일어나지 않는다.
        findBook.order.id
        // number를 조회하는 경우 Lazy 로딩이 발생하는 것을 예상
        findBook.order.number

        println("")
    }
}


