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



## build.gradle.kts

```gradle
plugins {
    id("org.springframework.boot") version "2.4.5"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.asciidoctor.convert") version "1.5.8"
    id("jacoco")

    kotlin("jvm") version "1.4.32"
    kotlin("plugin.spring") version "1.4.32"
    kotlin("plugin.jpa") version "1.4.32"
    kotlin("kapt") version "1.4.32"
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}
```

## plugin.spring 플러그인

### all-open

코틀린 언어로 스프링을 개발할 때 `plugin.spring` 플러그인을 사용합니다. 해당 플러그인을 사용하면 아래 어노테이션이 있으면 `all-open`을 자동으로 추가 시킵니다. 참고로 `kotlin-allopen`, `plugin.spring`는 동일한 프로젝트입니다.

* @Component
* @Async
* @Transactional
* @Cacheable
* @SpringBootTest
* @Configuration, @Controller, @RestController, @Service, @Repository, @Component


```kotlin
class Foo {
    fun test() {}
}
```

코틀린에서는 기본적으로 클래스에는 `final` 키워드가 첨부되며, 위 코드도 `final` 키워드를 자동으로 추가하며,  생략하도라도 자동으로 추가됩니다.

```kotlin
public final class Foo public constructor() {
    public final fun test(): kotlin.Unit { /* compiled code */ }
}
```

`Foo` 클래스를 빌드 하면 위 처럼 `final` 키워드가 붙어 있는 것을 확인 할 수 있습니다.


```kotlin
@Transactional
class Foo {
    fun test() {}
}
```
`@Transactional` 어노테이션을 붙이고 다시 빌드 해보고 클래스 파일을 살펴보겠습니다.

```kotlin
@org.springframework.transaction.annotation.Transactional public open class Foo public constructor() {
    public open fun test(): kotlin.Unit { /* compiled code */ }
}
```
`class`, `fun` 키워드 앞에 `open` 키워드가 추가된 것을 확인 할 수 있습니다. `plugin.spring` 플러그인을 사용하면 `open` 키워드를 직접 추가하지 않아도 됩니다.

