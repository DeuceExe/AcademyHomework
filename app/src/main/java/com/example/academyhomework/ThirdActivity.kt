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

        val name: String? = intent.getStringExtra(NAME)
        val surname: String? = intent.getStringExtra(SURNAME)
        val phone: Int = intent.getIntExtra(PHONE, 0)
        val age: Int = intent.getIntExtra(AGE, 0)
        val isMan: Boolean = intent.getBooleanExtra(IS_MAN, false)
        val birthday: String? = intent.getStringExtra(BIRTHDAY)
        val email: String? = intent.getStringExtra(EMAIL)

        if (isMan) {
            binding.layout.setBackgroundResource(R.color.purple_700)
        } else binding.layout.setBackgroundResource(R.color.pink)

        binding.apply {
            tvNameValue.text = name
            tvSurnameValue.text = surname
            tvPhoneValue.text = phone.toString()
            tvAgeValue.text = age.toString()
            if (isMan) tvGenderValue.setText(R.string.man)
            else tvGenderValue.setText(R.string.woman)
            tvBirthdayValue.text = birthday
            tvEmailValue.text = email
        }
    }
}