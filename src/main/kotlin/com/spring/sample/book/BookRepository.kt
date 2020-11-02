package com.spring.sample.book

import com.spring.sample.order.Order
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<Book, Long> {

    fun findByIdIn(ids: List<Long>): List<Book>
}