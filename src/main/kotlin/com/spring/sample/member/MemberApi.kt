package com.spring.sample.member

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.Email

@RestController
@RequestMapping("/api/members")
class MemberApi(
    private val memberRepository: MemberRepository
) {

    private val log by logger()
    private val members = mutableListOf<Member>()
    private var memberId = 1L

    @GetMapping("/{id}")
    fun getMember(@PathVariable id: Long): Member {
        val member = memberRepository.findById(id).orElseThrow { IllegalArgumentException("asd") }
        log.info(member.toString())
        return member
    }

    @GetMapping
    fun getMembers(
        @PageableDefault(sort = ["id"], direction = Sort.Direction.DESC) pageable: Pageable
    ): Page<Member> {
        val memberWithPage = memberRepository.findAll(pageable)
        log.info(memberWithPage.toString())
        return memberWithPage
    }

    @PostMapping
    fun createMember(
        @RequestBody @Valid request: MemberCreatRequest
    ) {
        val member = Member(
            firstName = request.firstName,
            lastName = request.lastName,
            email = request.email
        )
        memberRepository.save(member)
        log.info(member.toString())
    }
}

data class MemberCreatRequest(
    val firstName: String,
    val lastName: String,
    @field:Email
    val email: String
)

fun <A : Any> A.logger(): Lazy<Logger> = lazy { LoggerFactory.getLogger(this.javaClass) }