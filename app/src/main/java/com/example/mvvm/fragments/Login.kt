package com.example.mvvm.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mvvm.model.data.LogIn
import com.example.mvvm.R
import com.example.mvvm.viewModel.GetUsersViewModel
import com.example.mvvm.viewModel.LoginViewModel
import com.example.mvvm.databinding.FragmentLoginBinding


class Login : Fragment() {
    private lateinit var loginViewModel: LoginViewModel
    private val getUsersViewModel: GetUsersViewModel by viewModels()
    private var emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-z]+\$"
    private var passPattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%^&*_=+-]).{8,}\$"
    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentLoginBinding.inflate(inflater,container,false)
        binding.nextBtn1.setOnClickListener {
            validations()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel= ViewModelProvider(this)[LoginViewModel::class.java]
        loginViewModel.loginResult.observe(viewLifecycleOwner, Observer { loginResponse ->
            if (loginResponse != null) {
                loginResponse.data?.let {
                    val preferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                    preferences.edit().putBoolean("isLoggedIn", true).apply()
                    getUsersViewModel.fetchUsers(it.Token)
                    findNavController().navigate(R.id.action_login_to_home2)
                    val sharedPreferences1 = activity?.getSharedPreferences("Token", Context.MODE_PRIVATE)
                    val editor1 = sharedPreferences1?.edit()
                    editor1.apply {
                        editor1?.putString("Token",
                            getUsersViewModel.fetchUsers(it.Token).toString()
                        ) }?.apply()
                }
            } else {
                Toast.makeText(requireContext(), "Login failed", Toast.LENGTH_SHORT).show()
            }

        })
    }
    private fun validations(){
        if (binding.user1.text.toString().isEmpty()) {
            binding.user1.error = "Email required"
        }
        if (binding.pass1.text.toString().isEmpty()) {
            binding.pass1.error = "Password Required"
        } else {
            if (binding.user1.text.toString().trim { it <= ' ' }.matches(emailPattern.toRegex())
                && binding.pass1.text.toString().trim { it <= ' ' }
                    .matches(passPattern.toRegex())
            ) {
                val email = binding.user1.text.toString()
                val password = binding.pass1.text.toString()
                val loginBody = LogIn(email, password)
                loginViewModel.login(loginBody)
            }
        }

    }


}
