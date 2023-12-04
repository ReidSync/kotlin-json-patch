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
import kotlinx.serialization.json.JsonElement
import resources.testdata.TestData_MOVE
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * @author ctranxuan (streamdata.io).
 */
class MoveOperationTest : AbstractTest() {
//    @org.junit.Test
//    @Throws(java.io.IOException::class)
    @Test
    fun testMoveValueGeneratedHasNoValue() {
        val jsonNode1: JsonElement =
            MAPPER.readTree("{ \"foo\": { \"bar\": \"baz\", \"waldo\": \"fred\" }, \"qux\": { \"corge\": \"grault\" } }")
        val jsonNode2: JsonElement =
            MAPPER.readTree("{ \"foo\": { \"bar\": \"baz\" }, \"qux\": { \"corge\": \"grault\", \"thud\": \"fred\" } }")
        val patch: JsonElement =
            MAPPER.readTree("[{\"op\":\"move\",\"from\":\"/foo/waldo\",\"path\":\"/qux/thud\"}]")
        val diff: JsonElement = JsonDiff.asJson(jsonNode1, jsonNode2)
        assertEquals(diff, patch)
//        assertEquals(
//            diff,
//            org.hamcrest.CoreMatchers.equalTo<JsonElement>(patch)
//        )
    }

//    @org.junit.Test
//    @Throws(java.io.IOException::class)
    @Test
    fun testMoveArrayGeneratedHasNoValue() {
        val jsonNode1: JsonElement =
            MAPPER.readTree("{ \"foo\": [ \"all\", \"grass\", \"cows\", \"eat\" ] }")
        val jsonNode2: JsonElement =
            MAPPER.readTree("{ \"foo\": [ \"all\", \"cows\", \"eat\", \"grass\" ] }")
        val patch: JsonElement =
            MAPPER.readTree("[{\"op\":\"move\",\"from\":\"/foo/1\",\"path\":\"/foo/3\"}]")
        val diff: JsonElement = JsonDiff.asJson(jsonNode1, jsonNode2)
        assertEquals(diff, patch)
//        assertEquals(
//            diff,
//            org.hamcrest.CoreMatchers.equalTo<JsonElement>(patch)
//        )
    }

    //companion object {
        private val MAPPER = GsonObjectMapper()
        //@org.junit.runners.Parameterized.Parameters
        //@Throws(java.io.IOException::class)
        fun data(): Collection<PatchTestCase> {
            return PatchTestCase.load(TestData_MOVE)
        }
    //}
}