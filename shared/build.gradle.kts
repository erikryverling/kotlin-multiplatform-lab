import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.android.library)
    alias(libs.plugins.skie)
    alias(libs.plugins.ksp)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_17
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)

            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.contentNegotiation)
            implementation(libs.ktor.serialization.kotlinx.json)

            implementation(libs.kotlinInject.runtime)
        }

        androidMain.dependencies {
            implementation(libs.ktor.client.android)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }
}

android {
    namespace = "se.yverling.lab.kmp.shared"
    compileSdk = Versions.compileSdk

    defaultConfig {
        minSdk = Versions.minSdk

        val properties = Properties()
        properties.load(rootProject.file("local.properties").inputStream())
        val openWeatherMapApiKey = properties.getProperty("openWeatherMapApiKey")

        buildConfigField("String", "openWeatherMapApiKey", openWeatherMapApiKey)
    }

    buildFeatures {
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    //   kspAndroid("me.tatarka.inject:kotlin-inject-compiler-ksp:0.7.2")
//    kspIosX64("me.tatarka.inject:kotlin-inject-compiler-ksp:0.7.2")
//    kspIosArm64("me.tatarka.inject:kotlin-inject-compiler-ksp:0.7.2")
//    kspIosArm64("me.tatarka.inject:kotlin-inject-compiler-ksp:0.7.2")

    // KSP will eventually have better multiplatform support and we'll be able to simply have
    // `ksp libs.kotlinInject.compiler` in the dependencies block of each source set
    // https://github.com/google/ksp/pull/1021
    add("kspAndroid", libs.kotlinInject.compiler)
    add("kspIosX64", libs.kotlinInject.compiler)
    add("kspIosArm64", libs.kotlinInject.compiler)
    add("kspIosSimulatorArm64", libs.kotlinInject.compiler)
}
