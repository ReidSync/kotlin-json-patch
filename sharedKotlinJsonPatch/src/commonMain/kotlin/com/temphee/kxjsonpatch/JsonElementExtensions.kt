package com.temphee.kxjsonpatch

import kotlinx.serialization.json.*

/**
 * Created by Reid Byun on 2023/08/07.
 * Copyright (c) 2023 Bending Spoons. All rights reserved.
 */

/*
*  JsonElement Extensions
* */

fun JsonElement.apply(patch: JsonElement): JsonElement {
	return JsonPatch.apply(patch, this)
}

fun JsonElement.generatePatch(with: JsonElement): JsonElement {
	return JsonDiff.asJson(this, with)
}

internal fun JsonElement.isContainerNode(): Boolean {
	return this is JsonArray || this is JsonObject
}

internal fun JsonElement.deepCopy(): JsonElement {
	return when(this) {
		is JsonArray -> this.jsonArray.copy {}
		is JsonObject -> this.jsonObject.copy {}
		is JsonNull -> JsonNull // An order of checking type between JsonNull and JsonPrimitive makes difference.
		is JsonPrimitive -> this /* Todo check */
		else -> this /* Todo check */
	}
}

/*
* JsonArray Extensions
* */
internal fun JsonArray.add(value_: JsonElement?): JsonArray {
	val value=value_ ?: JsonNull
	return copy { add(value) }
}

internal fun JsonArray.insert(index: Int, value_: JsonElement?): JsonArray {
	val value=value_ ?: JsonNull
	return if(index>=size) {
		this.add(value)
	}
	else if(index<0) {
		this.copy { add(0, value)}
	}
	else {
		this.copy { add(index, value) }
	}
}

internal fun JsonArray.set(index: Int, value_: JsonElement?): JsonArray {
	val value=value_ ?: JsonNull
	if(index>=size) {
		throw IndexOutOfBoundsException("")
	}
	return copy { this[index] = value }
}

internal fun JsonArray.remove(index:Int): JsonArray {
	return copy { removeAt(index) }
}

private inline fun JsonArray.copy(mutatorBlock: MutableList<JsonElement>.() -> Unit): JsonArray {
	return JsonArray(this.toMutableList().apply(mutatorBlock))
}


/*
* JsonObject Extensions
* */
internal fun JsonObject.add(key: String, value_: JsonElement?): JsonObject {
	val value=value_ ?: JsonNull
	return copy {
		this[key] = value
	}
}

internal fun JsonObject.remove(key: String): JsonObject {
	return copy { remove(key) }
}

internal fun JsonObject.set(key: String, value_: JsonElement?): JsonObject {
	val value=value_ ?: JsonNull
	if(!this.containsKey(key)) {
		throw IndexOutOfBoundsException("Key[$key] doesn't exist")
	}
	return copy {
		this[key] = value
	}
}

internal fun JsonObject.addProperty(key: String, value: String): JsonObject {
	return this.copy {
		this[key] = JsonPrimitive(value)
	}
}

internal fun JsonObject.addProperty(key: String, value: Number): JsonObject {
	return this.copy {
		this[key] = JsonPrimitive(value)
	}
}

private inline fun JsonObject.copy(mutatorBlock: MutableMap<String, JsonElement>.() -> Unit): JsonObject {
	return JsonObject(this.toMutableMap().apply(mutatorBlock))
}