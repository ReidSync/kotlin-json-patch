# [kotlin-json-patch]
[![CircleCI](https://dl.circleci.com/status-badge/img/circleci/FX4uQvXfdGbsC2LtLwAcHN/6WSq31hZUQd6ntmfk7zYqZ/tree/main.svg?style=svg&circle-token=726a635dd6621f418b6a9b009e03e14aff877c5b)](https://dl.circleci.com/status-badge/redirect/circleci/FX4uQvXfdGbsC2LtLwAcHN/6WSq31hZUQd6ntmfk7zYqZ/tree/main)
[![CircleCI](https://dl.circleci.com/status-badge/img/circleci/FX4uQvXfdGbsC2LtLwAcHN/6WSq31hZUQd6ntmfk7zYqZ/tree/main.svg?style=shield&circle-token=726a635dd6621f418b6a9b009e03e14aff877c5b)](https://dl.circleci.com/status-badge/redirect/circleci/FX4uQvXfdGbsC2LtLwAcHN/6WSq31hZUQd6ntmfk7zYqZ/tree/main)
[![Kotlin](https://img.shields.io/badge/kotlin-1.9.20-white.svg?logo=kotlin&color=6A5ACD)](http://kotlinlang.org/)
[![Apache License](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?logo=apache)](https://www.apache.org/licenses/LICENSE-2.0.txt)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.reidsync/kotlin-json-patch?logo=sonatype&logoColor=D2691E&color=D2691E)](https://central.sonatype.com/artifact/io.github.reidsync/kotlin-json-patch/overview)

![badge-support-kotlin-multiplatform]
![badge-support-android-native]
![badge-support-apple-silicon]
<a href="https://android-arsenal.com/api?level=24"><img alt="API" src="https://img.shields.io/badge/API-24%2B-brightgreen.svg?style=flat"/></a>  
![badge-platform-android]
![badge-platform-ios]
![badge-platform-jvm]
![badge-platform-js]

## Kotlin JSON Patching Library

### This is an implementation of [RFC 6902 JSON Patch](https://datatracker.ietf.org/doc/html/rfc6902) written exclusively in Kotlin.
It is based on the [Apache 2.0](http://www.apache.org/licenses/LICENSE-2.0) licensed library from Flipkart, [zjsonpatch](https://github.com/flipkart-incubator/zjsonpatch).  
This project is a fork of [KJsonPatch](https://github.com/beyondeye/kjsonpatch) (with the [latest commit referenced](https://github.com/beyondeye/kjsonpatch/commit/939455832a09de666d9578963676996b5e09b6be)).

## Changes

This code has been modified from the original library in the following ways:
* Ported from Java to Kotlin
* Changed package names
* Substituted Gson dependency with [`kotlinx.serialization.json`](https://kotlinlang.org/api/latest/kotlin.test/)
* Added extensions for convenient usage of [`kotlinx.serialization.json`](https://kotlinlang.org/api/latest/kotlin.test/)

## Setup
Add the dependency to your app module’s `build.gradle` file:

```kotlin
repositories {
    mavenCentral()
}

dependencies {
    // e.g., implementation("io.github.reidsync:kotlin-json-patch:1.0.0")
    implementation("io.github.reidsync:kotlin-json-patch:${kotliln_json_patch_version}")
}
```
> _**Check the [kotlin-json-patch versions](https://central.sonatype.com/artifact/io.github.reidsync/kotlin-json-patch/versions)**_  
latest version : [![Maven Central](https://img.shields.io/maven-central/v/io.github.reidsync/kotlin-json-patch)](https://central.sonatype.com/artifact/io.github.reidsync/kotlin-json-patch/overview)  

You can add the dependency to `sourceSets.commonMain.dependecies` for your Kotlin Multiplatform project.

```kotlin
kotlin {
    sourceSets {
        commonMain.dependencies {
            //put your multiplatform dependencies here
            implementation("io.github.reidsync:kotlin-json-patch:${kotliln_json_patch_version}")
        }
    }
}

```

## API Usage

> The variables `source`, `target`, and `patch` below must be asserted as valid `JsonElement` objects. 
### Generating JSON Diff as a patch
```kotlin
val diff: JsonArray = JsonDiff.asJson(source: JsonElement, target: JsonElement)
```
or
```kotlin
val diff: JsonElement = source.generatePatch(with: target)
```
### Applying JSON patch
```kotlin
val result: JsonElement = JsonPatch.apply(patch: JsonElement, source: JsonElement)
```
or
```kotlin
val result: JsonElement = source.apply(patch: patch)
```
This operation is performed on a clone of the source object.

## 
These changes mostly involve porting from Java to Kotlin to transform it into a pure Kotlin library that can be imported into Kotlin Multiplatform. If you have any specific preferences or further adjustments, feel free to let me know!

<!--
![badge-platform-js-node]
![badge-platform-linux]
![badge-platform-macos]
![badge-platform-tvos]
![badge-platform-watchos]
![badge-platform-wasm]
![badge-platform-windows]

![badge-support-js-ir]
![badge-support-linux-arm]
-->

<!-- TAG_PLATFORMS -->
[badge-platform-android]: https://img.shields.io/badge/-android-6EDB8D.svg?logo=android&&logoColor=white&style=flat
[badge-platform-jvm]: https://img.shields.io/badge/-jvm-DB413D.svg?logo=jvm&logoColor=white&style=flat
[badge-platform-js]: https://img.shields.io/badge/-js-F8DB5D.svg?logo=JavaScript&logoColor=white&style=flat
[badge-platform-js-node]: https://img.shields.io/badge/-nodejs-68a063.svg?logo=nodedotjs&logoColor=white&style=flat
[badge-platform-linux]: https://img.shields.io/badge/-linux-2D3F6C.svg?logo=linux&logoColor=white&style=flat
[badge-platform-macos]: https://img.shields.io/badge/-macos-111111.svg?logo=macOS&logoColor=white&style=flat
[badge-platform-ios]: https://img.shields.io/badge/-ios-CDCDCD.svg?logo=iOS&logoColor=white&style=flat
[badge-platform-tvos]: https://img.shields.io/badge/-tvos-808080.svg?logo=AppleTV&logoColor=white&style=flat
[badge-platform-watchos]: https://img.shields.io/badge/-watchos-C0C0C0.svg?logo=Apple&logoColor=white&style=flat
[badge-platform-wasm]: https://img.shields.io/badge/-wasm-624FE8.svg?logo=webassembly&logoColor=white&style=flat
[badge-platform-windows]: https://img.shields.io/badge/-windows-4D76CD.svg?logo=Windows&logoColor=whitestyle=flat
[badge-support-android-native]: https://img.shields.io/badge/support-Android%20Native-6EDB8D.svg?style=flat?fontColor=white
[badge-support-apple-silicon]: https://img.shields.io/badge/support-Apple%20Silicon-808080.svg?style=flat
[badge-support-kotlin-multiplatform]: https://img.shields.io/badge/support-Kotlin%20Multiplatform-6A5ACD.svg?style=flat
[badge-support-js-ir]: https://img.shields.io/badge/support-[js--IR]-AAC4E0.svg?style=flat
[badge-support-linux-arm]: https://img.shields.io/badge/support-[LinuxArm]-2D3F6C.svg?style=flat

