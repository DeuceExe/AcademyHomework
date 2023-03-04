package com.example.academyhomework

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.academyhomework.databinding.ActivityMainBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialDatePicker.INPUT_MODE_TEXT
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataList = mutableListOf<DataList>()

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = layoutManager
        val fieldsAdapter = ListAdapter(dataList) {
            dataList.removeAt(it)
            dataList
        }
        binding.recyclerView.adapter = fieldsAdapter

        with(binding) {

            val fieldList = arrayOf(
                editName,
                editSurname,
                editPhone,
                editAge,
                editBirthday
            )

            val textWatcher = CustomTextWatcher(fieldList, btnSetData)
            for (editText in fieldList) editText.addTextChangedListener(textWatcher)

            btnSetData.setOnClickListener {
                dataList.add(
                    DataList(
                        setImage(editAge.text.toString().toInt()),
                        editName.text.toString(),
                        editSurname.text.toString(),
                        editPhone.text.toString(),
                        editAge.text.toString().toInt(),
                        editBirthday.text.toString()
                    )
                )
                fieldsAdapter.notifyDataSetChanged()
                clearFields()
                btnSecondActivity.isEnabled = true
            }
            editBirthday.setOnClickListener {
                showDatePicker()
            }

            btnSecondActivity.setOnClickListener {
                val intent = Intent(this@MainActivity, SecondActivity::class.java)
                intent.putExtra(DATA, dataList as Serializable)
                startActivity(intent)
            }

            nvMenu.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.users -> Toast.makeText(
                        this@MainActivity,
                        R.string.nv_users,
                        Toast.LENGTH_SHORT
                    ).show()
                    R.id.setting -> Toast.makeText(
                        this@MainActivity,
                        R.string.nv_settings,
                        Toast.LENGTH_SHORT
                    ).show()
                    else -> Toast.makeText(
                        this@MainActivity,
                        R.string.nv_information,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                true
            }
        }
    }

    private fun setImage(age: Int): Int {
        val imageId: Int = when (age) {
            in 0..10 -> R.drawable.ic_baby
            in 11..20 -> R.drawable.ic_schoolboy
            in 21..49 -> R.drawable.ic_man
            else -> R.drawable.ic_elderly
        }
        return imageId
    }

    private fun showDatePicker() {

        val materialDatePicker = MaterialDatePicker.Builder.datePicker()
            .setInputMode(INPUT_MODE_TEXT)
            .setTitleText(resources.getString(R.string.birth_date_hint))
            .build()

        materialDatePicker.show(supportFragmentManager, "TAG")

        materialDatePicker.addOnPositiveButtonClickListener {
            val dateFormat = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
            binding.editBirthday.setText(dateFormat.format(Date(it).time))
        }
    }

    private fun clearFields() {
        with(binding) {
            editName.text?.clear()
            editSurname.text?.clear()
            editPhone.text?.clear()
            editAge.text?.clear()
            editBirthday.text?.clear()
        }
    }

    companion object {

        const val FILENAME = "UserInfo"
        const val DATA = "Data"
    }
}