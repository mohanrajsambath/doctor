package com.task.doctor.viewmodel

import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.task.doctor.model.Doctor
import com.task.doctor.network.DoctorDataSource
import com.task.doctor.network.Result
import com.task.doctor.utils.NetworkConnectivity
import com.task.doctor.utils.NonNullMediatorLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/*
 * Copyright (c) 2019. Created for Coding Challenge and Created by R Sathish Kumar on 08-12-2019.
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
 * Created on :08-12-2019 
 * File Name : DoctorListViewModel.kt
 * ClassName : DoctorListViewModel
 * Module Name : app
 * Desc : DoctorListViewModel fetch data from DataSource class.
 * success and failure response from the Response  and
 * update to the view
 *
 */

class DoctorListViewModel(private val doctorDataSource: DoctorDataSource) : ViewModel() {
   private val _doctorLiveData = NonNullMediatorLiveData<List<Doctor>>()
   private val _error = NonNullMediatorLiveData<String>()
   private var doctorJob: Job? = null
   private var networkStatus = MutableLiveData<Boolean>()
   val isLoading = ObservableField(false)
   private  var lastKey: String =""
   val doctorList: MutableLiveData<List<Doctor>> get() = _doctorLiveData
   val error: LiveData<String> get() = _error

   private  var sortingDoctorList: MutableList<Doctor> = mutableListOf()


   override fun onCleared() {
      super.onCleared()
      doctorJob!!.cancel()
   }

   /**
    *Api request and response Handling method
    * first  time service hit from server
    * load data.
    */
   internal fun loadDoctorList() {
      try {
         isLoading.set(true)
         doctorJob = GlobalScope.launch {
            val response = doctorDataSource.getDoctor()
            when (response) {
               is Result.Success -> {
                  isLoading.set(false)
                  lastKey = response.data.lastKey
                   sortingDoctorList.addAll(response.data.doctors)
                 val sortedList = sortingDoctorList.sortedByDescending { it.reviewCount }
                  _doctorLiveData.postValue(sortedList)
               }
               is Result.Error -> {
                  isLoading.set(false)
                  _error.postValue(response.data.message)
               }
            }
         }
      }catch (Io:Exception){
         _error.postValue(Io.message)
      }
   }

   /**
    * Api request and response Handling method with Key when user scroll
    * loadmore
    */
   internal fun loadDoctorListWithKey(userVivyDoctorClicked:Boolean) {
      try {
         if(lastKey!=null) {
            isLoading.set(true)
            doctorJob = GlobalScope.launch {
               val response = doctorDataSource.getDoctorWithKey(lastKey)
               when (response) {
                  is Result.Success -> {
                     isLoading.set(false)
                     lastKey = response.data.lastKey
                     sortingDoctorList.addAll(response.data.doctors)
                    val sortedList = getSortedList(userVivyDoctorClicked)
                     _doctorLiveData.postValue(sortedList)
                  }
                  is Result.Error -> {
                     isLoading.set(false)
                     _error.postValue(response.data.message)
                  }
               }
            }
         }else{
            _error.postValue("Doctor List Ended")
         }
      }catch (Io:Exception){
         _error.postValue(Io.message)
      }

   }

   private fun getSortedList(userClickedVivy:Boolean): List<Doctor>? {
      return if (userClickedVivy) sortingDoctorList.sortedByDescending { it.rating } else sortingDoctorList.sortedByDescending { it.reviewCount }
   }

   /**
    * Sorting the list by rating
    */
   internal fun sortByVivyDoctors(){

      if(sortingDoctorList.size>0){
         val sortedList = sortingDoctorList.sortedByDescending { it.rating }
         _doctorLiveData.postValue(sortedList)
      }
   }

   /**
    * Sorting the list by reviewCount
    */
   internal fun sortByRecentDoctors(){
      if(sortingDoctorList.size>0){
         val sortedList = sortingDoctorList.sortedByDescending { it.reviewCount }
         _doctorLiveData.postValue(sortedList)
         }
   }

   /**
    * Checking internet connection and communicate with view to update information of network status
    */
   internal fun checkNetworkConnection(context: Context): LiveData<Boolean> {
      networkStatus.value = NetworkConnectivity.isNetworkAvailable(context)
      return networkStatus
   }
}