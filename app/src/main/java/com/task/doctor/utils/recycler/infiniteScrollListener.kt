package com.task.doctor.utils.recycler

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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
 * File Name : infiniteScrollListener.kt
 * ClassName : InfiniteScroll
 * Module Name : app
 * Desc : InfiniteScrollListener adds a scroll listener when the
 * recycler view is scrolled
 */

abstract class infiniteScrollListener : RecyclerView.OnScrollListener() {

   /**
    * The total number of items in the dataSet after the last load
    */
   private var mPreviousTotal = 0
   /**
    * True if we are still waiting for the last set of data to load.
    */
   private var mLoading = true

   override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
      super.onScrolled(recyclerView, dx, dy)

      val visibleItemCount = recyclerView.childCount
      var totalItemCount = 0
      var firstVisibleItem = 0

      recyclerView.layoutManager?.let {
         totalItemCount = it.itemCount
         firstVisibleItem = (it as LinearLayoutManager).findFirstVisibleItemPosition()
      }

      if (mLoading) if (totalItemCount > mPreviousTotal) {
         mLoading = false
         mPreviousTotal = totalItemCount
      }

      val visibleThreshold = 5

      if (!mLoading && totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold) {
         // End has been reached
         onLoadMore()
         mLoading = true
      }
   }


   /**
    * This function is called when the end of the list is reached
    * to load more items
    */
   abstract fun onLoadMore()

   }



