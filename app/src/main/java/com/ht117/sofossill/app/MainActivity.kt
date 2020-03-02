package com.ht117.sofossill.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.ht117.sofossill.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayUseLogoEnabled(true)

        toolbar.setupWithNavController(findNavController(this, R.id.container))
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(this, R.id.container)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}
