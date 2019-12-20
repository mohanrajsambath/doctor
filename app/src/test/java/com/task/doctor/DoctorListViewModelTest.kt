package com.task.doctor

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.task.doctor.network.DoctorDataSource
import com.task.doctor.viewmodel.DoctorListViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

/*
 * Copyright (c) 2019. Created for Coding Challenge and Created by R Sathish Kumar on 20-12-2019.
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
 * Created on :20-12-2019 
 * File Name : DoctorListViewModelTest.kt
 * ClassName : DoctorListViewModelTest
 * Module Name : app
 * Desc : 
 */
class DoctorListViewModelTest {

   @Rule
   @JvmField
   val instantExecutorRule = InstantTaskExecutorRule()
   private var doctorJob: Job? = null

   private val dataSource = Mockito.mock(DoctorDataSource::class.java)

   private val viewModel by lazy { DoctorListViewModel(dataSource) }

   @Test
   fun getDoctorList(){
      doctorJob = GlobalScope.launch {


      }
   }



}