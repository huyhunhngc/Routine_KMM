object Deps {
    // VERSION
    private const val materialVersion = "1.9.0"
    private const val activityComposeVersion = "1.6.1"
    const val composeVersion = "1.4.0-alpha02"
    const val compose3Version = "1.1.0-alpha07"
    const val composeAnimationVersion = "1.4.3"
    private const val composeNavigationVersion = "2.7.1"
    private const val coilComposeVersion = "2.1.0"
    private const val systemUIControllerVersion = "0.30.1"
    private const val navigationAnimationVersion = "0.28.0"
    private const val dateTimeVersion = "0.4.0"
    const val hiltVersion = "2.44"
    private const val hiltCompilerVersion = "1.0.0"
    private const val ktorVersion = "2.1.3"
    private const val gradleVersion = "7.2.2"
    private const val sqlDelightGradleVersion = "1.5.5"
    private const val sqlDelightVersion = "1.5.5"
    private const val assertKVersion = "0.25"
    private const val turbineVersion = "0.7.0"
    private const val jUnitVersion = "4.13.2"
    private const val testRunnerVersion = "1.5.1"
    private const val rulesVersion = "1.5.0"
    private const val mokoResourceVersion = "0.22.0"
    const val dataStoreVersion = "1.1.0-alpha04"
    const val kotlinVersion = "1.8.20"

    // MATERIAL
    const val material = "com.google.android.material:material:$materialVersion"

    // COMPOSE
    const val activityCompose = "androidx.activity:activity-compose:$activityComposeVersion"
    const val composeUi = "androidx.compose.ui:ui:$composeVersion"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling:$composeVersion"
    const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview:$composeVersion"
    const val composeFoundation = "androidx.compose.foundation:foundation:$composeVersion"
    const val composeMaterial = "androidx.compose.material:material:$composeVersion"
    const val compose3Material = "androidx.compose.material3:material3:$compose3Version"
    const val composeIconsExtended = "androidx.compose.material:material-icons-extended:$composeVersion"
    const val composeAnimation = "androidx.compose.animation:animation:$composeAnimationVersion"
    const val composeAnimationGraphics = "androidx.compose.animation:animation-graphics:$composeAnimationVersion"
    const val composeNavigation = "androidx.navigation:navigation-compose:$composeNavigationVersion"
    const val coilCompose = "io.coil-kt:coil-compose:$coilComposeVersion"
    const val systemUIController = "com.google.accompanist:accompanist-systemuicontroller:$systemUIControllerVersion"
    const val navigationAnimation ="com.google.accompanist:accompanist-navigation-animation:$navigationAnimationVersion"

    // KOTLIN DATE TIME
    const val kotlinDateTime = "org.jetbrains.kotlinx:kotlinx-datetime:$dateTimeVersion"

    // HILT
    const val hiltAndroid = "com.google.dagger:hilt-android:$hiltVersion"
    const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:$hiltVersion"
    const val hiltCompiler = "androidx.hilt:hilt-compiler:$hiltCompilerVersion"
    const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:$hiltCompilerVersion"

    // KTOR
    const val ktorCore = "io.ktor:ktor-client-core:$ktorVersion"
    const val ktorSerialization = "io.ktor:ktor-client-content-negotiation:$ktorVersion"
    const val ktorSerializationJson = "io.ktor:ktor-serialization-kotlinx-json:$ktorVersion"
    const val ktorAndroid = "io.ktor:ktor-client-android:$ktorVersion"
    const val ktorIOS = "io.ktor:ktor-client-ios:$ktorVersion"

    const val androidBuildTools = "com.android.tools.build:gradle:$gradleVersion"

    // SQLDELIGHT
    const val sqlDelightRuntime = "com.squareup.sqldelight:runtime:$sqlDelightVersion"
    const val sqlDelightAndroidDriver = "com.squareup.sqldelight:android-driver:$sqlDelightVersion"
    const val sqlDelightNativeDriver = "com.squareup.sqldelight:native-driver:$sqlDelightVersion"
    const val sqlDelightCoroutinesExtensions = "com.squareup.sqldelight:coroutines-extensions:$sqlDelightVersion"

    // TESTING

    const val assertK = "com.willowtreeapps.assertk:assertk:$assertKVersion"
    const val turbine = "app.cash.turbine:turbine:$turbineVersion"
    const val jUnit = "junit:junit:$jUnitVersion"
    const val testRunner = "androidx.test:runner:$testRunnerVersion"
    const val composeTesting = "androidx.compose.ui:ui-test-junit4:$composeVersion"
    const val composeTestManifest = "androidx.compose.ui:ui-test-manifest:$composeVersion"
    const val hiltTesting = "com.google.dagger:hilt-android-testing:$hiltVersion"
    const val rules = "androidx.test:rules:$rulesVersion"

    // MOKO resoures for KMM
    const val mokoResource = "dev.icerock.moko:resources:$mokoResourceVersion"
    const val mokoResourceCompose = "dev.icerock.moko:resources-compose:$mokoResourceVersion"
    const val pluginMoko = "dev.icerock.mobile.multiplatform-resources"

    // DataStore
    const val dataStore = "androidx.datastore:datastore-preferences:$dataStoreVersion"
    const val dataStoreCore = "androidx.datastore:datastore-preferences-core:$dataStoreVersion"

    // PLUGINS
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val sqlDelightGradlePlugin = "com.squareup.sqldelight:gradle-plugin:$sqlDelightGradleVersion"
    const val hiltGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$hiltVersion"
    const val squareupDelight = "com.squareup.sqldelight"

}