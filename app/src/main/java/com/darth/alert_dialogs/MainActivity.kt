package com.darth.alert_dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.darth.alert_dialogs.databinding.ActivityMainBinding
import com.darth.alert_dialogs.dialogs.CalendarPickerDialogFragment
import com.darth.alert_dialogs.dialogs.LoginDialogFragment
import com.darth.alert_dialogs.dialogs.SpinnerFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Radio Button Dialog
        binding.radioButton.setOnClickListener {
            val radioDialogBinding = layoutInflater.inflate(R.layout.radio_dialog, null)

            val radioDialog = Dialog(this)
            radioDialog.setContentView(radioDialogBinding)

            radioDialog.setCancelable(true)
            radioDialog.show()

            val radioCloseBtn = radioDialogBinding.findViewById<Button>(R.id.radioDialogClose)
            radioCloseBtn.setOnClickListener {
                radioDialog.dismiss()
            }
            val radioSaveBtn = radioDialogBinding.findViewById<Button>(R.id.radioDialogSave)
            radioSaveBtn.setOnClickListener {
                val radioGroup = radioDialogBinding.findViewById<RadioGroup>(R.id.radioGroup)
                val selectedID = radioGroup.checkedRadioButtonId

                if (selectedID != -1) {
                    val radioButton = radioDialogBinding.findViewById<RadioButton>(selectedID)
                    val selectedValue = radioButton.text.toString()
                    Toast.makeText(this@MainActivity, "Selected Value: $selectedValue", Toast.LENGTH_SHORT).show()
                }

                radioDialog.dismiss()
            }
        }

        // MultiChoose Dialog
        binding.multiChooseButton.setOnClickListener {
            val items = arrayOf("Option 1", "Option 2", "Option 3", "Option 4", "Option 5")
            val checkedItems = booleanArrayOf(false, false, false, false, false)
            val selectedItems = ArrayList<Int>()

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Select Options")
                .setMultiChoiceItems(items, checkedItems) { _, index, isChecked ->
                    if (isChecked) {
                        selectedItems.add(index)
                    } else if (selectedItems.contains(index)) {
                        selectedItems.remove(index)
                    }
                }
                .setPositiveButton("OK") { _, _ ->
                    val selectedOptions = ArrayList<String>()
                    for (index in selectedItems) {
                        selectedOptions.add(items[index])
                    }
                    // Perform any further actions with the selected options
                    // For example, display a Toast message with the selected options
                    val message = "Selected options: ${selectedOptions.joinToString(", ")}"
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Cancel") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }

        // Spinner Dialog
        binding.spinnerButton.setOnClickListener {
            val dialog = SpinnerFragment()
            dialog.show(supportFragmentManager, "Spinner Fragment")
        }

        // Login Dialog
        binding.loginButton.setOnClickListener {
            val dialog = LoginDialogFragment()
            dialog.show(supportFragmentManager, "LoginDialog")
        }

        // Calender selection Dialog
        binding.calenderButton.setOnClickListener {
            val dialog = CalendarPickerDialogFragment()
            dialog.show(supportFragmentManager, "CalendarPickerDialog")
        }
        
        // Alert Custom Dialog
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

        // Alert Classic Dialog
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