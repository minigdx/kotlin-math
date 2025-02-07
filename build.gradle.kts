@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.minigdx.mpp)
}

repositories {
    mavenCentral()
}

dependencies {
    this.jvmTestImplementation(libs.libgdx.backend)
    this.jvmTestImplementation(variantOf(libs.libgdx.platform) { classifier("natives-desktop") })
    this.jvmTestImplementation(libs.assertJ)
}

minigdxDeveloper {
    this.name.set("kotlin-math")
    this.description.set("Set of Kotlin APIs to make graphics math easier to write.")
    this.projectUrl.set("https://github.com/minigdx/kotlin-math")
    this.licence {
        name.set("Apache License 2.0")
        url.set("https://github.com/minigdx/kotlin-math/blob/master/LICENSE")
    }
    developer {
        name.set("David Wursteisen")
        email.set("david.wursteisen+minigdx@gmail.com")
        url.set("https://github.com/dwursteisen")
    }

    developer {
        name.set("Romain Guy")
        email.set("romainguy@curious-creature.com")
        url.set("https://github.com/romainguy")
    }

    developer {
        name.set("Denis Konoplev")
        email.set("dekonoplyov@gmail.com")
    }

    developer {
        name.set("G0BL1N")
        email.set("piraka282@gmail.com")
    }

    developer {
        name.set("shiraji")
        email.set("isogai.shiraji@gmail.com")
    }

    developer {
        name.set("Kyle Nordbo")
        email.set("knordbo@atlassian.com")
    }

    developer {
        name.set("Ryan Harter")
        email.set("ryanjharter@gmail.com")
    }

    developer {
        name.set("Ravindra Kumar")
        email.set("ravindrakumar8088@gmail.com")
    }
}
