package com.alightcreative.util.jsonpatch

import com.alightcreative.util.jsonpatch.utils.GsonObjectMapper
import com.alightcreative.util.jsonpatch.utils.IOUtils.toString
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import resources.testdata.TestData_DIFF
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

/*
* Copyright 2016 flipkart.com kjsonpatch.
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
*/ /**
 * @author ctranxuan (streamdata.io).
 */
class JsonDiffTest2 {
//    @org.junit.Test
//    @Throws(java.lang.Exception::class)
    @Test
    fun testPatchAppliedCleanly() {
        for (i in 0 until jsonNode.size) {
            val first: JsonElement = jsonNode.get(i).jsonObject.get("first")!!
            val second: JsonElement = jsonNode.get(i).jsonObject.get("second")!!
            val patch: JsonArray = jsonNode.get(i).jsonObject.get("patch")!!.jsonArray
            val message: String = jsonNode.get(i).jsonObject.get("message").toString()
            println("Test # $i")
            println(first)
            println(second)
            println(patch)
            val secondPrime: JsonElement = JsonPatch.apply(patch, first)
            println(secondPrime)
            assertEquals(secondPrime, second, message)
//            assertEquals(
//                message,
//                secondPrime,
//                org.hamcrest.core.IsEqual.equalTo<JsonElement>(second)
//            )
        }
    }

    companion object {
        var objectMapper = GsonObjectMapper()
        lateinit var jsonNode: JsonArray
//        @org.junit.BeforeClass
//        @Throws(java.io.IOException::class)
        @BeforeTest
        fun beforeClass() {
//            val path = "/testdata/diff.kt"
//            val resourceAsStream: java.io.InputStream =
//                JsonDiffTest2::class.java.getResourceAsStream(path)
//            val testData = toString(resourceAsStream, "UTF-8")
            jsonNode = objectMapper.readTree(TestData_DIFF).jsonArray
        }
    }
}