### Project Guidelines

This document provides project-specific details for development, testing, and configuration of the Spring Boot AOP project.

#### 1. Build and Configuration
- **Build System**: Gradle. Use the included `./gradlew` for all tasks to ensure consistency.
- **Spring Boot Version**: 4.0.3.
- **Java Version**: Ensure you're using a version compatible with Spring Boot 4.0 (Java 25 recommended).
- **Key Dependencies**:
  - `spring-boot-starter-aop`: Provides the core AOP support.
  - `lombok`: Used for boilerplate reduction (requires annotation processing enabled in your IDE).
  - `byte-buddy`: Included for advanced proxying examples.

#### 2. Testing Information
- **Testing Framework**: JUnit 5 (JUnit Jupiter).
- **Spring Integration**: Use `@SpringBootTest` for integration tests that require the full application context.
- **AOP Verification**: To verify aspect output in tests, use the `OutputCaptureExtension` from Spring Boot Test.
- **Example Test Structure**:
  ```java
  @SpringBootTest
  @ExtendWith(OutputCaptureExtension.class)
  class AopTest {
      @Autowired
      private MyService service;

      @Test
      void testAspect(CapturedOutput output) {
          service.performAction();
          assertTrue(output.getOut().contains("Expected log message from aspect"));
      }
  }
  ```
- **Running Tests**: Execute `./gradlew test` to run all tests or `./gradlew test --tests <className>` for specific ones.

#### 3. Development Information
- **AOP Implementation**:
  - Custom annotations are located in `com.gmail.arthurstrokov.aop.annotations`.
  - Aspects are in `com.gmail.arthurstrokov.aop.aspects`.
  - Current aspects use `@Around` advice for logging execution time and inspecting method signatures, and `@AfterThrowing` for logging exceptions.
- **Proxying**:
  - The project demonstrates both JDK Dynamic Proxies (for interfaces) and CGLIB/ByteBuddy (for classes).
  - Refer to `src/test/java/com/gmail/arthurstrokov/aop/proxy/` for educational examples of manual proxy creation.
- **Logging**:
  - SLF4J with Logback is the default. Aspects log information to the `INFO` level, and exceptions to the `ERROR` level.
- **Code Style**:
  - Follow standard Java and Spring Boot conventions.
  - Use Lombok for logging (`@Slf4j`) and boilerplate (constructors, getters/setters) where appropriate.
