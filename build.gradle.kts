import java.util.Date
import java.util.Properties

plugins {
    kotlin("multiplatform") version "1.3.70"
    id("maven-publish")
    id("org.jlleitschuh.gradle.ktlint") version "9.2.1"
    id("com.jfrog.bintray") version "1.8.5"
}

group = "com.github.dwursteisen.kotlin-math"
version = project.properties["version"] ?: "1.0-SNAPSHOT"

if (version == "unspecified") {
    version = "1.0-SNAPSHOT"
}

repositories {
    jcenter()
    maven(url = "https://dl.bintray.com/kotlin/kotlin-eap")
}

publishing {
    publications {
        create<MavenPublication>("default") {
            from(components["kotlin"])
        }
    }
}

kotlin {
    /* Targets configuration omitted.
    *  To find out how to configure the targets, please follow the link:
    *  https://kotlinlang.org/docs/reference/building-mpp-with-gradle.html#setting-up-targets */

    js {
        this.useCommonJs()
        this.browser
        this.nodejs
    }

    jvm {
        this.compilations.getByName("main").kotlinOptions.jvmTarget = "1.8"
        this.compilations.getByName("test").kotlinOptions.jvmTarget = "1.8"
    }

    mingwX64() {
        binaries {
            staticLib {}
            sharedLib {}
        }
    }
    linuxX64() {
        binaries {
            staticLib {}
            sharedLib {}
        }
    }
    ios() {
        binaries {
            staticLib {}
            sharedLib {}
        }
    }
    macosX64() {
        binaries {
            staticLib {}
            sharedLib {}
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(kotlin("stdlib-js"))
            }
        }

        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))

                implementation("com.badlogicgames.gdx:gdx-backend-lwjgl:1.9.9")
                implementation("com.badlogicgames.gdx:gdx-platform:1.9.9:natives-desktop")

                implementation("org.assertj:assertj-core:3.11.1")
            }
        }

        val macosX64Main by getting {
            dependencies { }
        }
    }
}

val properties = Properties()
if (project.file("local.properties").exists()) {
    properties.load(project.file("local.properties").inputStream())
}

val bintrayUser = if (project.hasProperty("bintray_user")) {
    project.property("bintray_user") as? String
} else {
    System.getProperty("BINTRAY_USER")
}

val bintrayKey = if (project.hasProperty("bintray_key")) {
    project.property("bintray_key") as? String
} else {
    System.getProperty("BINTRAY_KEY")
}

configure<com.jfrog.bintray.gradle.BintrayExtension> {
    user = properties.getProperty("bintray.user") ?: bintrayUser
    key = properties.getProperty("bintray.key") ?: bintrayKey
    publish = true
    setPublications("maven")
    pkg(delegateClosureOf<com.jfrog.bintray.gradle.BintrayExtension.PackageConfig> {
        repo = "minigdx"
        name = project.name
        githubRepo = "dwursteisen/kotlin-math.git"
        vcsUrl = "https://github.com/dwursteisen/kotlin-math.git"
        description = project.description
        setLabels("java")
        setLicenses("Apache 2.0")
        desc = description
        version(closureOf<com.jfrog.bintray.gradle.BintrayExtension.VersionConfig> {
            this.name = project.version.toString()
            released = Date().toString()
        })
    })
}

tasks.named("bintrayUpload") {
    dependsOn(":publishToMavenLocal")
}

tasks.withType<com.jfrog.bintray.gradle.tasks.BintrayUploadTask> {
    doFirst {
        project.publishing.publications
            .filterIsInstance<MavenPublication>()
            .forEach { publication ->
                val moduleFile = buildDir.resolve("publications/${publication.name}/module.json")
                if (moduleFile.exists()) {
                    publication.artifact(object :
                        org.gradle.api.publish.maven.internal.artifact.FileBasedMavenArtifact(moduleFile) {
                        override fun getDefaultExtension() = "module"
                    })
                }
            }
    }
}
