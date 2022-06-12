plugins {
    id(Dependencies.Plugin.DETEKT) version Dependencies.DETEKT_VERSION
}

buildscript {
    repositories {
        mavenLocal()
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Dependencies.Classpath.GRADLE)
        classpath(Dependencies.Classpath.KOTLIN)
        classpath(Dependencies.Classpath.NAVIGATION)
        classpath(Dependencies.Classpath.DETEKT)
        classpath(Dependencies.Classpath.KTLINT)
        classpath(Dependencies.Classpath.KOVER)
    }
}

allprojects {
    repositories {
        mavenLocal()
        google()
        mavenCentral()
    }
}

subprojects {
    apply(plugin = Dependencies.Plugin.DETEKT)
    apply(plugin = Dependencies.Plugin.KTLINT)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
