package com.task.doctor.domain.model

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
 * File Name : DoctorModel.kt
 * ClassName : DoctorModel
 * Module Name : app
 * Desc : Model class
 */

data class DoctorModel(
   val name: String,
   val address: String,
   val photoId: String?= "",
   val website: String?="",
   val rating: Double =0.0,
   val reviewCount : Int =0 )