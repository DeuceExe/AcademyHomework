package com.example.academyhomework

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.academyhomework.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("life", "Create Main Activity")

        binding.button2.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
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