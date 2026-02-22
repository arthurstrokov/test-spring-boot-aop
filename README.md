# Spring Boot AOP Demonstration Project

This repository provides a comprehensive guide and demonstration of **Aspect-Oriented Programming (AOP)** in a Spring Boot environment. It covers both high-level Spring AOP usage (with custom annotations and aspects) and low-level manual proxying techniques (JDK Dynamic Proxies and CGLIB/ByteBuddy).

## Overview

The project is designed for educational purposes to show:
- How to implement custom AOP annotations.
- How to create aspects to intercept method execution.
- The differences between JDK Dynamic Proxies and CGLIB proxies.
- Advanced proxying with ByteBuddy.

### Key Features
- **Execution Time Logging**: Custom `@LogExecutionTime` annotation to measure and log method duration.
- **Method Inspection**: Custom `@LogInspectingMethod` annotation to log detailed method signature information (modifiers, parameters, return types).
- **Manual Proxy Demos**: Unit tests demonstrating manual creation of proxies without the full Spring container.

## Requirements

- **Java**: 25 or later (required for Spring Boot 4.0 compatibility).
- **Build Tool**: Gradle (wrapper included).

## Tech Stack

- **Framework**: Spring Boot 4.0.3
- **AOP Support**: `spring-boot-starter-aspectj`
- **Testing**: JUnit 5 (JUnit Jupiter)
- **Utilities**: Lombok (for boilerplate reduction), ByteBuddy (for advanced proxying examples), SLF4J/Logback (for logging).

## Setup and Run

### Clone the Repository
```bash
git clone https://github.com/arthurstrokov/test-spring-boot-aop.git
cd test-spring-boot-aop
```

### Build the Project
```bash
./gradlew build
```

### Run the Application
The main entry point is `com.gmail.arthurstrokov.aop.Application`.
```bash
./gradlew bootRun
```

## Scripts

- `./gradlew build`: Compiles, tests, and packages the application.
- `./gradlew bootRun`: Runs the Spring Boot application.
- `./gradlew test`: Executes all unit and integration tests.
- `./gradlew clean`: Removes the build directory.

## Tests

The project includes both integration tests for Spring AOP and demonstration tests for manual proxying.

### Running All Tests
```bash
./gradlew test
```

### Running Specific Tests
- **AOP Annotation Tests**: `./gradlew test --tests com.gmail.arthurstrokov.aop.service.AnnotationAopTest`
- **JDK Proxy Demo**: `./gradlew test --tests com.gmail.arthurstrokov.aop.proxy.jdk.JdkDynamicProxyDemoTest`
- **CGLIB Proxy Demo**: `./gradlew test --tests com.gmail.arthurstrokov.aop.proxy.cglib.CglibDynamicProxyDemoTest`

Testing uses `OutputCaptureExtension` to verify that aspects are correctly logging information to the console.

## Project Structure

```text
.
├── build.gradle                # Build configuration
├── gradlew                     # Gradle wrapper script
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.gmail.arthurstrokov.aop
│   │   │       ├── Application.java        # Entry point
│   │   │       ├── annotations             # Custom AOP annotations (@LogExecutionTime, etc.)
│   │   │       ├── aspects                 # Aspect implementations (LogExecutionTimeAspect, etc.)
│   │   │       └── service                 # Business logic services using AOP
│   │   └── resources
│   │       └── application.properties     # Application configuration
│   └── test
│       └── java
│           └── com.gmail.arthurstrokov.aop
│               ├── proxy                   # Educational manual proxy examples (JDK, CGLIB)
│               └── service                 # Integration tests for AOP services
└── README.md
```

## Environment Variables

No specific environment variables are required for this project to run with default settings.

## License

TODO: Add license information here (e.g., MIT, Apache 2.0).

---
Developed by Arthur Strokov.

