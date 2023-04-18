package com.example.mvvm.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm.model.data.Data
import com.example.mvvm.model.details.DataX
import com.example.mvvm.repository.UserRepository

class GetUsersViewModel() : ViewModel() {
    val userRepository = UserRepository()

    private val _users = MutableLiveData<List<DataX>?>()
    val users: LiveData<List<DataX>?>
        get() = _users

    fun fetchUsers(token: String) {
        userRepository.getUsers(token) { userList ->
            _users.postValue(userList)
        }
    }
}