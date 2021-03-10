plugins {
    id("com.github.minigdx.gradle.plugin.developer") version "1.0-SNAPSHOT"
}

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    this.jvmTestImplementation("com.badlogicgames.gdx:gdx-backend-lwjgl:1.9.9")
    this.jvmTestImplementation("com.badlogicgames.gdx:gdx-platform:1.9.9:natives-desktop")
    this.jvmTestImplementation("org.assertj:assertj-core:3.11.1")
}
