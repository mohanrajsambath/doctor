package com.task.doctor.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.task.doctor.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

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
 * File Name : RetrofitClient.kt
 * ClassName : RetrofitClient
 * Module Name : app
 * Desc :RetrofitClient is invoking class of RetrofitClient and OkHttp
 */

object RetrofitClient  {

    internal fun getClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory()).build()
    }


    private fun okHttpClient(): OkHttpClient {
        val interceptor: HttpLoggingInterceptor? = HttpLoggingInterceptor()
        interceptor!!.level = HttpLoggingInterceptor.Level.BODY
        return if (!BuildConfig.RETROFIT_LOG_INTERCEPTOR) {
            OkHttpClient.Builder().build()
        } else {
            try {
                OkHttpClient.Builder().addInterceptor(interceptor).build()
            } finally {
            }
        }
    }




}