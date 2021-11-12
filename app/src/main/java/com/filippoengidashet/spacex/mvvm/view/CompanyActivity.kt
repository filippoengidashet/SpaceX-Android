package com.filippoengidashet.spacex.mvvm.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.filippoengidashet.spacex.R
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

/**
 * @author Filippo 09/11/2021
 */
@AndroidEntryPoint
class CompanyActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Calendar.getInstance(TimeZone.getTimeZone("UTC")).get(Calendar.YEAR)
        for(i in 1900 until 2022) {
            println(i)
        }

        toolbar = findViewById<Toolbar>(R.id.toolbar).apply {
            title = ""
        }
        setSupportActionBar(toolbar)

        //navController = Navigation.findNavController(this, R.id.main_nav_host)
        val nh = supportFragmentManager.findFragmentById(R.id.main_nav_host) as NavHostFragment
        navController = nh.navController

        toolbar.setupWithNavController(navController)

//        #3. Check for code smells with klint & detekt

//        #./gradlew ktlintCheck
//        #./gradlew detekt check
    }

    private fun showToast(message: String?, length: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, length).show()
    }
}