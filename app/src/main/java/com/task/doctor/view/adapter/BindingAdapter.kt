package com.task.doctor.view.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.task.doctor.R

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
 * File Name : BindingAdapter.kt
 * ClassName : BindingAdapter
 * Module Name : app
 * Desc : BindingAdapter which a data bound variable can be bound to a view attribute.
 */

@BindingAdapter("image")
fun setAvatarThumbnail(view: ImageView, imageURL: String?) {
   Picasso.get()
         .load(imageURL)
      .placeholder(R.drawable.ic_png_profile)
      .into(view)
}

