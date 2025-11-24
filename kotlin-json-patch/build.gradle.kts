plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    id("maven-publish.conventions")
}

group = "io.github.reidsync"
version = "1.0.0"

kotlin {
    jvm {
        // …
    }
    js(IR) {
        // …
    }
    androidTarget {
        compilations.all {
	        compilerOptions.configure {
		        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_1_8)
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
        minSdk = 23
    }
}
