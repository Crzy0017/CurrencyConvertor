package com.example.currencyi.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.currencyi.R
import com.example.currencyi.presentation.fifthfragment.FifthFragment
import com.example.currencyi.presentation.firstfragment.FirstFragment
import com.example.currencyi.presentation.fourthfragment.FourthFragment
import com.example.currencyi.presentation.secondfragment.SecondFragment
import com.example.currencyi.presentation.thirdfragment.ThirdFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupFragment()
    }

    private fun setupFragment() {
        bottomNavigation = findViewById(R.id.bottom_navigation)
        bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.firstFragment -> {
                    val transaction = supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_navigation, FirstFragment(), "MainFragment")
                    transaction.addToBackStack(null)
                    transaction.commit()
                    true
                }
                R.id.secondFragment -> {
                    val transaction = supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_navigation, SecondFragment(), "MainFragment")
                    transaction.addToBackStack(null)
                    transaction.commit()
                    true
                }
                R.id.thirdFragment -> {
                    val transaction = supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_navigation, ThirdFragment(), "MainFragment")
                    transaction.addToBackStack(null)
                    transaction.commit()
                    true
                }
                R.id.fourthFragment -> {
                    val transaction = supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_navigation, FourthFragment(), "MainFragment")
                    transaction.addToBackStack(null)
                    transaction.commit()
                    true
                }
                R.id.fifthFragment -> {
                    val transaction = supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_navigation, FifthFragment(), "MainFragment")
                    transaction.addToBackStack(null)
                    transaction.commit()
                    true
                }
                else -> false
            }
        }
    }
}