package com.task.doctor.view.fragment

import android.app.Activity
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.getColor
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.task.doctor.R
import com.task.doctor.databinding.FragmentDoctorListBinding
import com.task.doctor.model.Doctor
import com.task.doctor.utils.recycler.infiniteScrollListener
import com.task.doctor.view.adapter.DoctorRecyclerViewAdapter
import com.task.doctor.viewmodel.DoctorListViewModel
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
   private val doctorsViewModel: DoctorListViewModel by viewModel()
   private lateinit var getFragmentContext: Activity
   private var doctor = mutableListOf<Doctor>()
   private lateinit var doctorRecyclerViewAdapter: DoctorRecyclerViewAdapter
   private lateinit var linearLayoutManager: LinearLayoutManager

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      getFragmentContext = this.requireActivity()
   }

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
      if(!::binding.isInitialized) {
         binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_doctor_list, container, false)
         context ?: return binding.root

         binding.viewModel = doctorsViewModel
         binding.executePendingBindings()

         linearLayoutManager =          LinearLayoutManager(getFragmentContext, LinearLayoutManager.VERTICAL, false)
         binding.rcyVwDoctorList.layoutManager = linearLayoutManager
         doctorRecyclerViewAdapter = DoctorRecyclerViewAdapter(doctor)
         binding.rcyVwDoctorList.adapter = doctorRecyclerViewAdapter
         checkNetworkStatus()
         updatingDoctorList()
         addScrollListener()
         errorStatus()

      }
      return binding.root
   }

   /**
    *  OnclickListenter trigger when user clicked the button
    */
   private fun setOnClickListener() {
      binding.btnVivyDoctor.setOnClickListener(this)
      binding.btnRecentDoctor.setOnClickListener(this)
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      setOnClickListener()
   }


   private fun updatingDoctorList() {
      //adds the new set of results to the adapter list
      doctorsViewModel.doctorList.observe(this, Observer<List<Doctor>> {
         this.doctor.addAll(it)
         doctorRecyclerViewAdapter.notifyDataSetChanged()
      })
   }

   /*
   * displaying error status from viewmodel communicate via Observer
   * if the loading fails on load more
   */
   private fun errorStatus() {
      doctorsViewModel.error.observe(this, Observer {
            Toast.makeText(getFragmentContext, it, Toast.LENGTH_SHORT).show()
      })
   }

   /**
    * Check's the network Connectivity
    */
   private fun checkNetworkStatus() {
      doctorsViewModel.checkNetworkConnection(getFragmentContext).observe(this, Observer {
         when (it) {
            it -> if (it != null && it == true) doctorsViewModel.loadDoctorList() else checkNetworkConnectivity()
         }
      })
   }

   /**
    * InfiniteScrollListener onLoad more method is triggered when end of the list
    * is reached
    */
   private fun addScrollListener() {
      binding.rcyVwDoctorList.addOnScrollListener(object : infiniteScrollListener() {
         override fun onLoadMore() {
            doctorsViewModel.loadDoctorListWithKey(vivyDoctorClicked)
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
      checkLastStateUserClicked()
   }

   private fun checkLastStateUserClicked() {
      if (vivyDoctorClicked){
         loadVivyDoctor()
      }else{
         loadRecentDoctor()
      }
   }

   // Checking NetworkStatus
   private fun checkNetworkConnectivity() {
      val builder: AlertDialog.Builder? =
         AlertDialog.Builder(getFragmentContext, R.style.DialogTheme)
      builder!!.setTitle(R.string.str_tittle_network_error)
      builder.setMessage(R.string.str_msg_error_state)
      try {
         builder.setNegativeButton(
            R.string.str_retry,
            DialogInterface.OnClickListener { dialog, _ ->
               checkNetworkStatus()
               dialog.dismiss()
            })
         val dialog: AlertDialog? = builder.setPositiveButton(
            R.string.str_cancel,
            DialogInterface.OnClickListener { _, _ ->
               getFragmentContext.finish()
            }).create()
         dialog!!.setCanceledOnTouchOutside(false)
         dialog.setCancelable(false)
         dialog.setOnShowListener { dialogInterface ->
            val positive = (dialogInterface as AlertDialog).getButton(AlertDialog.BUTTON_POSITIVE)
            positive.setTextColor(getColor(getFragmentContext, R.color.colorDialogText))
            val negative = dialogInterface.getButton(AlertDialog.BUTTON_NEGATIVE)
            negative.setTextColor(getColor(getFragmentContext, R.color.colorDialogText))
         }
         dialog.show()
      } catch (ex: Exception) {
         ex.printStackTrace()
      } finally {
      }
   }
}
