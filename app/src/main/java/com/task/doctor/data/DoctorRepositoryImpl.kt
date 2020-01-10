package com.task.doctor.data

import com.task.data.common.Connectivity
import com.task.doctor.domain.model.DoctorModel
import com.task.doctor.domain.model.Result
import com.task.doctor.domain.repository.DoctorRepository
import com.task.doctor.network.DoctorService
import java.io.IOException
import java.lang.Exception

/*
 * Copyright (c) 2019. Created for Coding Challenge and Created by R Sathish Kumar on 09-01-2020.
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
 * Created on :09-01-2020 
 * File Name : DoctorRepositoryImpl.kt
 * ClassName : DoctorRepositoryImpl
 * Module Name : app
 * Desc : DoctorRepositoryImpl
 */

class DoctorRepositoryImpl(private val doctorService: DoctorService, private val connectivity: Connectivity) : DoctorRepository {
   private  var lastKey: String? = null

   override suspend fun getDoctorListFromNetwork(): Result<List<DoctorModel>> {
      return if (connectivity.isNetworkAvailable()) {
         getDataFromNetwork()
      } else return Result.Error(("No Internet Connection"))
   }

   override suspend fun getDoctorListWithKey(): Result<List<DoctorModel>> {
      return if (connectivity.isNetworkAvailable()) {
         getDoctorDataList()
      } else return Result.Error(("No Internet Connection"))
   }

   private suspend fun getDataFromNetwork(): Result<List<DoctorModel>> {
      try {
         val response = doctorService.listDoctors()
         if (response.isSuccessful) {
            lastKey = response.body()!!.lastKey
            val result = response.body().let { it ->
               it?.doctors!!.map {
                  it.toEntity()
               }
            }
            result.let { return Result.Success(it) }
         }
         return Result.Error(("Error occurred during fetching doctor list!"))
      } catch (e: Exception) {
         return Result.Error(e.message.toString())
      }
   }

   private suspend fun getDoctorDataList(): Result<List<DoctorModel>> {
      try {
         if (lastKey != null) {
            val response = doctorService.listDoctorsLastKey(lastKey!!)
            if (response.isSuccessful) {
               lastKey = response.body()!!.lastKey
               val result = response.body().let { it ->
                  it?.doctors!!.map {
                     it.toEntity()
                  }
               }
               result.let { return Result.Success(it) }
            }
         }
         return Result.Error("Doctor List Ended")
      } catch (e: Exception) {
         return Result.Error(e.message.toString())
      }
   }
}