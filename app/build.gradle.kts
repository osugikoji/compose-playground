import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    compileSdk = Config.COMPILE_SDK_VERSION

    defaultConfig {
        applicationId = "com.playground.sample"
        minSdk = Config.MIN_SDK_VERSION
        targetSdk = Config.TARGET_SDK_VERSION
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        val key = "OPEN_EXCHANGE_APP_ID"
        val appId = System.getenv().getOrDefault(key, gradleLocalProperties(rootDir).getProperty(key))
        buildConfigField("String", "APP_ID", "\"$appId\"")
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
}

dependencies {
    implementation(Dependencies.AndroidX.CORE_KTX)
    implementation(Dependencies.AndroidX.APP_COMPAT)
    implementation(Dependencies.UI.MATERIAL_DESIGN)
    implementation(project(":transaction"))
    implementation(project(":transaction-compose"))
}
