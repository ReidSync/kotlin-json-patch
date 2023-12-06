plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)
    id("io.github.gradle-nexus.publish-plugin").version("1.1.0")
}

buildscript {
    dependencies {
        this.classpath("io.github.gradle-nexus:publish-plugin:1.1.0")
    }
}

apply {
    this.from("scripts/publish-root.gradle")
}