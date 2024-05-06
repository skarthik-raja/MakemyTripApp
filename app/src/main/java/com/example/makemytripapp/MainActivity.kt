package com.example.makemytripapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.makemytripapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

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
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNav.setupWithNavController(navController)

        bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homemenu -> {
                    navController.navigate(R.id.home_Fragment_)
                    true
                }
                R.id.mytripsmenu -> {
                    navController.navigate(R.id.mytripsfragment)
                    true
                }
                R.id.tripmoney->{
                    navController.navigate(R.id.tripmoneyfragment)
                    true
                }
                R.id.where2go->{
                    navController.navigate(R.id.gofragment)
                    true
                }
                else -> false
            }
        }

    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
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
