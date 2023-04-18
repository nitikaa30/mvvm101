package com.example.mvvm.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm.model.data.LogIn
import com.example.mvvm.model.response.LoginResponse
import com.example.mvvm.repository.UserRepository

class LoginViewModel() : ViewModel() {

    val userRepository = UserRepository()

    private val _loginResult = MutableLiveData<LoginResponse?>()
    val loginResult: LiveData<LoginResponse?>
        get() = _loginResult

    fun login(login: LogIn) {
        userRepository.login(login) { loginResponse ->
            _loginResult.postValue(loginResponse)
        }
    }
}
