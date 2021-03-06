package com.spring.sample.order

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Instant

@RestController
@RequestMapping("/sample")
class SampleApi {

    @PostMapping
    fun asd(
        @RequestBody aaa: AAA
    ){


    }
}

data class AAA(
    val date: Instant
)