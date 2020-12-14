package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.myapplication.ui.*
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(){

    private lateinit var bottomAppBar: BottomAppBar
    private lateinit var floatingActionButton: FloatingActionButton
    private var isFabTapped: Boolean = false
    private lateinit var menuItem: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomAppBar = findViewById(R.id.bottomAppBar)
        setSupportActionBar(bottomAppBar)
        floatingActionButton = findViewById(R.id.fab)
        if (savedInstanceState == null) {
            handleFrame(TopEmployeeFragment())
        }
        handleFab()

        bottomAppBar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.department->
                    handleFrame(DepartmentFragment())
                R.id.top_emp->handleFrame(TopEmployeeFragment())
                R.id.profile->handleFrame(ProfileFragment())
                R.id.stats -> handleFrame(StatsFillFragment())
                else -> handleFrame(TopEmployeeFragment())
            }
        }



    }

    private fun handleFab() {
        floatingActionButton.setOnClickListener {
            isFabTapped = !isFabTapped
            if (isFabTapped) {
                startActivity(Intent(this,QrCodeScanner().javaClass))
                finish()
                //handleFrame(FabFragment())
            } else {
                handleFrame(TopEmployeeFragment())
            }
        }
    }

    private fun handleFrame(fragment:Fragment): Boolean {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
//        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out)
        fragmentTransaction.replace(R.id.frame1,fragment).commit()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottomappbar_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

}