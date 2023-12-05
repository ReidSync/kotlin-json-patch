# kxjsonpatch
## Kotlin JSON Patching Library

### This is an implementation of RFC 6902 JSON Patch written exclusively in Kotlin.
It is based on the [Apache 2.0](http://www.apache.org/licenses/LICENSE-2.0) licensed library from Flipkart, [zjsonpatch](https://github.com/flipkart-incubator/zjsonpatch).
This project is a fork of [KJsonPatch](https://github.com/beyondeye/kjsonpatch) (with the [latest commit referenced](https://github.com/beyondeye/kjsonpatch/commit/939455832a09de666d9578963676996b5e09b6be)).

**It can be used on Kotlin Multiplatform.**

## Changes

This code has been modified from the original library in the following ways:
* Ported from Java to Kotlin
* Changed package names
* Removed Gson dependency (replaced with [kotlinx.serialization.json](https://kotlinlang.org/api/latest/kotlin.test/))
* Added extensions for convenient usage of `kotlinx.serialization.json`

## API Usage
The variables `source`, `target`, and `patch` below must be asserted as valid `JsonElement` objects. 
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

## Test
You can run unit tests the same way as zjsonpatch and KJsonPatch.

## ______
These changes mostly involve porting from Java to Kotlin to transform it into a pure Kotlin library that can be imported into Kotlin Multiplatform. If you have any specific preferences or further adjustments, feel free to let me know!
