package com.example.academyhomework

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.academyhomework.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("life", "Create Second Activity")

        val info: String? = intent.getStringExtra(INFO)
        val name: String? = intent.getStringExtra(NAME)
        val surname: String? = intent.getStringExtra(SURNAME)
        val phone: Int = intent.getIntExtra(PHONE, 0)
        val age: Int = intent.getIntExtra(AGE, 0)

        binding.button.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.putExtra(INFO, info)
            intent.putExtra(NAME, name)
            intent.putExtra(SURNAME, surname)
            intent.putExtra(PHONE, phone)
            intent.putExtra(AGE, age)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("life", "Start Second Activity")
    }

    override fun onResume() {
        super.onResume()
        Log.d("life", "Resume Second Activity")
    }

    override fun onPause() {
        super.onPause()
        Log.d("life", "Pause Second Activity")
    }

    override fun onStop() {
        super.onStop()
        Log.d("life", "Stop Second Activity")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("life", "Destroy Second Activity")
    }
}