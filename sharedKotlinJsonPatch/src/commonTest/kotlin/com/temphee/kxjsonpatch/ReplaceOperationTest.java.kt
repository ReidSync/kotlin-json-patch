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

import resources.testdata.TestData_REPLACE

/**
 * @author ctranxuan (streamdata.io).
 */
object ReplaceOperationTest : AbstractTest() {
//    @org.junit.runners.Parameterized.Parameters
//    @Throws(java.io.IOException::class)
    fun data(): Collection<PatchTestCase> {
        return PatchTestCase.load(TestData_REPLACE)
    }
}