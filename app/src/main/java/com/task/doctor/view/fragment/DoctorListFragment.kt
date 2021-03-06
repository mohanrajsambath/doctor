package com.task.doctor.view.fragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getColor
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.task.doctor.R
import com.task.doctor.databinding.FragmentDoctorListBinding
import com.task.doctor.domain.model.DoctorModel
import com.task.doctor.utils.recycler.infiniteScrollListener
import com.task.doctor.view.adapter.DoctorRecyclerViewAdapter
import com.task.doctor.viewmodel.DoctorViewModel
import org.koin.android.viewmodel.ext.android.viewModel

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
 * File Name : DoctorListFragment.kt
 * ClassName : DoctorListFragment
 * Module Name : app
 * Desc : DoctorListFragment view class communicate with viewModel and fetch data and display into recyclerview
 * DoctorListFragment is handling view part.
 */

class DoctorListFragment : Fragment(), View.OnClickListener {

   private var vivyDoctorClicked: Boolean = false
   lateinit var binding: FragmentDoctorListBinding
   private val doctorsViewModel: DoctorViewModel by viewModel()
   private lateinit var getFragmentContext: Activity
   private var doctor = mutableListOf<DoctorModel>()
   private  var doctorRecyclerViewAdapter: DoctorRecyclerViewAdapter = DoctorRecyclerViewAdapter(doctor)

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      getFragmentContext = this.requireActivity()
   }

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
      if(!::binding.isInitialized) {
         binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_doctor_list, container, false)


         binding.viewModel = doctorsViewModel
         binding.executePendingBindings()

         binding.let {
            it.adapter = doctorRecyclerViewAdapter
         }

         loadData()
         setUpViewModelObserver()
         addScrollListener()

      }
      return binding.root
   }

   private fun setUpViewModelObserver() {
      //adds the new set of results to the adapter list
      doctorsViewModel.doctorList.observe(this, Observer<List<DoctorModel>> {
         this.doctor.addAll(it)
         doctorRecyclerViewAdapter.notifyDataSetChanged()
      })

      /**
       *  displaying error status from viewModel communicate via Observer
       */
      doctorsViewModel.error.observe(this, Observer {
         Toast.makeText(getFragmentContext, it, Toast.LENGTH_SHORT).show()
      })
   }

   private fun loadData()  = doctorsViewModel.loadData()

   /**
    *  OnclickListener trigger when user clicked the button
    */
   private fun setOnClickListener() {
      binding.btnVivyDoctor.setOnClickListener(this)
      binding.btnRecentDoctor.setOnClickListener(this)
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      setOnClickListener()
   }

   /**
    * InfiniteScrollListener onLoad more method is triggered when end of the list
    * is reached
    */
   private fun addScrollListener() {
      binding.rcyVwDoctorList.addOnScrollListener(object : infiniteScrollListener() {
         override fun onLoadMore() {
            doctorsViewModel.loadMoreData(vivyDoctorClicked)
         }
      })
   }

   /**
    * Onclick is trigger when user clicked on particular button
    * It will communicate with viewModel and sorted the result and updated into list.
    */
   override fun onClick(v: View?) {
      when (v?.id) {
         binding.btnRecentDoctor.id -> loadRecentDoctor()
         binding.btnVivyDoctor.id -> loadVivyDoctor()
      }
   }

   private fun loadVivyDoctor() {
      vivyDoctorClicked = true
      doctor.clear()
      binding.btnVivyDoctor.setBackgroundColor(getColor(getFragmentContext, R.color.colorWhite))
      binding.btnRecentDoctor.setBackgroundColor(
         getColor(getFragmentContext, R.color.colorBlackAlpha60
         )
      )
      doctorsViewModel.sortByVivyDoctors()
   }

   private fun loadRecentDoctor() {
      vivyDoctorClicked = false
      doctor.clear()
      binding.btnRecentDoctor.setBackgroundColor(getColor(getFragmentContext, R.color.colorWhite))
      binding.btnVivyDoctor.setBackgroundColor(getColor(getFragmentContext, R.color.colorBlackAlpha60))
      doctorsViewModel.sortByRecentDoctors()
   }

   override fun onResume() {
      super.onResume()
      userClickedLastStatus()
   }

   private fun userClickedLastStatus() {
      if (vivyDoctorClicked){
         loadVivyDoctor()
      }else{
         loadRecentDoctor()
      }
   }
}
