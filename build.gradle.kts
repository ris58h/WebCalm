import org.jetbrains.changelog.Changelog
import org.jetbrains.changelog.markdownToHTML

fun properties(key: String) = providers.gradleProperty(key)
fun environment(key: String) = providers.environmentVariable(key)

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.9.0"
    id("org.jetbrains.intellij.platform") version "2.3.0"
    id("org.jetbrains.changelog") version "2.1.0"
    id("antlr")
}

group = properties("pluginGroup").get()
version = properties("pluginVersion").get()

repositories {
    mavenCentral()
    intellijPlatform {
        defaultRepositories()
    }
}

val antlrVersion = "4.12.0"

dependencies {
    intellijPlatform {
        create(properties("platformType"), properties("platformVersion"))

        pluginVerifier()
    }

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

kotlin {
    jvmToolchain(17)
}

intellijPlatform {
    pluginConfiguration {
        name = properties("pluginName")
        ideaVersion {
            sinceBuild = properties("pluginSinceBuild")
            untilBuild = provider { null }
        }
    }
    buildSearchableOptions = false

    pluginVerification {
        ides {
            recommended()
        }
    }
}

changelog {
    groups.empty()
    repositoryUrl.set(properties("pluginRepositoryUrl"))

    // Allow both d.d and d.d.d version formats.
    headerParserRegex.set("""(\d+\.\d+(.\d+)?)""".toRegex())
}

tasks {
    compileKotlin {
        dependsOn(generateGrammarSource)
    }

    patchPluginXml {
        // Extract the <!-- Plugin description --> section from README.md and provide for the plugin's manifest
        pluginDescription.set(providers.fileContents(layout.projectDirectory.file("README.md")).asText.map {
            val start = "<!-- Plugin description -->"
            val end = "<!-- Plugin description end -->"

            with (it.lines()) {
                if (!containsAll(listOf(start, end))) {
                    throw GradleException("Plugin description section not found in README.md:\n$start ... $end")
                }
                subList(indexOf(start) + 1, indexOf(end)).joinToString("\n").let(::markdownToHTML)
            } +
                    "<br>" +
                    "<a href=\"https://github.com/ris58h/WebCalm\">Source Code</a>" +
                    "<br>" +
                    "<a href=\"https://github.com/ris58h/WebCalm/issues\">Issue Tracker</a>"
        })

        val changelog = project.changelog // local variable for configuration cache compatibility
        // Get the latest available change notes from the changelog file
        changeNotes.set(properties("pluginVersion").map { pluginVersion ->
            with(changelog) {
                renderItem(
                    (getOrNull(pluginVersion) ?: getUnreleased())
                        .withHeader(false)
                        .withEmptySections(false),
                    Changelog.OutputType.HTML,
                )
            }
        })
    }

    // A long releases list leads to `No space left on device` error while performing `build` GitHub action.
    // So we shrink the list to verify just against the since and the latest versions.
    val shrinkProductsReleases = register("shrinkProductsReleases") {
        doLast {
            val file = printProductsReleases.get().outputs.files.singleFile
            val lines = file.readLines()
            if (lines.size > 2) {
                val shrunkLines = listOf(lines.first(), lines.last())
                file.writeText(shrunkLines.joinToString("\n"))
            }
        }
    }
    printProductsReleases {
        finalizedBy(shrinkProductsReleases)
    }

    signPlugin {
        certificateChain.set(environment("CERTIFICATE_CHAIN"))
        privateKey.set(environment("PRIVATE_KEY"))
        password.set(environment("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        dependsOn(patchChangelog)
        token.set(environment("PUBLISH_TOKEN"))
    }
}
