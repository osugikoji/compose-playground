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
}

dependencies {
    implementation(Dependencies.DI.KOIN)
    api(Dependencies.DataSource.RETROFIT)
    api(Dependencies.DataSource.RETROFIT_CONVERTER)
    api(Dependencies.DataSource.MOSHI)
    api(Dependencies.DataSource.MOSHI_KOTLIN)
    api(Dependencies.DataSource.MOSHI_KOTLIN_CODEGEN)
}
