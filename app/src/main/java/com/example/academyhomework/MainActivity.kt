package com.example.academyhomework

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.academyhomework.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button1.isEnabled = false

        val fieldList = arrayOf(
            binding.editName,
            binding.editSurname,
            binding.editPhone,
            binding.editAge
        )
        val textWatcher = CustomTextWatcher(fieldList, binding.button1)
        for (editText in fieldList) editText.addTextChangedListener(textWatcher)

        binding.apply {
            button1.setOnClickListener {
                val age = editAge.text.toString().toInt()
                textView.text = getString(R.string.showText, editName.text, editSurname.text, age)
            }
        }
    }
}