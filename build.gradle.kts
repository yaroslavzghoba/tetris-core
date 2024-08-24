plugins {
    kotlin("jvm") version "2.0.0"
    id("maven-publish")
}

group = "com.yaroslavzghoba.tetris-core"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("maven") {
                from(components["java"])
                groupId = "com.github.yaroslavzghoba"
                artifactId = "tetris-core"
                version = "1.0.0"
            }
        }
    }
}