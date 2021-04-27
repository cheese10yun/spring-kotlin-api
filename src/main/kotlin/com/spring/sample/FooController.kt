package com.spring.sample

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/foo")
class FooController {

    @PostMapping
    fun foo(@RequestBody dto: BarRequest) = dto
}

data class BarRequest(
    val name: String
)