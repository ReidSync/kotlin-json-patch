package com.alightcreative.util.jsonpatch

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull

/**
 * Created by Reid Byun on 2023/07/31.
 * Copyright (c) 2023 Bending Spoons. All rights reserved.
 */

abstract class JsonPatchApplyProcessor(private val source: JsonElement = JsonNull) {
	var targetSource: JsonElement = source
		private set

	open fun setSource(changedSource: JsonElement) {
		targetSource = changedSource
	}
}

fun JsonPatchApplyProcessor.edit(actions: JsonPatchEditingContext.()->Unit) {
	val context = JsonPatchEditingContextImpl(source = this.targetSource)
	context.actions()

	this.setSource(context.source)
}