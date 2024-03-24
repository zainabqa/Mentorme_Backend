package com.rohakabir.i200552

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeScreen1 : AppCompatActivity() {

    private val homeFragment = HomeScreen()
    private val chatFragment = Chat()
    private val searchFragment = Search()
    private val profileFragment = Profiles()
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homescreen1)

        // Set the initial fragment

        bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        // Set listener for bottom navigation items
        bottomNavigationView .setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    setCurrentFragment(homeFragment)
                    true
                }
                R.id.chat -> {
                    setCurrentFragment(chatFragment)
                    true
                }
                R.id.search -> {
                    setCurrentFragment(searchFragment)
                    true
                }
                R.id.profile -> {
                    setCurrentFragment(profileFragment)
                    true
                }
                else->false
            }

        }
        setCurrentFragment(homeFragment)
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frame_layout, fragment).commit()

    }
//    fun navigateToNextActivity(view: View) {
//        val intent = Intent(this, Profile::class.java)
//        startActivity(intent)
//
//    }
}