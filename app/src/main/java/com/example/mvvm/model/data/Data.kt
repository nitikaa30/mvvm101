package com.example.mvvm.model.data

import com.google.gson.annotations.SerializedName

data class Data(@SerializedName("email")
                val Email: String,
                @SerializedName("id")
                val Id: Int,

                val Name: String,

                val Token: String)
