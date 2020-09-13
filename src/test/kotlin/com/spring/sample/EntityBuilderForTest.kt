package com.spring.sample

import com.spring.sample.book.Book
import com.spring.sample.order.Order
import com.spring.sample.order.OrderBook
import com.spring.sample.order.Orderer
import java.math.BigDecimal

fun buildBook(
    title: String = "title",
    writer: String = "writer",
    publisher: String = "publisher",
    price: BigDecimal = BigDecimal.TEN
) = Book(
    title = title,
    writer = writer,
    publisher = publisher,
    price = price
)

fun buildOrder(
    orderer: Orderer = buildOrderer(),
    books: List<Book> = listOf(buildBook())
) = Order(
    orderer = orderer,
    books = books
)

fun buildOrderer(
    name: String = "name",
    email: String = "asd@asd.com"
) = Orderer(
    name = name,
    email = email
)

fun buildOrderBook(
    bookId: Long = 0L,
    title: String = "title",
    price: BigDecimal = BigDecimal.TEN
) = OrderBook(
    bookId = bookId,
    title = title,
    price = price
)