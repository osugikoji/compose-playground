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

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.COMPOSE_VERSION
    }

    testOptions {
        animationsDisabled = true
        unitTests.isIncludeAndroidResources = true
    }
}

dependencies {
    implementation(Dependencies.AndroidX.APP_COMPAT)
    implementation(Dependencies.AndroidX.LIFECYCLE_RUNTIME_KTX)
    implementation(Dependencies.AndroidX.NAV_COMPOSABLE)
    implementation(Dependencies.DI.KOIN_COMPOSE)
    implementation(Dependencies.UI.COMPOSE_ACTIVITY)
    implementation(Dependencies.UI.COMPOSE_UI)
    implementation(Dependencies.UI.COMPOSE_UI_TOOLING)
    implementation(Dependencies.UI.COMPOSE_FOUNDATION)
    implementation(Dependencies.UI.COMPOSE_MATERIAL)
    implementation(project(":domain"))
    implementation(project(":core"))

    testImplementation(Dependencies.UnitTest.ESPRESSO_CORE)
    testImplementation(Dependencies.UnitTest.JUNIT)
    testImplementation(Dependencies.UnitTest.ROBOLECTRIC)
    testImplementation(Dependencies.UnitTest.CORE_TESTING)
    testImplementation(Dependencies.UnitTest.MOCKK)
    testImplementation(Dependencies.UnitTest.COROUTINES_TEST)
    testImplementation(Dependencies.UnitTest.MOCK_WEB_SERVER)
    testImplementation(Dependencies.UnitTest.COMPOSE_JUNIT)
    debugImplementation(Dependencies.UnitTest.COMPOSE_MANIFEST)
}

detekt {
    buildUponDefaultConfig = true
    config = files("$projectDir/detekt-compose.yml")
}

tasks.koverVerify {
    rule {
        name = "Minimal line coverage rate in percents"
        bound { minValue = 60 }
    }
}