[Spring Initializr](https://start.spring.io/)를 이용해서 프로젝트를 구성하면 `plugin.spring` 플러그인은 자동 적용됩니다.

```gradle
allOpen {
    annotation("javax.persistence.Entity")
    ...
}
```
`gradle` 설정으로 특정 어노테이션에 대해서 `allOpen`을 동작 시킬 수 있습니다.

```kotlin
@Entity
@Table(name = "book")
class Book(

    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "writer", nullable = false)
    var writer: String,

    ...

) : AuditingEntity() {
}
```
위 객체를 빌드해서 클래스 파일을 확인 하면 아래와 같습니다.

```kotlin
@javax.persistence.Entity @javax.persistence.Table public open class Book public constructor(title: kotlin.String, writer: kotlin.String, publisher: kotlin.String, price: java.math.BigDecimal) : com.spring.sample.AuditingEntity {
    @field:javax.persistence.Column public open var price: java.math.BigDecimal /* compiled code */

    @field:javax.persistence.Column public open var publisher: kotlin.String /* compiled code */

    @field:javax.persistence.Column public open var title: kotlin.String /* compiled code */

    @field:javax.persistence.Column public open var writer: kotlin.String /* compiled code */
}
```
`open` 키워드가 붙어 있는 것을 확인 할 수 있습니다. 특정 어노테이션에 `open` 키워드를 편리하기 추가할 수 있습니다.


### open이 왜 필요할까?

### no-arg

`no-arg`는 argument가 없는 기본 생성자를 의미합니다. 클래스는 기본 생성자가 기본저긍로 생성되며 다른 생성자를 만들면 기본 생성자는 명시적으로 선언하지 않는 이상 사라지게 됩니다.

```kotlin
class Bar(val name: String) {
    // constructor() : this("none name")
}

// 기본 생성자를 주석한 경우
public final class Bar public constructor(name: kotlin.String) {
    public final val name: kotlin.String /* compiled code */
}

// 기본 생성자를 주석 하지 않는 경우
public final class Bar public constructor(name: kotlin.String) {
    public constructor() { /* compiled code */ }
    public final val name: kotlin.String /* compiled code */
}
```

`no-arg`는 주로 `plugin.jpa` 플러그인과 같이 사용됩니다. `kotlin-spring` 플러그인과 마찬가지로 `@Entity`, `@Embeddable`, `@MappedSuperclass` 어노테이션에 자동으로 동작합니다.


```kotlin
@Entity
@Table(name = "book")
class Book(
    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "writer", nullable = false)
    var writer: String,

    @Column(name = "publisher", nullable = false)
    var publisher: String,

    @Column(name = "price", nullable = false)
    var price: BigDecimal

) : AuditingEntity() {
}

// 빌드한 클래스를 Decompile 결과
public class Book extends AuditingEntity {
    ...
    public Book(@NotNull String title, @NotNull String writer, @NotNull String publisher, @NotNull BigDecimal price) {
      Intrinsics.checkNotNullParameter(title, "title");
      Intrinsics.checkNotNullParameter(writer, "writer");
      Intrinsics.checkNotNullParameter(publisher, "publisher");
      Intrinsics.checkNotNullParameter(price, "price");
      super();
      this.title = title;
      this.writer = writer;
      this.publisher = publisher;
      this.price = price;
   }

   public Book() {
   }
}
```
Book 객체에 기본 생성자가 없지만 `plugin.jpa` 플러그인으롷 인해서 기본 생성자가 생성됩니다.


### no-arg이 왜 필요할까?



## Kotlin With JPA
* [x] d?
* [x] d?
* [x] d?
* [x] d?


Kotlin -> Java 호출은 크게 문제 없음
Java -> Kotlin 호출시 문제



## jackson-module-kotlin 모듈 -> 현재는 문제 없음

[Spring Initializr](https://start.spring.io/)를 이용해서 `Spring Web MVC` 프로젝트를 생성하게 되면 `com.fasterxml.jackson.module:jackson-module-kotlin` 디펜던시가 자동으로 추가된다. [jackson-module-kotlin](https://github.com/FasterXML/jackson-module-kotlin)는 기존 Jackson 으로 deserialize 하기 위해서는 기본 생성자가 반드시 필요 합니다. 하지만 코틀린에서 `data class`의 객체를 deserialize를 진행하게 되면 기본 생성자가 없기 때문에 문제가 발생합니다.

```java
class SampleRequestBody {
    private String name;
    private int age;

// all arguemtn 생성자는 주석
//    public SampleRequestBody(String name, int age) {
//        this.name = name;
//        this.age = age;
//    }
}
```
위 같은 자바 코드는 all arguemtn 생성자가 주석인 경우 기본 생성자가를 명시적으로 선언하지 않아도 존재하기 때문에 deserialize 진행에 문제가 없습니다. 만약 다른 생성자가 있다면 명시적으로 기본 생성자를 작성하지 않으면 예외가 발생합니다.

```kotlin
data class SampleRequestBody(
    val name: String,
    val age: Int
)

// 위 data class를 빌드 이후 Decompile 결과
public final class SampleRequestBody {
   @NotNull
   private final String name;
   private final int age;

   @NotNull
   public final String getName() {
      return this.name;
   }

   public final int getAge() {
      return this.age;
   }

   public SampleRequestBody(@NotNull String name, int age) {
      Intrinsics.checkNotNullParameter(name, "name");
      super();
      this.name = name;
      this.age = age;
   }
   ..
}
```
코틀린의 `data class`는 all argument 생성자만 생성하기 때문에 기존 jackson으로 deserialize를 못하고 `jackson-module-kotlin`을 통해서 단일 생성자로 deserialize를 진핼할 수 있습니다.




## 참고
* [https://kotlinlang.org/docs](https://kotlinlang.org/docs/all-open-plugin.html)
* [Why does Hibernate require no argument constructor?](https://stackoverflow.com/questions/2935826/why-does-hibernate-require-no-argument-constructor)