package com.example.academyhomework

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.academyhomework.databinding.ActivitySecondBinding

const val IS_MAN: String = "Is_man"
const val BIRTHDAY: String = "Birthday"
const val EMAIL: String = "Email"

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
            if (binding.etEmail.text.toString().isValidEmail()) startActivity(intent)
            else {
                val toast = Toast.makeText(applicationContext, "Incorrect email", Toast.LENGTH_LONG)
                toast.show()
            }

            intent.putExtra(INFO, info)
            intent.putExtra(NAME, name)
            intent.putExtra(SURNAME, surname)
            intent.putExtra(PHONE, phone)
            intent.putExtra(AGE, age)
            intent.putExtra(IS_MAN, binding.radioMan.toString())
            intent.putExtra(BIRTHDAY, binding.etBirthday.toString())
            intent.putExtra(EMAIL, binding.etEmail.toString())
            startActivity(intent)
        }

        binding.etBirthday.setOnClickListener {
            binding.calendar.visibility = View.VISIBLE
            binding.layout.setBackgroundColor(Color.GRAY)
            binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
                binding.etBirthday.setText("$dayOfMonth.${month + 1}.$year")
                binding.calendar.visibility = View.GONE
                binding.layout.setBackgroundColor(Color.WHITE)
            }
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