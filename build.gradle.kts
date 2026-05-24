import org.gradle.jvm.toolchain.JavaLanguageVersion

plugins {
    java
    alias(libs.plugins.spring.boot)
}

group = "com.catalystone"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // Spring Boot BOM — pins all managed versions (replaces io.spring.dependency-management plugin)
    implementation(platform(libs.spring.boot.bom))

    implementation(libs.spring.boot.starter)
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.jdbc)
    implementation(libs.spring.boot.starter.jpa)
    implementation(libs.spring.boot.starter.actuator)
    implementation(libs.h2)
    implementation(libs.flyway.core)
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)

    testImplementation(libs.spring.boot.starter.test)
    testImplementation(libs.junit.jupiter.api)
    testRuntimeOnly(libs.junit.jupiter.engine)
    testImplementation(libs.mockito.junit.jupiter)
    testCompileOnly(libs.lombok)
    testAnnotationProcessor(libs.lombok)
}

tasks.withType<Test> {
    useJUnitPlatform()
}
