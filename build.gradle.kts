import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// Properties
ext["testcontainersVersion"] = "1.16.3"
ext["springCloudVersion"] = "2021.0.1"
val kotlinVersion = "1.6.21"
val tgBotApiVersion = "2.0.0"
val ktlintVersion = "0.45.2"
val caffeineVersion = "3.1.0"

plugins {
    val kotlinVersion = "1.6.21"

    idea

    id("org.springframework.boot") version "2.6.8"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"

    kotlin("jvm") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.allopen") version kotlinVersion

    id("org.jlleitschuh.gradle.ktlint") version "10.2.0"
}

group = "ru.isin-smart-soft"
version = "1.0.0"
java.sourceCompatibility = JavaVersion.VERSION_11

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
        mavenBom("org.testcontainers:testcontainers-bom:${property("testcontainersVersion")}")
    }
}

dependencies {

    // SPRING
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-configuration-processor")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    // caching
    implementation("org.springframework.boot:spring-boot-starter-cache")
    implementation("com.github.ben-manes.caffeine:caffeine:$caffeineVersion")

    // Coroutine
    // implementation("org.springframework.kotlin:spring-kotlin-coroutine:0.3.7")
    // implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0")
    // implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0")

    // logging
    implementation("net.logstash.logback:logstash-logback-encoder:7.1.1")
    implementation("io.github.microutils:kotlin-logging-jvm:2.1.21")

    // test
    testImplementation("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.8.2")
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlinVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5:$kotlinVersion")
    testImplementation("io.mockk:mockk:1.12.4")
    testImplementation("com.ninja-squad:springmockk:3.1.1")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // CheckStyle
    ktlint("com.pinterest:ktlint:$ktlintVersion")

    // Telegram
    implementation("org.telegram:telegrambots:6.0.1")

    // Annotation Processor
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
}

springBoot {
    buildInfo() // Добавляет bean BuildProperties в контекст
}

tasks {
    compileKotlin {
        dependsOn("ktlintCheck")
    }

    withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }

    withType<Test> {
        useJUnitPlatform()
    }

    withType<Jar> {
        // Workaround for https://stackoverflow.com/q/42174572/750510
        archiveBaseName.set(rootProject.name + "-" + this.project.path.removePrefix(":").replace(":", "-"))
    }
}

repositories {
    jcenter()
    mavenCentral()
    maven("https://dl.bintray.com/konrad-kaminski/maven")
}
