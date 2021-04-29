package com.spring.sample.book

import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<Book, Long> {

    fun findByIdIn(ids: List<Long>): List<Book>
}