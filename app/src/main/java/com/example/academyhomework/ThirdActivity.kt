package com.example.academyhomework

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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

        binding.tvPhoneValue.setOnClickListener {
            showCallApp()
        }

        binding.imageCall.setOnClickListener {
            showCallApp()
        }

        binding.tvEmailValue.setOnClickListener {
            writeEmail()
        }

        binding.imageEmail.setOnClickListener {
            writeEmail()
        }
    }

    private fun showCallApp() {
        if (ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.CALL_PHONE), REQUEST_CODE
            )
        } else {
            call()
        }
    }

    private fun writeEmail() {
        val emailIntent = Intent(Intent.ACTION_SENDTO)
        emailIntent.data = Uri.parse("mailto:")

        startActivity(Intent.createChooser(emailIntent, "Send mail..."))
        finish()
    }

    private fun call() {
        val dialIntent = Intent(Intent.ACTION_CALL)
        dialIntent.data = Uri.parse("tel:" + binding.tvPhoneValue.text.toString())
        startActivity(dialIntent)
    }

    companion object {
        const val REQUEST_CODE = 1
    }
}