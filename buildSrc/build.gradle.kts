repositories {
    mavenCentral()
}

val antlrVersion = "4.12.0"

dependencies {
    implementation(gradleApi())

    implementation("org.antlr", "antlr4", antlrVersion) {
        exclude("com.ibm.icu", "icu4j")
        exclude("org.abego.treelayout", "org.abego.treelayout.core")
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}
