package com.example.academyhomework

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.academyhomework.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fieldList = arrayOf(
            binding.editName,
            binding.editSurname,
            binding.editPhone,
            binding.editAge
        )

        val textWatcher = CustomTextWatcher(fieldList, binding.button1)
        for (editText in fieldList) editText.addTextChangedListener(textWatcher)

        clickWrite()
        clickClear()
    }

    private fun clickWrite() {
        binding.apply {
            button1.setOnClickListener {
                nameTxt.visibility = View.VISIBLE
                surnameTxt.visibility = View.VISIBLE
                phoneTxt.visibility = View.VISIBLE
                ageTxt.visibility = View.VISIBLE
                nameValue.text = editName.text
                surnameValue.text = editSurname.text
                phoneValue.text = editPhone.text
                ageValue.text = editAge.text
                button2.isEnabled = true
            }
        }
    }

    private fun clickClear() {
        binding.apply {
            button2.setOnClickListener {
                nameTxt.visibility = View.INVISIBLE
                surnameTxt.visibility = View.INVISIBLE
                phoneTxt.visibility = View.INVISIBLE
                ageTxt.visibility = View.INVISIBLE
                nameValue.text = ""
                surnameValue.text = ""
                phoneValue.text = ""
                ageValue.text = ""
                button2.isEnabled = false
            }
        }
    }
}