package com.example.mvvm.api

import com.example.mvvm.model.data.LogIn
import com.example.mvvm.model.details.Details
import com.example.mvvm.model.response.LoginResponse
import com.example.mvvm.model.response.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface API {
    @POST("authaccount/login")
    fun loginUser(@Body registerData: LogIn): Call<LoginResponse>

    @GET("users")
    fun getInfo(@Header("Authorization") token:String): Call<Details>

}