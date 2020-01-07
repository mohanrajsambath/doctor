package com.task.doctor.model
import com.google.gson.annotations.SerializedName

/*
 * Copyright (c) 2019. Created for Coding Challenge and Created by R Sathish Kumar on 11-12-2019.
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
 * Created on :11-12-2019 
 * File Name : DoctorResponseModel.kt
 * ClassName : DoctorResponseModel
 * Module Name : app
 * Desc : This class is Response Model
 */
data class DoctorResponseModel(
    @SerializedName("doctors")
    val doctors: List<Doctor> = listOf(),
    @SerializedName("lastKey")
    val lastKey: String = "" // CvQD7gEAAKjcb
)

data class Doctor(
    @SerializedName("address")
    val address: String = "", // Karl-Marx-Allee 82, 10223 Berlin, Germany
    @SerializedName("email")
    val email: Any? = Any(), // null
    @SerializedName("highlighted")
    val highlighted: Boolean = false, // false
    @SerializedName("id")
    val id: String = "", // ChIJ8ZxLJkBOqEcRz5X22ib1KWU
    @SerializedName("integration")
    val integration: Any? = Any(), // null
    @SerializedName("lat")
    val lat: Double = 0.0, // 52.5169599
    @SerializedName("lng")
    val lng: Double = 0.0, // 13.2395953
    @SerializedName("name")
    val name: String = "", // Dr. med. Casares Akdenizli
    @SerializedName("openingHours")
    val openingHours: List<Any> = listOf(),
    @SerializedName("phoneNumber")
    val phoneNumber: String = "", // +29 30 29778970
    @SerializedName("photoId")
    val photoId: String? = "", // https://vivy.com/interviews/challenges/android/img/people/15.jpeg
    @SerializedName("rating")
    val rating: Double = 0.0, // 3.9000000953672316
    @SerializedName("reviewCount")
    val reviewCount: Int = 0, // 5
    @SerializedName("source")
    val source: String = "", // google
    @SerializedName("specialityIds")
    val specialityIds: List<Int> = listOf(),
    @SerializedName("translation")
    val translation: Any? = Any(), // null
    @SerializedName("website")
    val website: String? = "" // http://www.vivy-doc.de/
)
