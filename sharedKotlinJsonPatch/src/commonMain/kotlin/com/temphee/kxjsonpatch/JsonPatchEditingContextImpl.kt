package com.alightcreative.util.jsonpatch

import kotlinx.serialization.json.*

/**
 * Created by Reid Byun on 2023/07/30.
 * Copyright (c) 2023 Bending Spoons. All rights reserved.
 */

class JsonPatchEditingContextImpl(var source: JsonElement): JsonPatchEditingContext {
	override fun remove(path: List<String>) {
		source = editElement(source, path, action = { root ->
			if (path.isEmpty()) {
				throw JsonPatchApplicationException("[Remove Operation] path is empty")
			}
			else {
				var parentNode = root//getParentNode(root, searchPath)
				if (parentNode == null) {
					throw JsonPatchApplicationException("[Remove Operation] noSuchPath in source, path provided : " + path)
				}
				else {
					val fieldToRemove = path[path.size - 1].replace("\"".toRegex(), "")
					if (parentNode is JsonObject) {
						val copyp = parentNode.remove(fieldToRemove)
						println(parentNode)
						println(copyp)
						println(root)
						parentNode = copyp
					}
					else if (parentNode is JsonArray) {
						parentNode = parentNode.remove(arrayIndex(fieldToRemove, parentNode.size - 1))
						//return parentNode
					}
					else {
						throw JsonPatchApplicationException("[Remove Operation] noSuchPath in source, path provided : " + path)
					}
				}
				parentNode
			}}) ?: source
	}

	override fun replace(path: List<String>, value: JsonElement) {
		source = editElement(source, path, action = { root ->
			if (path.isEmpty()) {
				throw JsonPatchApplicationException("[Replace Operation] path is empty")
			} else {
				var parentNode = getParentNode(source, path)
				if (parentNode == null) {
					throw JsonPatchApplicationException("[Replace Operation] noSuchPath in source, path provided : " + path)
				} else {
					val fieldToReplace = path[path.size - 1].replace("\"".toRegex(), "")
					if (fieldToReplace.isEmpty() && path.size == 1) {
						parentNode = value
					}
					else if (parentNode is JsonObject) {
						parentNode = parentNode.add(fieldToReplace, value)
					}
					else if (parentNode is JsonArray) {
						parentNode = parentNode.set(arrayIndex(fieldToReplace, parentNode.size - 1), value)
					}
					else {
						throw JsonPatchApplicationException("[Replace Operation] noSuchPath in source, path provided : " + path)
					}
					parentNode
				}
			}
		}) ?: source
	}

	override fun add(path: List<String>, value: JsonElement) {
		source = editElement(source, path, action = { root ->
			if (path.isEmpty()) {
				throw JsonPatchApplicationException("[ADD Operation] path is empty , path : ")
			} else {
				var parentNode = root//getParentNode(root, searchPath)
				if (parentNode == null) {
					throw JsonPatchApplicationException("[ADD Operation] noSuchPath in source, path provided : " + path)
				} else {
					val fieldToReplace = path[path.size - 1].replace("\"".toRegex(), "")
					if (fieldToReplace == "" && path.size == 1)
						parentNode = value
					else if (!parentNode.isContainerNode()) {
						throw JsonPatchApplicationException("[ADD Operation] parent is not a container in source, path provided : $path | node : $parentNode")
					}
					else if (parentNode is JsonArray) {
						parentNode = addToArray(path, value, parentNode)
					}
					else {
						parentNode = addToObject(path, parentNode, value)
					}
				}
				parentNode
			}
		}) ?: source
	}

	override fun move(fromPath: List<String>, toPath: List<String>) {
		val parentNode = getParentNode(source, fromPath)
		val field = fromPath[fromPath.size - 1].replace("\"".toRegex(), "")
		val valueNode = if (parentNode!! is JsonArray) {
			parentNode.jsonArray[field.toInt()]
		}
		else {
			parentNode.jsonObject[field]
		}

		remove(fromPath)
		add(toPath, valueNode!!)
	}

	override fun copy(fromPath: List<String>, toPath: List<String>) {
		val parentNode = getParentNode(source, fromPath)
		val field = fromPath[fromPath.size - 1].replace("\"".toRegex(), "")
		val valueNode = if (parentNode!! is JsonArray) {
			parentNode.jsonArray[field.toInt()]
		}
		else {
			parentNode.jsonObject[field]
		}
		add(toPath, valueNode!!)
	}

	override fun test(path: List<String>, value: JsonElement) {
		source = editElement(source, path, action = { root ->
			if (path.isEmpty()) {
				throw JsonPatchApplicationException("[TEST Operation] path is empty , path : ")
			} else {
				var parentNode = root
				if (parentNode == null) {
					throw JsonPatchApplicationException("[TEST Operation] noSuchPath in source, path provided : " + path)
				}
				else {
					val fieldToReplace = path[path.size - 1].replace("\"".toRegex(), "")
					if (fieldToReplace == "" && path.size == 1)
						parentNode = value
					else if (!parentNode.isContainerNode())
						throw JsonPatchApplicationException("[TEST Operation] parent is not a container in source, path provided : $path | node : $parentNode")
					else if (parentNode is JsonArray) {
						val target = parentNode
						val idxStr = path[path.size - 1]

						if ("-" == idxStr) {
							// see http://tools.ietf.org/html/rfc6902#section-4.1
							if (target.get(target.size - 1) != value) {
								throw JsonPatchApplicationException("[TEST Operation] value mismatch")
							}
						} else {
							val idx = arrayIndex(idxStr.replace("\"".toRegex(), ""), target.size)
							if (target.get(idx) != value) {
								throw JsonPatchApplicationException("[TEST Operation] value mismatch")
							}
						}
					} else {
						val target = parentNode as JsonObject
						val key = path[path.size - 1].replace("\"".toRegex(), "")
						if (target.get(key) != value) {
							throw JsonPatchApplicationException("[TEST Operation] value mismatch")
						}
					}
					parentNode
				}
			}
		}) ?: source
	}

