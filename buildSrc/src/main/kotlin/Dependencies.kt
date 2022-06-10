object Dependencies {

    private const val NAV_VERSION = "2.4.2"
    private const val RETROFIT_VERSION = "2.9.0"
    private const val MOSHI_VERSION = "1.13.0"

    object AndroidX {
        const val CORE_KTX = "androidx.core:core-ktx:1.8.0"
        const val APP_COMPAT = "androidx.appcompat:appcompat:1.4.2"
        const val NAV_FRAGMENT_KTX = "androidx.navigation:navigation-fragment-ktx:$NAV_VERSION"
        const val NAV_UI_KTX = "androidx.navigation:navigation-ui-ktx:$NAV_VERSION"
    }

    object UI {
        const val MATERIAL_DESIGN = "com.google.android.material:material:1.6.1"
    }

    object DI {
        const val KOIN = "io.insert-koin:koin-android:3.2.0"
    }

    object DataSource {
        const val RETROFIT = "com.squareup.retrofit2:retrofit:$RETROFIT_VERSION"
        const val RETROFIT_CONVERTER = "com.squareup.retrofit2:converter-moshi:$RETROFIT_VERSION"
        const val MOSHI = "com.squareup.moshi:moshi:$MOSHI_VERSION"
        const val MOSHI_KOTLIN = "com.squareup.moshi:moshi-kotlin:$MOSHI_VERSION"
        const val MOSHI_KOTLIN_CODEGEN = "com.squareup.moshi:moshi-kotlin-codegen:$MOSHI_VERSION"
    }

    object UnitTest {
        const val JUNIT = "junit:junit:4.13.2"
        const val ROBOLECTRIC = "org.robolectric:robolectric:4.8.1"
        const val CORE_TESTING = "androidx.arch.core:core-testing:2.1.0"
        const val MOCKK = "io.mockk:mockk:1.12.3"
    }

    object Plugin {
        const val GRADLE = "com.android.tools.build:gradle:7.0.4"
        const val KOTLIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10"
        const val NAVIGATION = "androidx.navigation:navigation-safe-args-gradle-plugin:$NAV_VERSION"
    }
}
