package com.example.academyhomework

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button1 = findViewById<Button>(R.id.button1)
        val editName = findViewById<EditText>(R.id.editName)
        val editSurname = findViewById<EditText>(R.id.editSurname)
        val editPhone = findViewById<EditText>(R.id.editPhone)
        val editAge = findViewById<EditText>(R.id.editAge)
        val infoText = findViewById<TextView>(R.id.infoText)

        button1.isEnabled = false

        val fieldList = arrayOf(
            editName,
            editSurname,
            editPhone,
            editAge
        )

        val textWatcher = CustomTextWatcher(fieldList, button1)
        for (editText in fieldList) editText.addTextChangedListener(textWatcher)

        button1.setOnClickListener {
            val age = editAge.text.toString().toInt()
            infoText.text = getString(R.string.showText, editName.text, editSurname.text, age)
        }
    }
}