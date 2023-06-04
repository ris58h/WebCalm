plugins {
    id("org.jetbrains.kotlin.jvm") version "1.7.20"
    id("org.jetbrains.intellij") version "1.13.1"
    id("org.jetbrains.changelog") version "2.1.0"
    id("antlr")
}

group = "ris58h.webcalm"
version = "0.4"

repositories {
    mavenCentral()
}

val antlrVersion = "4.12.0"

dependencies {
    implementation("org.antlr:antlr4-intellij-adaptor:0.1") {
        constraints {
            implementation("org.antlr", "antlr4-runtime", antlrVersion) {
                because("Old runtime leads to 'Could not deserialize ATN' error.")
            }
        }
    }
    antlr("org.antlr", "antlr4", antlrVersion) {
        // Not required for 'generateGrammarSource' task.
        exclude("com.ibm.icu", "icu4j")
        exclude("org.abego.treelayout", "org.abego.treelayout.core")
    }
}

//TODO: a hack to exclude antlr4 dependencies from the resulting distribution zip. See https://github.com/gradle/gradle/issues/820
configurations[JavaPlugin.API_CONFIGURATION_NAME].let { apiConfiguration ->
    apiConfiguration.setExtendsFrom(apiConfiguration.extendsFrom.filter { it.name != "antlr" })
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

    buildSearchableOptions {
        enabled = false
    }

    changelog {
        // Allow both d.d and d.d.d version formats.
        headerParserRegex.set("""(\d+\.\d+(.\d+)?)""".toRegex())
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}
