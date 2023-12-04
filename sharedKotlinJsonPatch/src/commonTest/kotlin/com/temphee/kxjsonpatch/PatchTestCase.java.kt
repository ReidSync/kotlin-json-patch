package com.temphee.kxjsonpatch

import com.temphee.kxjsonpatch.utils.GsonObjectMapper
import com.temphee.kxjsonpatch.utils.IOUtils.toString
import kotlinx.serialization.json.*

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
*/
class PatchTestCase private constructor(
    val isOperation: Boolean,
    node: JsonObject
) {
    private val node: JsonObject
    fun getNode(): JsonObject {
        return node
    }

    init {
        this.node = node
    }

    companion object {
        private val MAPPER = GsonObjectMapper()
        //fun load(fileName: String): Collection<PatchTestCase> {
        fun load(testData: String): Collection<PatchTestCase> {
//            val path = "/testdata/$fileName.json"
//            val resourceAsStream: java.io.InputStream =
//                PatchTestCase::class.java.getResourceAsStream(path)
//            val testData = toString(resourceAsStream, "UTF-8")
            val tree: JsonElement = MAPPER.readTree(testData)
            val result: MutableList<PatchTestCase> = ArrayList<PatchTestCase>()
            for (node in tree.jsonObject.get("errors")!!.jsonArray) {
                if (isEnabled(node)) {
                    result.add(PatchTestCase(false, node.jsonObject))
                }
            }
            for (node in tree.jsonObject.get("ops")!!.jsonArray) {
                if (isEnabled(node)) {
                    result.add(PatchTestCase(true, node.jsonObject))
                }
            }
            return result
        }

        private fun isEnabled(node: JsonElement): Boolean {
            val disabled: JsonElement = node.jsonObject.get("disabled")!!
            return !disabled.jsonPrimitive.boolean
        }
    }
}