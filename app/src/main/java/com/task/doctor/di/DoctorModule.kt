package com.task.doctor.di

import com.task.data.common.Connectivity
import com.task.data.common.ConnectivityImpl
import com.task.doctor.BuildConfig
import com.task.doctor.data.DoctorRepositoryImpl
import com.task.doctor.domain.repository.DoctorRepository
import com.task.doctor.domain.usecases.DoctorUseCases
import com.task.doctor.domain.usecases.DoctorUseCasesImpl
import com.task.doctor.network.DoctorService
import com.task.doctor.view.adapter.DoctorRecyclerViewAdapter
import com.task.doctor.viewmodel.DoctorViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*
 * Copyright (c) 2019. Created for Coding Challenge and Created by R Sathish Kumar on 14-12-2019.
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
 * Created on :14-12-2019 
 * File Name : DoctorModule.kt
 * ClassName : AppInjector
 * Module Name : app
 * Desc : This class is a doctorModule  which provides the di
 *
 */

val doctorModule = module {
   single {  provideRetrofit(get())}
   factory {  okHttpClient()}
   factory { doctorService(get()) }

   single { DoctorRepositoryImpl(get(),get()) }.bind(DoctorRepository::class)
   single { ConnectivityImpl(androidContext()) }.bind(Connectivity::class)
   viewModel {DoctorViewModel(get())}
   factory { DoctorUseCasesImpl(get()) }.bind(DoctorUseCases::class)
   factory {
      DoctorRecyclerViewAdapter(get())
   }

}

 fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
   return Retrofit.Builder()
      .baseUrl(BuildConfig.BASE_URL)
      .client(okHttpClient)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
}

 fun okHttpClient(): OkHttpClient {
    val interceptor: HttpLoggingInterceptor? = HttpLoggingInterceptor()
    interceptor!!.level = HttpLoggingInterceptor.Level.BODY
   return OkHttpClient().newBuilder().addInterceptor(interceptor).build()
   }

fun  doctorService(retrofit: Retrofit): DoctorService = retrofit.create(DoctorService::class.java)



