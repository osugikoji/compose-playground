object Dependencies {

    private const val NAV_VERSION = "2.4.2"
    private const val RETROFIT_VERSION = "2.9.0"
    private const val MOSHI_VERSION = "1.13.0"
    private const val KOIN_VERSION = "3.2.0"
    private const val ESPRESSO_VERSION = "3.4.0"
    const val COMPOSE_VERSION = "1.1.1"
    const val DETEKT_VERSION = "1.20.0"

    object AndroidX {
        const val CORE_KTX = "androidx.core:core-ktx:1.8.0"
        const val APP_COMPAT = "androidx.appcompat:appcompat:1.4.2"
        const val NAV_FRAGMENT_KTX = "androidx.navigation:navigation-fragment-ktx:$NAV_VERSION"
        const val NAV_UI_KTX = "androidx.navigation:navigation-ui-ktx:$NAV_VERSION"
        const val NAV_COMPOSABLE = "androidx.navigation:navigation-compose:$NAV_VERSION"
        const val ESPRESSO_IDLING = "androidx.test.espresso:espresso-idling-resource:$ESPRESSO_VERSION"
        const val LIFECYCLE_RUNTIME_KTX = "androidx.lifecycle:lifecycle-runtime-ktx:2.4.1"
    }

    object UI {
        const val MATERIAL_DESIGN = "com.google.android.material:material:1.6.1"
        const val COMPOSE_ACTIVITY = "androidx.activity:activity-compose:$COMPOSE_VERSION"
        const val COMPOSE_UI = "androidx.compose.ui:ui:$COMPOSE_VERSION"
        const val COMPOSE_UI_TOOLING = "androidx.compose.ui:ui-tooling:$COMPOSE_VERSION"
        const val COMPOSE_FOUNDATION = "androidx.compose.foundation:foundation:$COMPOSE_VERSION"
        const val COMPOSE_MATERIAL = "androidx.compose.material:material:$COMPOSE_VERSION"
    }

    object DI {
        const val KOIN = "io.insert-koin:koin-android:$KOIN_VERSION"
        const val KOIN_COMPOSE = "io.insert-koin:koin-androidx-compose:$KOIN_VERSION"
    }

    object DataSource {
        const val RETROFIT = "com.squareup.retrofit2:retrofit:$RETROFIT_VERSION"
        const val RETROFIT_CONVERTER = "com.squareup.retrofit2:converter-moshi:$RETROFIT_VERSION"
        const val RETRO_MOCK = "co.infinum:retromock:1.1.1"
        const val MOSHI = "com.squareup.moshi:moshi:$MOSHI_VERSION"
        const val MOSHI_KOTLIN = "com.squareup.moshi:moshi-kotlin:$MOSHI_VERSION"
        const val MOSHI_KOTLIN_CODEGEN = "com.squareup.moshi:moshi-kotlin-codegen:$MOSHI_VERSION"
    }

    object UnitTest {
        const val KOIN = "io.insert-koin:koin-test:$KOIN_VERSION"
        const val JUNIT = "junit:junit:4.13.2"
        const val ROBOLECTRIC = "org.robolectric:robolectric:4.8.1"
        const val CORE_KTX = "androidx.test:core-ktx:1.4.0"
        const val CORE_TESTING = "androidx.arch.core:core-testing:2.1.0"
        const val ANDROID_EXT_JUNIT = "androidx.test.ext:junit-ktx:1.1.3"
        const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:$ESPRESSO_VERSION"
        const val ESPRESSO_INTENTS = "androidx.test.espresso:espresso-intents:$ESPRESSO_VERSION"
        const val MOCKK = "io.mockk:mockk:1.12.3"
        const val MOCK_WEB_SERVER = "com.squareup.okhttp3:mockwebserver:4.9.3"
        const val COROUTINES_TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.2"
        const val COMPOSE_JUNIT = "androidx.compose.ui:ui-test-junit4:$COMPOSE_VERSION"
        const val COMPOSE_MANIFEST = "androidx.compose.ui:ui-test-manifest:$COMPOSE_VERSION"
    }

    object Classpath {
        const val GRADLE = "com.android.tools.build:gradle:7.2.1"
        const val KOTLIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10"
        const val NAVIGATION = "androidx.navigation:navigation-safe-args-gradle-plugin:$NAV_VERSION"
        const val DETEKT = "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:$DETEKT_VERSION"
        const val KTLINT = "org.jlleitschuh.gradle:ktlint-gradle:10.3.0"
        const val KOVER = "org.jetbrains.kotlinx:kover:0.5.0"
    }

    object Plugin {
        const val DETEKT = "io.gitlab.arturbosch.detekt"
        const val KTLINT = "org.jlleitschuh.gradle.ktlint"
    }
}
