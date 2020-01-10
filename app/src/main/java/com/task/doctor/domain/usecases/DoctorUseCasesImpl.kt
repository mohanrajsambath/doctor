package com.task.doctor.domain.usecases

import com.task.doctor.domain.model.DoctorModel
import com.task.doctor.domain.model.Result
import com.task.doctor.domain.repository.DoctorRepository

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
 * File Name : DoctorUseCasesImpl.kt
 * ClassName : DoctorUseCasesImpl
 * Module Name : app
 * Desc : Doctor data use case Implementation
 */

class DoctorUseCasesImpl(private val repo: DoctorRepository):DoctorUseCases {
   override suspend fun getDoctorList(): Result<List<DoctorModel>> {
      return  repo.getDoctorListFromNetwork()
   }

   override suspend fun getDoctorListWithKey(): Result<List<DoctorModel>> {
      return  repo.getDoctorListWithKey()
   }
}