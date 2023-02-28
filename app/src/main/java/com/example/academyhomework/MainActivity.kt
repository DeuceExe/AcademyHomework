package com.example.academyhomework

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.academyhomework.databinding.ActivityMainBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialDatePicker.INPUT_MODE_TEXT

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var dataList = mutableListOf<Any>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btnSetData.isEnabled = false

            val fieldList = arrayOf(
                editName,
                editSurname,
                editPhone,
                editAge,
                editBirthday
            )

            val textWatcher = CustomTextWatcher(fieldList, btnSetData)
            for (editText in fieldList) editText.addTextChangedListener(textWatcher)

            btnSetData.setOnClickListener {
                dataList.add(0, editName.text.toString())
                dataList.add(1, editSurname.text.toString())
                dataList.add(2, editAge.text.toString().toInt())
                dataList.add(3, editPhone.text.toString())
                dataList.add(4, editBirthday.text.toString())
            }

            editBirthday.setOnClickListener {
                showDatePicker()
            }
        }

    }

    private fun showDatePicker() {

        val materialDatePicker = MaterialDatePicker.Builder.datePicker()
            .setInputMode(INPUT_MODE_TEXT)
            .setTitleText("Select your date of birth")
            .build()

        materialDatePicker.addOnPositiveButtonClickListener {
            binding.editBirthday.setText(materialDatePicker.headerText)
            materialDatePicker.setMenuVisibility(true)
        }

        materialDatePicker.show(supportFragmentManager, "TAG")
    }

}
