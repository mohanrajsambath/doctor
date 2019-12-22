package com.task.doctor

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.task.doctor.network.DoctorDataSource
import com.task.doctor.network.Result
import com.task.doctor.viewmodel.DoctorListViewModel
import junit.framework.TestCase.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import java.io.IOException
import java.lang.Exception

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


   private lateinit var  dataSource : DoctorDataSource



   @Before
   @Throws(Exception::class)
   fun setUp() {
      // for the "@Mock" annotations
      MockitoAnnotations.initMocks(this)

      // Make presenter a mock while using mock repository and viewContract created above
      dataSource = mock(DoctorDataSource::class.java)

   }

   @Test
   fun getDoctorListSuccessTest(){
      runBlocking {
         doctorJob = launch {
          val response  = dataSource.getDoctor()
            when(response){
             is Result.Success -> assertEquals(response,"200")
            }
         }
      }
   }


   @Test
   fun getDoctorListFailTest(){
      runBlocking {
         doctorJob = launch {
            val response  = dataSource.getDoctor()
            when(response){
               is Result.Error -> assertEquals(response.data.message,"Error Occurred")
            }
         }
      }
   }
}