package com.temphee.kxjsonpatch

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull

/**
 * Created by Reid Byun on 2023/07/31.
 */

abstract class JsonPatchApplyProcessor(private val source: JsonElement = JsonNull) {
	var targetSource: JsonElement = source
		private set

	open fun setSource(changedSource: JsonElement) {
		targetSource = changedSource
	}
}
//
//fun JsonPatchApplyProcessor.edit(actions: JsonPatchEditingContext.()->Unit) {
//	val context = JsonPatchEditingContextImpl(source = this.targetSource)
//	context.actions()
//
//	this.setSource(context.source)
//}

fun JsonPatchApplyProcessor.edit(actions: JsonPatchEditingContext.()->Unit) {
	if (this is NoopProcessor) { // for test
		val context = JsonPatchEditingContextTestImpl(source = this.targetSource)
		context.actions()
		this.setSource(context.source)
	}
	else {
		val context = JsonPatchEditingContextImpl(source = this.targetSource)
		context.actions()
		this.setSource(context.source)
	}
}