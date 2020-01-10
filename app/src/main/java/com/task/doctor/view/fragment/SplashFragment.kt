package com.task.doctor.view.fragment

import android.app.Activity
import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.task.doctor.R
import org.koin.android.viewmodel.ext.android.viewModel

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
 * File Name : SplashFragment.kt
 * ClassName : SplashFragment
 * Module Name : app
 * Desc : Splash Screen will loaded first  when App is launched
 * It is first screen of App
 */

class SplashFragment : Fragment() {

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
   ): View? = inflater.inflate(R.layout.fragment_splash, container, false)

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)

      Handler().postDelayed({
         context?.let {
            findNavController().navigate(R.id.action_splashFragment_to_doctorListFragment)
         }
      }, 2000)
   }

}