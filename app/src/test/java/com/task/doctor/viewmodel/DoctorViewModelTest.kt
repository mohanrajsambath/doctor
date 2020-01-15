package com.task.doctor.viewmodel

import com.task.doctor.BaseTest
import com.task.doctor.domain.model.DoctorModel
import com.task.doctor.domain.model.Result
import com.task.doctor.domain.usecases.DoctorUseCases
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

/*
 * Project Name : doctor
 * Created by : R Sathish Kumar - Android Application Developer
 * Created on :15-01-2020 
 * File Name : DoctorViewModelTest.kt
 * ClassName : DoctorViewModelTest
 * Module Name : app
 * Desc : 
 */

class DoctorViewModelTest : BaseTest() {

   @Mock
   private lateinit var useCase: DoctorUseCases

   @Mock
   private lateinit var recipeListViewModel: DoctorViewModel

   private val doctorList = emptyList<DoctorModel>()

   @Before
   fun init() {
      MockitoAnnotations.initMocks(this)
      recipeListViewModel = DoctorViewModel(useCase)
   }

   @Test
   fun  loadDoctor_Success () = runBlocking{
      val result = Result.Success(doctorList)
      `when`(useCase.getDoctorList()).thenReturn(result)
      val response = useCase.getDoctorList()
      Assert.assertEquals(result,response)
   }

   @Test
   fun  loadDoctorListWithKey_Success () = runBlocking{
      val result = Result.Success(doctorList)
      `when`(useCase.getDoctorListWithKey()).thenReturn(result)
      val response = useCase.getDoctorListWithKey()
      Assert.assertEquals(result,response)
   }

}