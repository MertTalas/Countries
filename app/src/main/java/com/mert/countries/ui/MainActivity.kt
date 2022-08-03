package com.mert.countries.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupWithNavController
import com.mert.countries.R
import com.mert.countries.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = findNavHostFragment(R.id.navHostFragment)
        navController = navHostFragment.navController
        binding.bottomNavView.setupWithNavController(navController)
    }

    private fun findNavHostFragment(navHostFragment: Int) = supportFragmentManager.findFragmentById(navHostFragment) as NavHostFragment
}