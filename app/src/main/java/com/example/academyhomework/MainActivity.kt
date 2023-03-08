package com.example.academyhomework

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.academyhomework.databinding.ActivityMainBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialDatePicker.INPUT_MODE_TEXT
import com.google.android.material.textfield.TextInputEditText
import java.io.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val dataList = mutableListOf<DataList>()
    private lateinit var fieldsAdapter: ListAdapter
    private val externalState = Environment.getExternalStorageState()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        isFileExist()

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = layoutManager
        fieldsAdapter = ListAdapter(dataList) {
            dataList.removeAt(it)
            binding.root.context.openFileOutput(FILENAME, Context.MODE_PRIVATE).use { file ->
                repeat(dataList.size) { index ->
                    file.write(dataList[index].name.plus("\n").toByteArray())
                    file.write(dataList[index].surname.plus("\n").toByteArray())
                    file.write(dataList[index].phone.plus("\n").toByteArray())
                    file.write(dataList[index].age.toString().plus("\n").toByteArray())
                    file.write(dataList[index].birthday.plus("\n").toByteArray())
                }
            }
            dataList
        }
        binding.recyclerView.adapter = fieldsAdapter

        setClickListeners(fieldsAdapter)
    }

    override fun onStop() {
        super.onStop()
        val file = File.createTempFile(TEMP_FILE, null, this.externalCacheDir)
        BufferedWriter(FileWriter(file)).use {
            it.write(binding.editName.text.toString())
            it.write(binding.editSurname.text.toString())
            it.write(binding.editPhone.text.toString())
            it.write(binding.editAge.text.toString())
            it.write(binding.editBirthday.text.toString())
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        Log.d("restore", "TempFile")
        if (externalState == Environment.MEDIA_MOUNTED) {
            val currentUserData = mutableListOf<String>()
            FileInputStream(TEMP_FILE).bufferedReader().useLines { lines ->
                lines.forEach {
                    currentUserData.add(it)
                }

                currentUserData.chunked(5) {
                    binding.editName.setText(it[0])
                    binding.editSurname.setText(it[1])
                    binding.editPhone.setText(it[2])
                    binding.editAge.setText(it[3])
                    binding.editBirthday.setText(it[4])
                }
            }
        }
    }

    private fun setClickListeners(fieldsAdapter: ListAdapter) {
        with(binding) {
            setTextWatcher()
            btnSetData.setOnClickListener {
                writeToFile()
                returnUserList()
                fieldsAdapter.notifyDataSetChanged()
                clearFields()
                btnSecondActivity.isEnabled = true
            }
            editBirthday.setOnClickListener {
                showDatePicker()
            }

            btnSecondActivity.setOnClickListener {
                checkExternalState()
                val intent = Intent(this@MainActivity, SecondActivity::class.java)
                intent.putExtra(DATA, dataList as Serializable)
                startActivity(intent)
            }

            nvMenu.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.users -> Toast.makeText(
                        this@MainActivity, R.string.nv_users, Toast.LENGTH_SHORT
                    ).show()
                    R.id.setting -> Toast.makeText(
                        this@MainActivity, R.string.nv_settings, Toast.LENGTH_SHORT
                    ).show()
                    else -> Toast.makeText(
                        this@MainActivity, R.string.nv_information, Toast.LENGTH_SHORT
                    ).show()
                }
                true
            }
        }
    }

    private fun checkExternalState() {
        val externalState = Environment.getExternalStorageState()
        var externalList = ""
        binding.root.context.openFileInput(FILENAME).bufferedReader().useLines { lines ->
            lines.forEach {
                externalList += "$it\n"
            }
        }
        if (externalState == Environment.MEDIA_MOUNTED) {
            val file = File(this.getExternalFilesDir(null), EXTERNAL_FILE)
            BufferedWriter(FileWriter(file)).use {
                it.write(externalList)
            }
        }
    }

    private fun isFileExist() {
        try {
            if (File(INTERNAL_PATH).exists()) {
                val builder = AlertDialog.Builder(this)
                builder.setTitle(R.string.dialog_title)
                builder.setMessage(R.string.dialog_message)
                builder.setPositiveButton(R.string.dialog_positive_button) { dialog, _ ->
                    returnUserList()
                    dialog.dismiss()
                    binding.btnSecondActivity.isEnabled = true
                }
                builder.setNegativeButton(R.string.dialog_negative_button) { dialog, _ ->
                    File(INTERNAL_PATH).delete()
                    /*binding.root.context.openFileOutput(FILENAME, Context.MODE_PRIVATE).use {
                        it.write("".toByteArray())
                    }*/
                    dialog.dismiss()
                }
                builder.show()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun writeToFile() {
        var result = ""

        if (File(INTERNAL_PATH).exists()) {
            binding.root.context.openFileInput(FILENAME).bufferedReader().useLines { lines ->
                lines.forEach {
                    result += "$it\n"
                }
            }
        }
        binding.root.context.openFileOutput(FILENAME, Context.MODE_PRIVATE).use {
            it.write(result.toByteArray())
            it.write(getStringToFileOutput(binding.editName))
            it.write(getStringToFileOutput(binding.editSurname))
            it.write(getStringToFileOutput(binding.editPhone))
            it.write(getStringToFileOutput(binding.editAge))
            it.write(getStringToFileOutput(binding.editBirthday))
        }
    }

    private fun returnUserList() {
        dataList.clear()
        val currentUserData = mutableListOf<String>()
        binding.root.context.openFileInput(FILENAME).bufferedReader().useLines { lines ->
            lines.forEach {
                currentUserData.add(it)
            }
            currentUserData.chunked(5) {
                dataList.add(
                    DataList(
                        name = it[0],
                        surname = it[1],
                        phone = it[2],
                        age = it[3].toInt(),
                        imageId = setImage(it[3].toInt()),
                        birthday = it[4]
                    )
                )
            }
        }
        fieldsAdapter.notifyDataSetChanged()
    }

    private fun getStringToFileOutput(text: TextInputEditText) =
        text.text.toString().plus("\n").toByteArray()

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
        val materialDatePicker =
            MaterialDatePicker.Builder.datePicker().setInputMode(INPUT_MODE_TEXT)
                .setTitleText(resources.getString(R.string.birth_date_hint)).build()

        materialDatePicker.addOnPositiveButtonClickListener {
            val dateFormat = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
            binding.editBirthday.setText(dateFormat.format(Date(it).time))
        }
        materialDatePicker.show(supportFragmentManager, "TAG")
    }

    private fun setTextWatcher() {
        with(binding) {
            val fieldList = arrayOf(
                editName, editSurname, editPhone, editAge, editBirthday
            )
            val textWatcher = CustomTextWatcher(fieldList, btnSetData)
            for (editText in fieldList) editText.addTextChangedListener(textWatcher)
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

        const val EXTERNAL_PATH =
            "/sdcard/Android/data/com.example.academyhomework/files/ExternalFile"
        const val INTERNAL_PATH = "/data/data/com.example.academyhomework/files/UserInfo"
        const val FILENAME = "UserInfo"
        const val EXTERNAL_FILE = "ExternalFile"
        const val TEMP_FILE = "Temp"
        const val DATA = "Data"
    }
}