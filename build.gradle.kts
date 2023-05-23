plugins {
    id("org.jetbrains.kotlin.jvm") version "1.7.20"
    id("org.jetbrains.intellij") version "1.13.1"
    id("antlr")
}

group = "ris58h.webcalm"
version = "0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.antlr:antlr4-intellij-adaptor:0.1")
    antlr("org.antlr:antlr4:4.12.0") {
        exclude("com.ibm.icu", "icu4j")
    }
}

intellij {
    version.set("2020.3") // Build against 'since' version
//    version.set("LATEST-EAP-SNAPSHOT") // Check against 'latest' version
    type.set("IC") // Target IDE Platform

    updateSinceUntilBuild.set(false)
}

tasks {
    withType<JavaCompile> {
        sourceCompatibility = "11"
        targetCompatibility = "11"
    }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }
    getByName("compileKotlin").dependsOn("generateGrammarSource")

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}
