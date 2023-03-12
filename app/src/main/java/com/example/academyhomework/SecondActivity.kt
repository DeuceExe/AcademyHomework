package com.example.academyhomework

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.academyhomework.MainActivity.Companion.DATA
import com.example.academyhomework.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val newList: MutableList<DataList> =
            intent.getSerializableExtra(DATA) as MutableList<DataList>

        when (resources.configuration.orientation) {
            Configuration.ORIENTATION_PORTRAIT -> {
                val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                binding.rv.layoutManager = layoutManager
            }
            else -> {
                val layoutManager = GridLayoutManager(this, 2)
                binding.rv.layoutManager = layoutManager
            }
        }
        val adapter = ListAdapter(newList) { _, position ->
            newList.removeAt(position)
            newList
        }
        binding.rv.adapter = adapter
    }
}