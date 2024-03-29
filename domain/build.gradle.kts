plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kover")
}

android {
    compileSdk = Config.COMPILE_SDK_VERSION

    defaultConfig {
        minSdk = Config.MIN_SDK_VERSION
        targetSdk = Config.TARGET_SDK_VERSION

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
    }
}

dependencies {
    implementation(Dependencies.DI.KOIN)
    implementation(project(":core"))
    implementation(project(":data"))

    testImplementation(Dependencies.UnitTest.CORE_KTX)
    testImplementation(Dependencies.UnitTest.ROBOLECTRIC)
    testImplementation(Dependencies.UnitTest.JUNIT)
    testImplementation(Dependencies.UnitTest.MOCKK)
    testImplementation(Dependencies.UnitTest.KOIN)
}

tasks.koverVerify {
    rule {
        name = "Minimal line coverage rate in percents"
        bound { minValue = 90 }
    }
}
