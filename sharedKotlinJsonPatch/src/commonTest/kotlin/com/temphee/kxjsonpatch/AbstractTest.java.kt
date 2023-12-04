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
package com.alightcreative.util.jsonpatch

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonArray
import kotlin.test.DefaultAsserter.fail
import kotlin.test.Test
import kotlin.test.assertEquals

abstract class AbstractTest {
    var p: PatchTestCase? = null

    @Test
    fun test() {
        if (p?.isOperation == true) {
            testOpertaion()
        } else {
            testError()
        }
    }

    @Test
    private fun testOpertaion() {
        val node: JsonObject = p?.getNode()!!
        val first: JsonElement = node.get("node")!!
        val second: JsonElement = node.get("expected")!!
        val patch: JsonElement = node.get("op")!!
        val message = if (node.containsKey("message")) node.get("message").toString() else ""
        val secondPrime: JsonElement =
            JsonPatch.apply(patch.jsonArray, first)
        assertEquals(secondPrime, second, message)
//        org.junit.Assert.assertThat<JsonElement>(
//            message,
//            secondPrime,
//            org.hamcrest.core.IsEqual.equalTo<JsonElement>(second)
//        )
    }

    private fun testError() {
        val node: JsonObject = p?.getNode()!!
        val first: JsonElement = node.get("node")!!
        val patch: JsonElement = node.get("op")!!
        try {
            JsonPatch.apply(patch.jsonArray, first)
            fail("Failure expected: " + node.get("message"))
        } catch (e: Exception) {
        }
    }
}