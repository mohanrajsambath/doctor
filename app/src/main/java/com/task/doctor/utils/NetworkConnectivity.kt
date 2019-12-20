package com.task.doctor.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

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
 * File Name : NetworkConnectivity.kt
 * ClassName : NetworkConnectivityDefaultOnlineChecker
 * Module Name : app
 * Desc : Check's the network Connectivity
 */
object NetworkConnectivity {

   /**
    * Check's the network Connectivity
    * @return  Boolean status of the network connection
    */
   @SuppressWarnings("deprecation")
   internal fun isNetworkAvailable(context: Context): Boolean {
      val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
      return if (connectivityManager is ConnectivityManager) {
         val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
         networkInfo?.isConnected ?: false
      } else false
   }



}