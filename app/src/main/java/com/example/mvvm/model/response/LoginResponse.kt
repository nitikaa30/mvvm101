package com.example.mvvm.model.response

import com.example.mvvm.model.data.Data

data class LoginResponse(val code: Int,
                         val `data`: Data?,
                         val message: String)
