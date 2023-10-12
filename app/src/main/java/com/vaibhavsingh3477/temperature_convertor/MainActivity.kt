package com.vaibhavsingh3477.temperature_convertor

import android.app.AlertDialog
import android.app.AlertDialog.Builder
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.vaibhavsingh3477.temperature_convertor.databinding.ActivityMainBinding
import java.text.DecimalFormat


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var selectedUnit: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val df = DecimalFormat("#.##")
        selectedUnit = "Fahrenheit"

        binding.selectType.setOnClickListener() {
            showAlertDialog()
        }

        binding.editInput.addTextChangedListener() {
            val resultText: String
            val inputVal = binding.editInput.text.toString()
            if (inputVal.isNotEmpty()) {
                var doubleInput = inputVal.toDouble()
                if (selectedUnit == "Fahrenheit") {
                    resultText = df.format((doubleInput - 32) * 5 / 9)
                    binding.textResultType.text = "Celcius"
                } else {
                    resultText = df.format((doubleInput * 9 / 5) + 32)
                    binding.textResultType.text = "Fahrenheit"
                }
                binding.textResult.text = resultText
            }

        }

    }

    private fun showAlertDialog() {
        val alertDialog: Builder = Builder(this@MainActivity)
        alertDialog.setTitle("Select Unit")
        val items = arrayOf("Fahrenheit", "Celsius") //Array to contained Options
        val checkedItem = -1
        alertDialog.setSingleChoiceItems(items, checkedItem,
            DialogInterface.OnClickListener { dialog, which ->
                selectedUnit = items[which]
                binding.textType.setText(items[which])
            })
        alertDialog.setPositiveButton(
            android.R.string.ok,
            DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
            })
        val alert: AlertDialog = alertDialog.create()
        alert.setCanceledOnTouchOutside(false)
        alert.show()

    }


    fun celsiusToFarhenheit(celsius: Double): Double {
        return celsius * 9 / 5 + 32
    }

    fun fahrenheitToCelsius(fahrenheit: Double): Double {
        return (fahrenheit - 32) * 5 / 9
    }

    fun celsiusToKelvin(celsius: Double): Double {
        return celsius + 273.15
    }

    fun kelvinToCelsius(kelvin: Double): Double {
        return kelvin - 273.15
    }

    fun fahrenheitToKelvin(fahrenheit: Double): Double {
        return (fahrenheit + 459.67) * 5 / 9
    }

    fun kelvinToFahrenheit(kelvin: Double): Double {
        return kelvin * 9 / 5 - 459.67
    }
}
