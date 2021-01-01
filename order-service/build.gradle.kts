import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.4.1"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    war
    kotlin("jvm") version "1.4.21"
    kotlin("plugin.spring") version "1.4.21"
    id ("org.jetbrains.kotlin.plugin.noarg") version "1.3.50"
}

group = "com.example.sagas"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    // Eventuate Tram
//    implementation("io.eventuate.tram.core:eventuate-tram-spring-consumer-kafka:0.24.0.RELEASE")
//    implementation("io.eventuate.tram.core:eventuate-tram-spring-producer-jdbc:0.24.0.RELEASE")
//    implementation("io.eventuate.tram.core:eventuate-tram-spring-consumer-jdbc:0.24.0.RELEASE")
//    implementation("io.eventuate.tram.core:eventuate-tram-spring-optimistic-locking:0.24.0.RELEASE")
//    implementation("io.eventuate.tram.sagas:eventuate-tram-sagas-orchestration-simple-dsl:0.15.2.RELEASE")
//    implementation("io.eventuate.tram.sagas:eventuate-tram-sagas-spring-orchestration-simple-dsl:0.15.2.RELEASE")
//    implementation("io.eventuate.tram.sagas:eventuate-jpa-sagas-framework:0.15.2.RELEASE")
//    implementation("io.eventuate.tram.core:eventuate-tram-jdbc-kafka:0.15.2.RELEASE")
    // Spring Boot
    implementation("org.springframework.boot:spring-boot-starter-web:2.2.6.RELEASE")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.2.6.RELEASE")
    implementation("org.springframework.boot:spring-boot-starter-jdbc:2.2.6.RELEASE")

    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // MySQL
    implementation("mysql:mysql-connector-java")

    providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

noArg {
    annotation("javax.persistence.Entity")
}
