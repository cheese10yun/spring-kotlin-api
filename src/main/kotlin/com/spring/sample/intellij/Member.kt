package com.spring.sample.intellij

/**
 * 동일 문자열 ⌘ + ⌃ + g
 * 동일 위치열 ⌥ + drag
 * 복사 히스토리 command + shift + v를
 */
data class Member(
    val name: String,
    val email: String,
    val phone: String,
    val address: String
)

data class MemberRequestDto(
    val name: String,
    val email: String,
    val phone: String,
    val address: String
)


interface MemberCustomRepository {

}

/**
 * Refactoring : Pull Members Up
 * MemberCustomRepository 인터페이스로 메서드를 올린다
 */
class MemberRepositoryImpl : MemberCustomRepository {

    fun findByXXX(): List<Member> {
        return listOf(Member(name = "name", email = "email", phone = "phone", address = "address"))
    }

}

