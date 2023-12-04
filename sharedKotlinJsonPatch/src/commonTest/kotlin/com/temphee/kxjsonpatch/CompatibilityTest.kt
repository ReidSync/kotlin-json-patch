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
import kotlinx.serialization.json.JsonObject
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class CompatibilityTest {
    var mapper: GsonObjectMapper = GsonObjectMapper()
    var addNodeWithMissingValue: JsonElement = mapper.readTree("[{\"op\":\"add\",\"path\":\"a\"}]")
    var replaceNodeWithMissingValue: JsonElement = mapper.readTree("[{\"op\":\"replace\",\"path\":\"a\"}]")

    @BeforeTest
    fun setUp() {
        mapper = GsonObjectMapper()
        addNodeWithMissingValue = mapper.readTree("[{\"op\":\"add\",\"path\":\"a\"}]")
        replaceNodeWithMissingValue = mapper.readTree("[{\"op\":\"replace\",\"path\":\"a\"}]")
    }

    @Test
    fun withFlagAddShouldTreatMissingValuesAsNulls() {
        val expected: JsonElement = mapper.readTree("{\"a\":null}")
        val result: JsonElement = JsonPatch.apply(
            addNodeWithMissingValue,
            JsonObject(emptyMap()),
            setOf(CompatibilityFlags.MISSING_VALUES_AS_NULLS)
        )
        assertEquals(result, expected)
    }

    @Test
    fun withFlagAddNodeWithMissingValueShouldValidateCorrectly() {
        JsonPatch.validate(
            addNodeWithMissingValue,
            setOf(CompatibilityFlags.MISSING_VALUES_AS_NULLS)
        )
    }

    @Test
    fun withFlagReplaceShouldTreatMissingValuesAsNull() {
        val source: JsonElement = mapper.readTree("{\"a\":\"test\"}")
        val expected: JsonElement = mapper.readTree("{\"a\":null}")
        val result: JsonElement = JsonPatch.apply(
            replaceNodeWithMissingValue,
            source,
            setOf(CompatibilityFlags.MISSING_VALUES_AS_NULLS)
        )
        assertEquals(
            result,
            expected
        )
    }

    @Test
    fun withFlagReplaceNodeWithMissingValueShouldValidateCorrectly() {
        JsonPatch.validate(
            addNodeWithMissingValue,
            setOf(CompatibilityFlags.MISSING_VALUES_AS_NULLS)
        )
    }
}