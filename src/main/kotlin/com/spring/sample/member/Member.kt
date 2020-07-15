package com.spring.sample.member

import com.spring.sample.EntityAuditing
import javax.persistence.Entity
import javax.persistence.Table


@Entity
@Table(name = "member")
data class Member(
    var firstName: String,
    var lastName: String,
    var email: String
) : EntityAuditing()

