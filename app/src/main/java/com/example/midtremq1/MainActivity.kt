package com.example.midtremq1

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var num1EditText: EditText
    private lateinit var num2EditText: EditText
    private lateinit var resultTextView: TextView
    private lateinit var operationSpinner: Spinner
    private lateinit var calculateButton: Button
    private var selectedOperation: String = "Add"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        num1EditText = findViewById(R.id.num1)
        num2EditText = findViewById(R.id.num2)
        resultTextView = findViewById(R.id.result)
        operationSpinner = findViewById(R.id.operation_spinner)
        calculateButton = findViewById(R.id.calculate_button)

        val operations = arrayOf("Add", "Subtract", "Multiply", "Divide")
        operationSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, operations)

        operationSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                selectedOperation = operations[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        calculateButton.setOnClickListener {
            performCalculation()
        }
    }

    private fun performCalculation() {
        val num1 = num1EditText.text.toString().toDoubleOrNull()
        val num2 = num2EditText.text.toString().toDoubleOrNull()

        if (num1 == null || num2 == null) {
            Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show()
            return
        }

        val result = when (selectedOperation) {
            "Add" -> num1 + num2
            "Subtract" -> num1 - num2
            "Multiply" -> num1 * num2
            "Divide" -> {
                if (num2 == 0.0) {
                    Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show()
                    return
                } else {
                    num1 / num2
                }
            }
            else -> 0.0
        }

        resultTextView.text = result.toString()
    }
}
