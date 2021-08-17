plugins {
    java
    kotlin("multiplatform") version "1.4.32"
    id("org.jetbrains.compose") version "0.0.0-web-dev-11"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

kotlin {
    js(IR) {
        browser()
        binaries.executable()
    }
    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation(compose.web.web)
                implementation(compose.runtime)
            }
        }
    }
}
tasks.getByName<Test>("test") {
    useJUnitPlatform()
}