package com.spring.sample.book

import java.math.BigDecimal

internal class BookTest


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