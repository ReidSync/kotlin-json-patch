import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmDefaultMode

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    id("maven-publish.conventions")
}

group = "io.github.reidsync"
version = "1.1.0"

kotlin {
	jvmToolchain(8)

	@OptIn(ExperimentalWasmDsl::class)
	wasmJs {
		browser()
		nodejs()
	}

	androidTarget {
		compilerOptions {
			jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_1_8)
			jvmDefault = JvmDefaultMode.DISABLE
	    }
        publishLibraryVariants("release", "debug")
    }

	jvm("desktop")
    
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
