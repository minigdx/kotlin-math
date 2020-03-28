plugins {
    kotlin("multiplatform") version "1.3.70"
    id("maven-publish")
    id("org.jlleitschuh.gradle.ktlint") version "9.2.1"
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
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/dwursteisen/kotlin-math")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
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
