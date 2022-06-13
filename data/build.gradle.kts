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
}

dependencies {
    implementation(Dependencies.DI.KOIN)
    implementation(Dependencies.DataSource.RETROFIT)
    implementation(Dependencies.DataSource.RETROFIT_CONVERTER)
    implementation(Dependencies.DataSource.RETRO_MOCK)
    implementation(Dependencies.DataSource.MOSHI)
    implementation(Dependencies.DataSource.MOSHI_KOTLIN)
    implementation(Dependencies.DataSource.MOSHI_KOTLIN_CODEGEN)
    implementation(project(":core"))

    testImplementation(Dependencies.UnitTest.KOIN)
    testImplementation(Dependencies.UnitTest.JUNIT)
    testImplementation(Dependencies.UnitTest.MOCK_WEB_SERVER)
}

tasks.koverVerify {
    rule {
        name = "Minimal line coverage rate in percents"
        bound { minValue = 90 }
    }
}
