package com.example.academyhomework

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.academyhomework.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "Create Activity")

        supportFragmentManager
            .beginTransaction()
            .add(R.id.frameLayout, FirstFragment())
            .commit()

        binding.btnSendData.setOnClickListener {
            replaceFragment(FirstFragment.newInstance("Eva", 25, false))
        }

        binding.btnTemporaryActivity.setOnClickListener {
            val intent = Intent(applicationContext, TemporaryActivity::class.java)
            startActivity(intent)
        }

        binding.btnThirdFragment.setOnClickListener {

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.secondFL, ThirdFragment())
                .commit()
        }
        binding.btnFourthFragment.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.secondFL, FourthFragment())
                .commit()
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameLayout, fragment)
            .commit()
    }

    override fun onStart() {
        Log.d(TAG, "Start Activity")
        super.onStart()
    }

    override fun onResume() {
        Log.d(TAG, "Resume Activity")
        super.onResume()
    }

    override fun onPause() {
        Log.d(TAG, "Pause Activity")
        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "Stop Activity")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(TAG, "Destroy Activity")
        super.onDestroy()
    }

    companion object {

        const val NAME = "bundleName"
        const val AGE = "bundleAge"
        const val IS_MAN = "bundleIsMan"
        const val REQUEST_KEY = "request_key"
        const val TAG = "life"
    }
}