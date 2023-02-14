package com.example.academyhomework

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.academyhomework.databinding.ActivityFourthBinding


class FourthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFourthBinding

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFourthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var toggle = true
        binding.apply {
            cardOperator.setOnClickListener {
                toggle = if (toggle) {
                    imageView3.setBackgroundColor(getColor(R.color.white))
                    imageView3.setColorFilter(getColor(R.color.phoneStyle))
                    false
                } else {
                    imageView3.setBackgroundColor(getColor(R.color.phoneStyle))
                    imageView3.setColorFilter(getColor(R.color.white))
                    true
                }
            }
        }
    }
}