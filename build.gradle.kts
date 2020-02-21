plugins {
    kotlin("multiplatform") version "1.3.70-eap-42"
    id("maven-publish")
}

group = "com.github.dwursteisen.kotlin-math"
version = "1.0.0-SNAPSHOT"

repositories {
    jcenter()
    maven(url = "https://dl.bintray.com/kotlin/kotlin-eap")
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
    }

    macosX64() {
        binaries {
            staticLib {

            }

            sharedLib {
                
            }
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

        js().compilations["main"].defaultSourceSet {
            dependencies {
                implementation(kotlin("stdlib-js"))
            }
        }

        jvm().compilations["main"].defaultSourceSet {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
            }
        }
        jvm().compilations["test"].defaultSourceSet {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.12")
            }
        }
        macosX64().compilations["main"].defaultSourceSet {
            dependencies {

            }
        }
    }
}
