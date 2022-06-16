package com.example.currencyi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.*
import com.example.currencyi.dialog.FirstDialogCallBack
import com.example.currencyi.dialog.FirstDialogFragment
import com.example.currencyi.fifthFragment.FifthFragment
import com.example.currencyi.firstFragment.FirstFragment
import com.example.currencyi.fourthFragment.FourthFragment
import com.example.currencyi.itemtouchhelper.DragDrop
import com.example.currencyi.itemtouchhelper.ItemTouchDelegate
import com.example.currencyi.itemtouchhelper.SwipeRight
import com.example.currencyi.model.Add
import com.example.currencyi.model.Currency
import com.example.currencyi.secondFragment.SecondFragment
import com.example.currencyi.thirdFragment.ThirdFragment
import com.example.currencyi.ui.Adapter
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