package com.task.doctor.network

import com.task.doctor.model.DoctorResponseModel
import com.task.doctor.network.repository.RetrofitInterface
import java.io.IOException
import java.lang.Exception

/*
 * Copyright (c) 2019. Created for Coding Challenge and Created by R Sathish Kumar on 12-12-2019.
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
 * Created on :12-12-2019 
 * File Name : DoctorDataSource.kt
 * ClassName : DoctorDataSource
 * Module Name : app
 * Desc : DoctorDataSource
 */


open class DoctorDataSource constructor(private val apiService: RetrofitInterface) {
   suspend fun getDoctor() = safeApiCall(
      call = { getDoctorList() }, errorMessage = "Error Occurred"
   )

   private suspend fun getDoctorList(): Result<DoctorResponseModel> {
         val response = apiService.listDoctors().await()
         if (response.isSuccessful) return Result.Success(response.body()!!)
         return Result.Error(IOException("Error occurred during fetching doctor list!"))
   }

   suspend fun getDoctorWithKey(lastKey:String) = safeApiCall(
      call = { getDoctorWithKeyList(lastKey) }, errorMessage = "Error Occurred"
   )

   private suspend fun getDoctorWithKeyList(lastKey:String): Result<DoctorResponseModel> {
      val response = apiService.listDoctorsWithKey(lastKey).await()
      if (response.isSuccessful) return Result.Success(response.body()!!)
      return Result.Error(IOException("Error occurred during fetching doctor list!"))
   }

}

suspend fun <T : Any> safeApiCall(call: suspend () -> Result<T>, errorMessage: String): Result<T> =
   try {
      call.invoke()
   } catch (e: Exception) {
      Result.Error(IOException(errorMessage, e))
   }