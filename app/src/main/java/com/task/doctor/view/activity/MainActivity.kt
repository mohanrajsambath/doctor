package com.task.doctor.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.task.doctor.R

/*
 * Copyright (c) 2019. Created for Coding Challenge and Created by R Sathish Kumar on 07-12-2019.
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
 * Created on :07-12-2019 
 * File Name : SplashScreenActivity.kt
 * ClassName : SplashScreenActivity
 * Module Name : app
 * Desc : First Launch Screen of Application
 */

class MainActivity : AppCompatActivity() {


   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_main)
   }

   override fun onSupportNavigateUp() = this.findNavController(R.id.nav_host_splash_fragment).navigateUp()

}