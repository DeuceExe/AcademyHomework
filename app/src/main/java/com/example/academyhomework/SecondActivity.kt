package com.example.academyhomework

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.academyhomework.databinding.ActivitySecondBinding
import java.util.*


const val IS_MAN: String = "Is_man"
const val BIRTHDAY: String = "Birthday"
const val EMAIL: String = "Email"

class SecondActivity : AppCompatActivity() {

    var mYear = 0
    var mMonth: Int = 0
    var mDay: Int = 0
    var DIALOG_DATE = 1

    private lateinit var binding: ActivitySecondBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val info: String? = intent.getStringExtra(INFO)
        val name: String? = intent.getStringExtra(NAME)
        val surname: String? = intent.getStringExtra(SURNAME)
        val phone: Int = intent.getIntExtra(PHONE, 0)
        val age: Int = intent.getIntExtra(AGE, 0)

        binding.etBirthday.isEnabled = false

        binding.btnMain.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.putExtra(INFO, info)
            intent.putExtra(NAME, name)
            intent.putExtra(SURNAME, surname)
            intent.putExtra(PHONE, phone)
            intent.putExtra(AGE, age)
            startActivity(intent)
        }

        binding.btnThird.setOnClickListener {
            val intent = Intent(applicationContext, ThirdActivity::class.java)

            fun String.isValidEmail() =
                !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

            if (binding.etEmail.text.toString()
                    .isValidEmail() && (binding.radioMan.isChecked || binding.radioWoman.isChecked) &&
                binding.etEmail.text.toString().trim { it <= ' ' }.isNotEmpty() &&
                binding.etBirthday.text.toString().trim { it <= ' ' }.isNotEmpty()
            ) {
                intent.putExtra(NAME, name)
                intent.putExtra(SURNAME, surname)
                intent.putExtra(PHONE, phone)
                intent.putExtra(AGE, age)
                if (binding.radioMan.isChecked) intent.putExtra(IS_MAN, true)
                else intent.putExtra(IS_MAN, false)
                intent.putExtra(BIRTHDAY, binding.etBirthday.text.toString())
                intent.putExtra(EMAIL, binding.etEmail.text.toString())
                startActivity(intent)
            } else {
                val toast = Toast.makeText(
                    applicationContext,
                    "Incorrect email or not all data entered",
                    Toast.LENGTH_LONG
                )
                toast.show()
            }
        }

        binding.imageCalendar.setOnClickListener {
            binding.layout.setBackgroundColor(Color.GRAY)
            binding.datePicker.visibility = View.VISIBLE
            binding.btnSetData.visibility = View.VISIBLE
            datePickerDialog()
            binding.btnSetData.setOnClickListener {
                binding.datePicker.visibility = View.GONE
                binding.layout.setBackgroundColor(Color.WHITE)
                binding.btnSetData.visibility = View.GONE
            }
        }
    }

    private fun datePickerDialog() {
        val picker = binding.datePicker
        val today = Calendar.getInstance()
        picker.init(
            today.get(Calendar.YEAR),
            today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)
        ) { view, year, month, day ->
            val month = month + 1
            binding.etBirthday.setText("$day/$month/$year")
        }

    }
}