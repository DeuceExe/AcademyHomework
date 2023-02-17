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

        val info: String? = intent.getStringExtra(INFO)
        val name: String? = intent.getStringExtra(NAME)
        val surname: String? = intent.getStringExtra(SURNAME)
        val phone: String? = intent.getStringExtra(PHONE)
        val age: String? = intent.getStringExtra(AGE)
        binding.infoText.text = info
        binding.editName.setText(name)
        binding.editSurname.setText(surname)
        binding.editPhone.setText(phone)
        binding.editAge.setText(age)

        binding.button2.setOnClickListener {
            val intent = Intent(applicationContext, SecondActivity::class.java)
            intent.putExtra(INFO, binding.infoText.text.toString())
            intent.putExtra(NAME, binding.editName.text.toString())
            intent.putExtra(SURNAME, binding.editSurname.text.toString())
            intent.putExtra(PHONE, binding.editPhone.text.toString())
            intent.putExtra(AGE, binding.editAge.text.toString())
            startActivity(intent)
        }

        binding.button1.setOnClickListener {
            val age = binding.editAge.text.toString().toInt()
            binding.infoText.text =
                getString(R.string.showText, binding.editName.text, binding.editSurname.text, age)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("Info", binding.infoText.text.toString())
        outState.putString("Name", binding.editName.text.toString())
        outState.putString("Phone", binding.editPhone.text.toString())
        Log.d("life", "onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        binding.infoText.text = savedInstanceState.getString("Info")
        binding.editName.setText(savedInstanceState.getString("Name"))
        binding.editPhone.setText(savedInstanceState.getString("Phone"))
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