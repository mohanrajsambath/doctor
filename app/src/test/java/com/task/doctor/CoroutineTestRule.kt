package com.task.doctor

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/*
 * Copyright (c) 2019. Created for Coding Challenge and Created by R Sathish Kumar on 10-01-2020.
 * All Rights Reserved,Company Confidential.
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
 *
 * Project Name : doctor
 * Created by : R Sathish Kumar - Android Application Developer
 * Created on :10-01-2020 
 * File Name : CoroutineTestRule.kt
 * ClassName : CoroutineTestRule
 * Module Name : app
 * Desc : 
 */
class CoroutineTestRule: TestRule {

   override fun apply(base: Statement?, description: Description?): Statement =
      object : Statement() {

         override fun evaluate() {
            Dispatchers.setMain(TestCoroutineDispatcher())
            try {
               base?.evaluate()
            } finally {
               Dispatchers.resetMain()
            }
         }
      }
}