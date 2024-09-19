plugins {
    kotlin("plugin.spring") apply false
    id("org.springframework.boot") apply false
    id("io.spring.dependency-management")
}

tasks.getByName("jar") {
    enabled = true
}

dependencies {
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.yaml:snakeyaml")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    testRuntimeOnly("org.postgresql:postgresql")
    runtimeOnly("com.h2database:h2")
//  TODO: implementation("org.springframework.boot:spring-boot-starter-security")
}
