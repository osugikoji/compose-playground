plugins {
    id("com.android.library")
    id("kotlin-android")
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
}
