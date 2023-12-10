plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("com.rikonardo.papermake") version "1.0.6"
    id("net.kyori.blossom") version "2.1.0"
}

group = "me.name"
version = "0.1.0"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.2-R0.1-SNAPSHOT")
}

tasks {
    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
    }

    jar {
        enabled = false
    }

    shadowJar {
        archiveClassifier.set("")
    }

    build {
        dependsOn(shadowJar)
    }
}

sourceSets {
    main {
        blossom {
            resources {
                property("group", project.group.toString())
                property("name", project.name.lowercase().replace("-", ""))
                property("version", project.version.toString())
            }
        }
    }
}
