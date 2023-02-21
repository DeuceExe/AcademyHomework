package com.example.academyhomework

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.academyhomework.databinding.ActivityMainBinding

const val INFO: String = "Info"
const val NAME: String = "Name"
const val SURNAME: String = "Surname"
const val PHONE: String = "Phone"
const val AGE: String = "Age"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("life", "Create Main Activity")

        binding.btnSecondActivity.isEnabled = false
        binding.btnWrite.isEnabled = false

        val info: String? = intent.getStringExtra(INFO)
        val name: String? = intent.getStringExtra(NAME)
        val surname: String? = intent.getStringExtra(SURNAME)
        val phone: Int = intent.getIntExtra(PHONE, 0)
        val age: Int = intent.getIntExtra(AGE, 0)

        binding.infoText.text = info
        binding.editName.setText(name)
        binding.editSurname.setText(surname)
        //Чтобы при запуске не сетало 0
        if (phone != 0 && age != 0) {
            binding.editPhone.setText(phone.toString())
            binding.editAge.setText(age.toString())
        }

        binding.btnSecondActivity.setOnClickListener {
            putIntent()
        }

        binding.btnWrite.setOnClickListener {
            val _age = binding.editAge.text.toString().toInt()
            binding.infoText.text =
                getString(R.string.showText, binding.editName.text, binding.editSurname.text, _age)
            binding.btnSecondActivity.isEnabled = true
        }

        val fieldList = arrayOf(
            binding.editName,
            binding.editSurname,
            binding.editPhone,
            binding.editAge
        )

        val textWatcher = CustomTextWatcher(fieldList, binding.btnWrite)
        for (editText in fieldList) editText.addTextChangedListener(textWatcher)

    }

    private fun checkEmptyField(): Boolean {
        binding.apply {
            return !editName.text.isNullOrEmpty() &&
                    !editSurname.text.isNullOrEmpty() &&
                    !editPhone.text.isNullOrEmpty() &&
                    !editAge.text.isNullOrEmpty() &&
                    !infoText.text.isNullOrEmpty()
        }
    }

    private fun putIntent() {
        val intent = Intent(applicationContext, SecondActivity::class.java)
        if (checkEmptyField()) {
            intent.putExtra(INFO, binding.infoText.text.toString())
            intent.putExtra(NAME, binding.editName.text.toString())
            intent.putExtra(SURNAME, binding.editSurname.text.toString())
            intent.putExtra(PHONE, binding.editPhone.text.toString().toInt())
            intent.putExtra(AGE, binding.editAge.text.toString().toInt())
        }
        startActivity(intent)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("Info", binding.infoText.text.toString())
        Log.d("life", "onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        binding.infoText.text = savedInstanceState.getString("Info")
        Log.d("life", "onRestoreInstanceState")
    }

    override fun onStart() {
        super.onStart()
        Log.d("life", "Start Main Activity")
    }

    override fun onResume() {
        super.onResume()
        Log.d("life", "Resume Main Activity")

    }

    override fun onPause() {
        super.onPause()
        Log.d("life", "Pause Main Activity")
    }

    override fun onStop() {
        super.onStop()
        Log.d("life", "Stop Main Activity")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("life", "Destroy Main Activity")
    }
}