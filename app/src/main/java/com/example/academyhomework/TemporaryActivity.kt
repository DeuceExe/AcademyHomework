package com.example.academyhomework

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.academyhomework.databinding.ActivityTemporaryBinding

class TemporaryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTemporaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTemporaryBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}