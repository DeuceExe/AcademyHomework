package com.example.academyhomework

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
        Log.d("life", "Create Activity")
    }

    override fun onStart() {
        super.onStart()
        Log.d("life", "Start Activity")
    }

    override fun onResume() {
        super.onResume()
        Log.d("life", "Resume Activity")
    }

    override fun onPause() {
        super.onPause()
        Log.d("life", "Pause Activity")
    }

    override fun onStop() {
        super.onStop()
        Log.d("life", "Stop Activity")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("life", "Destroy Activity")
    }
}