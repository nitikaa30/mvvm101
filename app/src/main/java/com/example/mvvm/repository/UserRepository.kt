package com.example.mvvm.repository

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.mvvm.model.data.Data
import com.example.mvvm.model.data.LogIn
import com.example.mvvm.model.details.DataX
import com.example.mvvm.model.details.Details
import com.example.mvvm.model.response.LoginResponse
import com.example.mvvm.model.response.UserResponse
import com.example.mvvm.retrofit.Retrofit

class UserRepository() {


    fun login(login: LogIn, callback: (LoginResponse?) -> Unit) {
        Retrofit.apiInterface.loginUser(login).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                if (response.isSuccessful) {
                    callback(response.body())
                } else {
                    callback(null)
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                callback(null)
            }
        })
    }

    fun getUsers(token: String, callback: (List<DataX>?) -> Unit) {
        Retrofit.apiInterface.getInfo(token).enqueue(object : Callback<Details> {
            override fun onResponse(
                call: Call<Details>,
                response: Response<Details>
            ) {
                if (response.isSuccessful) {
                    val list: ArrayList<DataX>?=response.body()?.data
                    callback(list)
                } else {
                    callback(null)
                }
            }

            override fun onFailure(call: Call<Details>, t: Throwable) {
                callback(null)
            }
        })
    }
}