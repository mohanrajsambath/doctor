package com.task.doctor.view.fragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.task.doctor.R
import com.task.doctor.databinding.FragmentDoctorDetailBinding
import com.task.doctor.model.Doctor
import com.task.doctor.view.adapter.setAvatarThumbnail
import kotlinx.android.synthetic.main.item_doctor_details.*

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
 * File Name : DoctorDetailsFragment.kt
 * ClassName : DoctorDetailsFragment
 * Module Name : app
 * Desc : DoctorDetailFragment class show the details of Selected doctor profile.
 * Its show name of doctor , Address and Image of Doctor.
 */
class DoctorDetailFragment : Fragment() {
   lateinit var binding: FragmentDoctorDetailBinding
   private lateinit var getFragmentContext: Activity
   val args by lazy {
      DoctorDetailFragmentArgs.fromBundle(arguments!!)
   }

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      getFragmentContext = this.requireActivity()
   }

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
   ): View? {
      binding = DataBindingUtil.inflate(inflater, R.layout.fragment_doctor_detail, container, false)
      binding.executePendingBindings()
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      binding.txtViewDoctorName.text = args.name
      binding.txtViewDoctorAddress.text = args.address
      setAvatarThumbnail(view = binding.ImgViewAvatar, imageURL = args.Image)

   }
}