package com.example.mvvm

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.mvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.
        findFragmentById(R.id.nav_host_fragment1) as NavHostFragment
        navController = navHostFragment.navController

        val preferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        if (preferences.getBoolean("isLoggedIn", false)) {
            navController.navigate(R.id.action_splash_to_home2)
        } else {
            navController.navigate(R.id.action_splash_to_login)
        }
    }
}
