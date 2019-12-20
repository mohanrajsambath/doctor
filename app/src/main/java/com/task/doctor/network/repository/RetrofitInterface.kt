package com.task.doctor.network.repository

import com.task.doctor.model.DoctorResponseModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

/*
 * Copyright (c) 2019. Created for Coding Challenge and Created by R Sathish Kumar on 05-12-2019.
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
 * Created on :05-12-2019
 * File Name : RetrofitInterface.kt
 * ClassName : RetrofitInterface
 * Module Name : app
 * Desc : RetrofitInterface is Api Interface class
 */

interface RetrofitInterface {


    /**
     * Request to doctor search list from api
     */
    @GET("android/doctors.json")
    fun listDoctors(): Deferred<Response<DoctorResponseModel>>

    @GET("android/doctors-{lastkey}.json")
    fun listDoctorsWithKey(@Path("lastkey") lastkey: String): Deferred<Response<DoctorResponseModel>>



}