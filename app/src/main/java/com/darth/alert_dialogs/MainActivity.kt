package com.darth.alert_dialogs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.darth.alert_dialogs.databinding.ActivityMainBinding
import com.darth.alert_dialogs.dialogs.SpinnerFragment

private lateinit var binding : ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.spinnerButton.setOnClickListener {
            val dialog = SpinnerFragment()
            dialog.show(supportFragmentManager, "Spinner Fragment")
        }
    }
}