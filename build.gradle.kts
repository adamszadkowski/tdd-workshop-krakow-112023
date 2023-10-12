import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.20"
    kotlin("plugin.spring") version "1.9.20"

    id("org.springframework.boot") version "3.1.5"
    id("nebula.integtest") version "9.6.3"
}

repositories {
    mavenCentral()
}

springBoot {
    mainClass.set("pl.allegro.tdd.DummyMainKt")
}

dependencies {
    implementation(enforcedPlatform(kotlin("bom")))
    implementation(platform("org.springframework.boot:spring-boot-dependencies:3.1.5"))
    implementation(platform("org.junit:junit-bom:5.10.1"))

    constraints {
        implementation("io.strikt:strikt-core:0.34.1")
        implementation("io.mockk:mockk:1.13.8")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.junit.jupiter:junit-jupiter-params")
    testImplementation("io.strikt:strikt-core")
    testImplementation("io.mockk:mockk")

    integTestImplementation("org.springframework.boot:spring-boot-starter-test")
    integTestImplementation("org.springframework.security:spring-security-test")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}


tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "17"
        }
    }

    withType<Test> {
        useJUnitPlatform()
    }
}
