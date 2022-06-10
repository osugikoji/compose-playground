buildscript {
    repositories {
        mavenLocal()
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Dependencies.Plugin.GRADLE)
        classpath(Dependencies.Plugin.KOTLIN)
        classpath(Dependencies.Plugin.NAVIGATION)
    }
}

allprojects {
    repositories {
        mavenLocal()
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
