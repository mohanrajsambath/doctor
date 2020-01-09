package com.task.doctor.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.task.doctor.R
import com.task.doctor.databinding.ItemDoctorDetailsBinding
import com.task.doctor.model.Doctor
import com.task.doctor.view.fragment.DoctorListFragmentDirections

/*
 * Copyright (c) 2019. Created for Coding Challenge and Created by R Sathish Kumar on 15-12-2019.
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
 * Created on :15-12-2019 
 * File Name : ListAdapter.kt
 * ClassName : ListAdapter
 * Module Name : app
 * Desc : DoctorRecyclerViewAdapter adapter class for the bind the value into
 * recyclerView show the value in list
 */

class DoctorRecyclerViewAdapter(private var items: MutableList<Doctor>) :
   RecyclerView.Adapter<DoctorRecyclerViewAdapter.ViewHolder>() {
   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
   ): ViewHolder {
      val binding: ItemDoctorDetailsBinding = DataBindingUtil.inflate(
         LayoutInflater.from(parent.context), R.layout.item_doctor_details, parent, false
      )
      return ViewHolder(binding)
   }

   override fun getItemCount(): Int {
      return items.size
   }

   override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      holder.bind(items[position])
   }

   class ViewHolder(private val binding: ItemDoctorDetailsBinding) :
      RecyclerView.ViewHolder(binding.root) {
      private lateinit var item: Doctor

      internal fun bind(item: Doctor) {
         this.item = item
         binding.itemDetails = item
         binding.setClickListener {
            val name = item.name
            val address = item.address
            val photoUrl = item.photoId.toString()
            it?.findNavController()?.navigate(
               R.id.action_detail, sendData(name,address,photoUrl).arguments
            )
         }
      }

      private fun sendData(name: String, address: String,imageUrl:String): NavDirections {
         return DoctorListFragmentDirections.actionDetail(name, address,imageUrl)
      }
   }
}



