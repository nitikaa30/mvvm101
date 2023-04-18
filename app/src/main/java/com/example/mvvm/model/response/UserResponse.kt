package com.example.mvvm.model.response

import com.example.mvvm.model.data.Data

data class UserResponse(val code: Int,
                        val data: List<Data>?,
                        val message: String)
