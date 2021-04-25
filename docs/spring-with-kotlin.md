# Spring With Kotlin

* plugin.spring 해주는 것들
    * final
* com.fasterxml.jackson.module:jackson-module-kotlin 이 해주는 것
* plugin.jpa 해주는것

## ??
* [ ] plugin.spring 이 해주는 것들
* [ ] plugin.jpa 해주는 것들
* [ ] jpa all open이 필요한 이유
* [ ] org.jetbrains.kotlin:kotlin-reflect 해주는거
* [ ] org.jetbrains.kotlin:kotlin-stdlib-jdk8 해주는거
* [ ] com.fasterxml.jackson.module:jackson-module-kotlin 해주는거



# Spring with Kotlin 

코틀린 기반으로 스프링 프레임워크를 사용하면서 겪었던 문제들을 정리해봄



## plugin.spring 플러그인

코틀린 언어로 스프링을 개발할 때 `plugin.spring` 플러그인을 사용합니다. 해당 플러그인을 사용하면 아래 어노테이션이 있으면 `all-open`을 자동으로 추가 시킵니다. 참고로 `kotlin-allopen`, `plugin.spring`는 동일한 프로젝트입니다.

* @Component
* @Async
* @Transactional
* @Cacheable
* @SpringBootTest
* @Configuration, @Controller, @RestController, @Service, @Repository, @Component


### 왜 all-open이 필요할까?


## 


## 참고
* [https://kotlinlang.org/docs](https://kotlinlang.org/docs/all-open-plugin.html)
* []()