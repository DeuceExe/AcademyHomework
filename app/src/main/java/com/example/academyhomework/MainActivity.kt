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
        Log.d("life", "Create Activity")

        supportFragmentManager
            .beginTransaction()
            .add(R.id.frameLayout, FirstFragment())
            .commit()

        binding.btnTemporaryActivity.setOnClickListener {
            val intent = Intent(applicationContext, TemporaryActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        Log.d("life", "Start Activity")
        super.onStart()
    }

    override fun onResume() {
        Log.d("life", "Resume Activity")
        super.onResume()
    }

    override fun onPause() {
        Log.d("life", "Pause Activity")
        super.onPause()
    }

    override fun onStop() {
        Log.d("life", "Stop Activity")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("life", "Destroy Activity")
        super.onDestroy()
    }
}