	private fun getParentNode(source: JsonElement, fromPath: List<String>): JsonElement? {
		val pathToParent = fromPath.subList(0, fromPath.size - 1) // would never by out of bound, lets see
		return getNode(source, pathToParent, 1)
	}

	private fun getNode(ret: JsonElement, path: List<String>, pos_: Int): JsonElement? {
		var pos = pos_
		if (pos >= path.size) {
			return ret
		}
		val key = path[pos]
		if (ret is JsonArray) {
			val keyInt = (key.replace("\"".toRegex(), "")).toInt()
			return getNode(ret[keyInt], path, ++pos)
		} else if (ret is JsonObject) {
			if (ret.containsKey(key)) {
				return getNode(ret[key]!!, path, ++pos)
			}
			return null
		} else {
			return ret
		}
	}

	private fun editElement(source: JsonElement, fromPath: List<String>, action: (JsonElement)-> JsonElement?): JsonElement? {
		val pathToParent = fromPath.subList(0, fromPath.size - 1) // would never by out of bound, lets see
		return findAndAction(source, pathToParent, 1, action)
	}

	private fun findAndAction(ret: JsonElement, path: List<String>, pos_: Int, action: (JsonElement)-> JsonElement?): JsonElement? {
		var pos = pos_
		if (pos >= path.size) {
			// Result
			return action(ret)
		}
		val key = path[pos]
		if (ret is JsonArray) {
			val keyInt = (key.replace("\"".toRegex(), "")).toInt()
			return ret.set(keyInt, findAndAction(ret[keyInt], path, ++pos, action))
		}
		else if (ret is JsonObject) {
			if (ret.containsKey(key)) {
				return findAndAction(ret[key]!!, path, ++pos, action)
			}
			return null
		} else {
			// Result
			return action(ret)
		}
	}

	private fun arrayIndex(s: String, max: Int): Int {
		val index = s.toInt()
		if (index < 0) {
			throw JsonPatchApplicationException("index Out of bound, index is negative")
		} else if (index > max) {
			throw JsonPatchApplicationException("index Out of bound, index is greater than " + max)
		}
		return index
	}

	private fun addToObject(path: List<String>, node: JsonElement, value: JsonElement): JsonObject {
		val target = node as JsonObject
		val key = path[path.size - 1].replace("\"".toRegex(), "")

		return target.add(key, value)
	}

	private fun addToArray(path: List<String>, value: JsonElement, parentNode: JsonElement): JsonElement {
		var target = parentNode as JsonArray
		val idxStr = path[path.size - 1]

		if ("-" == idxStr) {
			// see http://tools.ietf.org/html/rfc6902#section-4.1
			//target.add(value)
			target = target.add(value)
		} else {
			//val idx = arrayIndex(idxStr.replace("\"".toRegex(), ""), target.size())
			val idx = arrayIndex(idxStr.replace("\"".toRegex(), ""), target.size)
			target = target.insert(idx, value)
		}

		return target
	}
}

private fun JsonArray.insert(index: Int, value_: JsonElement?):JsonArray {
	val value=value_ ?:JsonNull
	return if(index>=size) {
		this.add(value)
	}
	else if(index<0) {
		this.copy { insert(0, value) }
	}
	else {
		this.copy { insert(index, value) }
	}
}

fun JsonArray.add(value_: JsonElement?):JsonArray {
	val value=value_ ?:JsonNull
	return copy { add(value) }
}

private fun JsonArray.set(index: Int, value_: JsonElement?):JsonArray {
	val value=value_ ?:JsonNull
	if(index>=size) {
		throw IndexOutOfBoundsException("")
	}
	return copy { this[index] = value }
}

private fun JsonArray.remove(index:Int):JsonArray {
	return copy{ removeAt(index) }
}

private inline fun JsonArray.copy(mutatorBlock: MutableList<JsonElement>.() -> Unit): JsonArray {
	return JsonArray(this.toMutableList().apply(mutatorBlock))
}

fun JsonObject.add(key: String, value_: JsonElement?): JsonObject {
	val value=value_ ?:JsonNull
	return copy { this[key] = value }
}

private fun JsonObject.remove(key: String): JsonObject {
	return copy { remove(key) }
}

fun JsonObject.addProperty(key: String, value: String): JsonObject {
	return this.copy {
		this[key] = JsonPrimitive(value)
	}
}

private inline fun JsonObject.copy(mutatorBlock: MutableMap<String, JsonElement>.() -> Unit): JsonObject {
	return JsonObject(this.toMutableMap().apply(mutatorBlock))
}

private fun JsonElement.isContainerNode(): Boolean {
	return this is JsonArray || this is JsonObject
}

fun JsonElement.deepCopy(): JsonElement {
	return when(this) {
		is JsonArray-> this.jsonArray.copy {}
		is JsonObject-> this.jsonObject.copy {}
		is JsonPrimitive-> JsonPrimitive(this.jsonPrimitive.content) /* Todo check */
		is JsonNull-> this.jsonNull
	}
}