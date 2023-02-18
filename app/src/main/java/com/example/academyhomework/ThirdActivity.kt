package com.example.academyhomework

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.academyhomework.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val info: String? = intent.getStringExtra(INFO)
        val name: String? = intent.getStringExtra(NAME)
        val surname: String? = intent.getStringExtra(SURNAME)
        val phone: Int = intent.getIntExtra(PHONE, 0)
        val age: Int = intent.getIntExtra(AGE, 0)
        val isMan: Boolean = intent.getBooleanExtra(IS_MAN, false)
        val birthday: String? = intent.getStringExtra(BIRTHDAY)
        val email: String? = intent.getStringExtra(EMAIL)
    }
}