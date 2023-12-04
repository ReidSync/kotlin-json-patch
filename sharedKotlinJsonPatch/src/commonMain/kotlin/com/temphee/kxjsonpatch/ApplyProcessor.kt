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
import kotlinx.serialization.json.*

class ApplyProcessor(target: JsonElement) : JsonPatchProcessor {

    private var targetSource: JsonElement

    init {
        this.targetSource = target.deepCopy()
    }

    fun result(): JsonElement {
        return targetSource
    }

    override fun move(fromPath: List<String>, toPath: List<String>) {
        val parentNode = getParentNode(targetSource, fromPath)
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
        val parentNode = getParentNode(targetSource, fromPath)
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
        val result = editElement(targetSource, path, action = { root ->
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
        })

        targetSource = result ?: targetSource
    }

    override fun add(path: List<String>, value: JsonElement) {
        val result = editElement(targetSource, path, action = { root ->
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
        })

        targetSource = result ?: targetSource
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

    override fun replace(path: List<String>, value: JsonElement) {
        val result = editElement(targetSource, path, action = { root ->
            if (path.isEmpty()) {
                throw JsonPatchApplicationException("[Replace Operation] path is empty")
            } else {
                var parentNode = getParentNode(targetSource, path)
                if (parentNode == null) {
                    throw JsonPatchApplicationException("[Replace Operation] noSuchPath in source, path provided : " + path)
                } else {
                    val fieldToReplace = path[path.size - 1].replace("\"".toRegex(), "")
                    if (fieldToReplace.isNullOrEmpty() && path.size == 1) {
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
        })

        targetSource = result ?: targetSource
    }

    override fun remove(path: List<String>) {
        val result = editElement(targetSource, path, action = { root ->
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
        }})

        targetSource = result ?: targetSource
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
            val element = ret.get(keyInt)
            if (element == null)
                return null
            else
                return getNode(ret.get(keyInt), path, ++pos)
        } else if (ret is JsonObject) {
            val ret_ = ret
            if (ret_.containsKey(key)) {
                return getNode(ret_.get(key)!!, path, ++pos)
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
            val result = action(ret)
            return result
        }
        val key = path[pos]
        if (ret is JsonArray) {
            val keyInt = (key.replace("\"".toRegex(), "")).toInt()
            val element = ret[keyInt]
            if (element == null) {
                return null
            }
            else {
                val result = ret.set(keyInt, findAndAction(ret[keyInt], path, ++pos, action))
                return result
            }
        }
        else if (ret is JsonObject) {
            val ret_ = ret
            if (ret_.containsKey(key)) {
                return findAndAction(ret_[key]!!, path, ++pos, action)
            }
            return null
        } else {
            // Result
            val result = action(ret)
            return result
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
}

////TODO insert not very efficient: find a better way to do it?
//private fun insert(target: JsonArray, idx: Int, value: JsonElement) {
//    val lastidx = target.size - 1
//    val last = target.get(lastidx)
//    target.add(last)
//    //move up elements after idx
//    for (i in (lastidx - 1) downTo idx)
//        target.set(i + 1, target.get(i))
//    //finally insert new value
//    target.set(idx, value)
//}

private fun  JsonArray.insert(index: Int, value_: JsonElement?):JsonArray {
    val value=value_ ?:JsonNull
    if(index>=size) {
        return this.add(value)
    }
    else if(index<0) {
        return this.copy { insert(0, value) }
    }
    else {
        return this.copy { insert(index, value) }
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

inline fun JsonArray.copy(mutatorBlock: MutableList<JsonElement>.() -> Unit): JsonArray {
    return JsonArray(this.toMutableList().apply(mutatorBlock))
}

fun  JsonObject.add(key: String, value_: JsonElement?):JsonObject {
    val value=value_ ?:JsonNull
    return copy { this[key] = value }
}

private fun  JsonObject.remove(key: String):JsonObject {
    return copy { remove(key) }
}

fun JsonObject.addProperty(key: String, value: String): JsonObject {
    return this.copy {
        this[key] = JsonPrimitive(value)
    }
}

inline fun JsonObject.copy(mutatorBlock: MutableMap<String, JsonElement>.() -> Unit): JsonObject {
    return JsonObject(this.toMutableMap().apply(mutatorBlock))
}

private fun  JsonElement.isContainerNode(): Boolean {
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

fun JsonElement.isJsonArray(): Boolean {
    if (this as? JsonArray != null) {
        return true
    }
    return false
}

fun JsonElement.isJsonObject(): Boolean {
    if (this as? JsonObject != null) {
        return true
    }
    return false
}