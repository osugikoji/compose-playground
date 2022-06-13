plugins {
    id("com.android.library")
    id("kotlin-android")
    id("androidx.navigation.safeargs.kotlin")
    id("kover")
}

android {
    compileSdk = Config.COMPILE_SDK_VERSION

    defaultConfig {
        minSdk = Config.MIN_SDK_VERSION
        targetSdk = Config.TARGET_SDK_VERSION

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFile("consumer-rules.pro")
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

    buildFeatures {
        viewBinding = true
    }

    testOptions {
        animationsDisabled = true
        unitTests.isIncludeAndroidResources = true
    }
}

dependencies {
    implementation(Dependencies.AndroidX.CORE_KTX)
    implementation(Dependencies.AndroidX.APP_COMPAT)
    implementation(Dependencies.AndroidX.NAV_FRAGMENT_KTX)
    implementation(Dependencies.AndroidX.NAV_UI_KTX)
    implementation(Dependencies.UI.MATERIAL_DESIGN)
    implementation(Dependencies.DI.KOIN)
    implementation(project(":core"))
    implementation(project(":domain"))

    testImplementation(Dependencies.UnitTest.ANDROID_EXT_JUNIT)
    testImplementation(Dependencies.UnitTest.ESPRESSO_CORE)
    testImplementation(Dependencies.UnitTest.ESPRESSO_INTENTS)
    testImplementation(Dependencies.UnitTest.JUNIT)
    testImplementation(Dependencies.UnitTest.ROBOLECTRIC)
    testImplementation(Dependencies.UnitTest.CORE_TESTING)
    testImplementation(Dependencies.UnitTest.MOCKK)
    testImplementation(Dependencies.UnitTest.COROUTINES_TEST)
    testImplementation(Dependencies.UnitTest.MOCK_WEB_SERVER)
}

tasks.koverVerify {
    rule {
        name = "Minimal line coverage rate in percents"
        bound { minValue = 80 }
    }
}
