package com.temphee.kxjsonpatch.utils

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull

class GsonObjectMapper {
    fun readTree(jsondata: String?): JsonElement {
        if (jsondata != null) {
            return Json.parseToJsonElement(jsondata)
        }
        else {
            return JsonNull
        }
    }
}