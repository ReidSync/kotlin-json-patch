plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    //`maven-publish`
    id("convention.publication")
}

//repositories {
//    gradlePluginPortal() // To use 'maven-publish' and 'signing' plugins in our own plugin
//}

//apply(from = "${rootProject.file("convention-plugins/src/main/kotlin/convention.publication.gradle.kts")}")
//apply(plugin = "${rootProject.file("convention-plugins")}")
group = "io.github.reidsync"
version = "0.0.2"

kotlin {
    jvm {
        // …
    }
    js(IR) {
        // …
    }
    ios()
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
        publishLibraryVariants("release", "debug")
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "sharedKotlinJsonPatch"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            //put your multiplatform dependencies here
            implementation(libs.kotlinx.serialization.json)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.reidsync.kxjsonpatch"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}