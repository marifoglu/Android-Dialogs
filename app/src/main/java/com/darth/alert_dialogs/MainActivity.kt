package com.darth.alert_dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.darth.alert_dialogs.databinding.ActivityMainBinding
import com.darth.alert_dialogs.dialogs.SpinnerFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Alert Dialog Spinner Dialog Fragment
        binding.spinnerButton.setOnClickListener {
            val dialog = SpinnerFragment()
            dialog.show(supportFragmentManager, "Spinner Fragment")
        }

        // Alert Dialog Custom
        binding.alertDialogButton.setOnClickListener {
            val alertDialogBinding = layoutInflater.inflate(R.layout.custom_alert_dialog, null)

            val alertDialog = Dialog(this)
            alertDialog.setContentView(alertDialogBinding)

            alertDialog.setCancelable(true)
            alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            alertDialog.show()

            val alertCloseBtn = alertDialogBinding.findViewById<Button>(R.id.alertDialogClose)
            alertCloseBtn.setOnClickListener {
                alertDialog.dismiss()
            }
        }

        // Alert Dialog Classic
        binding.alertDialogCustomButton.setOnClickListener {
            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setTitle("Custom AlertDialog")
            alertDialogBuilder.setMessage("Do you want to save this repository?")
            alertDialogBuilder.setPositiveButton("Yes") { dialog: DialogInterface, _: Int ->
                Toast.makeText(this@MainActivity, "Thanks :)", Toast.LENGTH_LONG).show()
                dialog.dismiss()
            }
            alertDialogBuilder.setNegativeButton("No") { dialog: DialogInterface, _: Int ->
                Toast.makeText(this@MainActivity, "No worries :)", Toast.LENGTH_LONG).show()
                dialog.dismiss()
            }
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }

    }
}