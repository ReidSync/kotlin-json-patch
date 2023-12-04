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
import kotlinx.serialization.json.jsonArray
import kotlin.test.Test
import kotlin.test.assertFailsWith

class ApiTest {
    @Test
    fun applyingNonArrayPatchShouldThrowAnException() {
        assertFailsWith<InvalidJsonPatchException> {
            val objectMapper = GsonObjectMapper()
            val invalid: JsonElement = objectMapper.readTree("[{\"not\": \"a patch\"}]")
            val to: JsonElement = objectMapper.readTree("{\"a\":1}")
            JsonPatch.apply(invalid.jsonArray, to)
        }
    }

    @Test
    fun applyingAnInvalidArrayShouldThrowAnException() {
        assertFailsWith<InvalidJsonPatchException> {
            val objectMapper = GsonObjectMapper()
            val invalid: JsonElement = objectMapper.readTree("[1, 2, 3, 4, 5]")
            val to: JsonElement = objectMapper.readTree("{\"a\":1}")
            JsonPatch.apply(invalid.jsonArray, to)
        }
    }

    @Test
    fun applyingAPatchWithAnInvalidOperationShouldThrowAnException() {
        assertFailsWith<InvalidJsonPatchException> {
            val objectMapper = GsonObjectMapper()
            val invalid: JsonElement = objectMapper.readTree("[{\"op\": \"what\"}]")
            val to: JsonElement = objectMapper.readTree("{\"a\":1}")
            JsonPatch.apply(invalid.jsonArray, to)
        }
    }

    @Test
    fun validatingNonArrayPatchShouldThrowAnException() {
        assertFailsWith<InvalidJsonPatchException> {
            val objectMapper = GsonObjectMapper()
            val invalid: JsonElement = objectMapper.readTree("{\"not\": \"a patch\"}")
            JsonPatch.validate(invalid)
        }
    }

    @Test
    fun validatingAnInvalidArrayShouldThrowAnException() {
        assertFailsWith<InvalidJsonPatchException> {
            val objectMapper = GsonObjectMapper()
            val invalid: JsonElement = objectMapper.readTree("[1, 2, 3, 4, 5]")
            JsonPatch.validate(invalid)
        }
    }

    @Test
    fun validatingAPatchWithAnInvalidOperationShouldThrowAnException() {
        assertFailsWith<InvalidJsonPatchException> {
            val objectMapper = GsonObjectMapper()
            val invalid: JsonElement = objectMapper.readTree("[{\"op\": \"what\"}]")
            JsonPatch.validate(invalid)
        }
    }
}