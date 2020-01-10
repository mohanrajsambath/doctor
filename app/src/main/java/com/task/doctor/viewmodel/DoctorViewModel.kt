package com.task.doctor.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.task.doctor.domain.model.DoctorModel
import com.task.doctor.domain.model.Result
import com.task.doctor.domain.usecases.DoctorUseCases
import com.task.doctor.viewmodel.Base.NonNullMediatorLiveData
import com.task.doctor.viewmodel.Base.BaseViewModel
import kotlinx.coroutines.*

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
 * Desc : DoctorListViewModel fetch data from DoctorUseCases
 */

class DoctorViewModel constructor(private val useCase: DoctorUseCases) : BaseViewModel() {

   private val _doctorLiveData =
      NonNullMediatorLiveData<List<DoctorModel>>()
   val doctorList: MutableLiveData<List<DoctorModel>> get() = _doctorLiveData

   private  var sortingDoctorList: MutableList<DoctorModel> = mutableListOf()

   /**
    * Api request and  initial load doctor
    * sets from server
    */
   fun loadData(){
            viewModelScope.launch {
                when(val result = useCase.getDoctorList()){
                   is Result.Success -> {
                      val data = result.data
                      sortingDoctorList.addAll(data)
                      val sortedList = sortingDoctorList.sortedByDescending { it.reviewCount }
                      _doctorLiveData.postValue(sortedList)
                   }
                   is Result.Error -> {
                      error.postValue(result.data)
                   }
                }
               isLoading.set(false)
            }
   }


   /**
    * Api request and response Handling method with Key when user scroll
    * loadMore
    */
   fun loadMoreData(userVivyDoctorClicked:Boolean){
      viewModelScope.launch {
         when(val result = useCase.getDoctorListWithKey()){
            is Result.Success -> {
               val data = result.data
               sortingDoctorList.addAll(data)
               val sortedList = getSortedList(userVivyDoctorClicked)
               _doctorLiveData.postValue(sortedList)
            }
         }
         isLoading.set(false)
      }
   }

   private fun getSortedList(userClickedVivy:Boolean): List<DoctorModel>? {
      return if (userClickedVivy) sortingDoctorList.sortedByDescending { it.rating }
      else sortingDoctorList.sortedByDescending { it.reviewCount }
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
}