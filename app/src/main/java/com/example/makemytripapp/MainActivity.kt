package com.example.makemytripapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.makemytripapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.navController

        showHideBottomNavigation(navController)

    }

    private fun hideBottomNavigation(){
        binding.bottomNavigationView.visibility = View.GONE
    }

    private fun showBottomNavigation(){
        binding.bottomNavigationView.visibility = View.VISIBLE
    }

    private fun showHideBottomNavigation(navController: NavController){
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.splashFragment,
                R.id.languageFragment,
                R.id.loginFragment,
                R.id.otpFragment-> hideBottomNavigation()
                else -> showBottomNavigation()

            }

        }
    }

}
