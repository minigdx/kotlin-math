plugins {
    kotlin("multiplatform") version "1.3.60"
    id("maven-publish")
}

repositories {
    jcenter()
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
            executable {

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
