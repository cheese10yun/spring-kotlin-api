package com.spring.sample.order

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Orderer(
    @Column(name = "name", nullable = false)
    var name: String,
    @Column(name = "email", nullable = false)
    var email: String
)