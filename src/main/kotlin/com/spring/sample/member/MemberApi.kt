package com.spring.sample.member

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/members")
class MemberApi {

    private val members = mutableListOf<Member>()
    private var memberId = 1L

    @GetMapping("/{id}")
    fun getMember(@PathVariable id: Long): Member {
        return members.stream()
            .filter { it.id == id }
            .findFirst()
            .orElseThrow { IllegalArgumentException("member id: $id 존재하지 않습니다") }
    }

    @GetMapping
    fun getMembers(): MutableList<Member> {
        return members
    }

    @PostMapping
    fun createMember(
        @RequestBody request: MemberCreatRequest
    ) {
        members.add(Member(
            id = memberId++,
            firstName = request.firstName,
            lastName = request.lastName,
            email = request.email
        ))
    }
}

data class MemberCreatRequest(
    val firstName: String,
    val lastName: String,
    val email: String
)