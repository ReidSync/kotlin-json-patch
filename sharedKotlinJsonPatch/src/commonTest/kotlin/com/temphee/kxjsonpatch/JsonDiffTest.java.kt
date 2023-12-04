/*
 * Copyright 2016 flipkart.com zjsonpatch.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/
package com.temphee.kxjsonpatch

import com.temphee.kxjsonpatch.utils.GsonObjectMapper
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import resources.testdata.TestData_SAMPLE
import kotlin.jvm.JvmStatic
import kotlin.random.Random
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Unit test
 */
class JsonDiffTest {
    var objectMapper = GsonObjectMapper()
    lateinit var jsonNode: JsonArray
    @BeforeTest
    fun setUp() {
        jsonNode = objectMapper.readTree(TestData_SAMPLE).jsonArray
    }

    //    @org.junit.Test
//    @Throws(java.lang.Exception::class)
    @Test
    fun testSampleJsonDiff() {
        for (i in 0 until jsonNode.size) {
            val first: JsonElement = jsonNode.get(i).jsonObject.get("first")!!
            val second: JsonElement = jsonNode.get(i).jsonObject.get("second")!!
            println("Test # $i")
            println(first)
            println(second)
            val actualPatch: JsonElement = JsonDiff.asJson(first, second)
            println(actualPatch)
            val secondPrime: JsonElement = JsonPatch.apply(actualPatch, first)
            println(secondPrime)
            assertEquals(second, secondPrime)
        }
    }

//    @org.junit.Test
//    @Throws(java.lang.Exception::class)
    @Test
    fun testGeneratedJsonDiff() {
        //val random: java.util.Random = java.util.Random()
        for (i in 0..999) {
            val first: JsonElement = TestDataGenerator.generate((0..10).random())
            val second: JsonElement = TestDataGenerator.generate((0..10).random())
            val actualPatch: JsonElement = JsonDiff.asJson(first, second)
            println("Test # $i")
            println(first)
            println(second)
            println(actualPatch)
            val secondPrime: JsonElement = JsonPatch.apply(actualPatch, first)
            println(secondPrime)
            assertEquals(second, secondPrime)
        }
    }

//    companion object {
//        var objectMapper = GsonObjectMapper()
//        var jsonNode: JsonArray = objectMapper.readTree(TestData_SAMPLE).jsonArray
//        //lateinit var jsonNode: JsonArray
//        //@org.junit.BeforeClass
//        //@Throws(java.io.IOException::class)
//        @BeforeTest
//        fun beforeClass() {
////            val path = "/testdata/sample.json"
////            val resourceAsStream: java.io.InputStream =
////                JsonDiffTest::class.java.getResourceAsStream(path)
//            val testData = TestData_SAMPLE
//            jsonNode = objectMapper.readTree(testData).jsonArray
//        }
//    }
}