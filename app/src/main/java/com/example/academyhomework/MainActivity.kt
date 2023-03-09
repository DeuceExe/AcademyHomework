package com.example.academyhomework

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        isFileExist()
        saveImage()

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

    override fun onStart() {
        super.onStart()
        val tempData = mutableListOf<String>()
        try {
            if (cacheDir.list().isNotEmpty()) {
                val tempFiles = this.cacheDir.listFiles()
                val file = File(this.cacheDir, tempFiles[0].name)
                file.bufferedReader().useLines { lines ->
                    lines.forEach {
                        tempData.add(it)
                    }
                    tempData.chunked(5) {
                        with(binding) {
                            editName.setText(it[0])
                            editSurname.setText(it[1])
                            editPhone.setText(it[2])
                            editAge.setText(it[3])
                            editBirthday.setText(it[4])
                        }
                    }
                }
                tempFiles[0].delete()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun onStop() {
        super.onStop()
        File.createTempFile(TEMP_FILE, null, this.cacheDir)
        val tempFiles = this.cacheDir.listFiles()
        val file = File(this.cacheDir, tempFiles[0].name)

        val fileWrite = FileWriter(file.absoluteFile)
        val bufferedWriter = BufferedWriter(fileWrite)

        if (cacheDir.list().isNotEmpty()) {
            with(binding) {
                if (!editName.text.isNullOrEmpty() || !editSurname.text.isNullOrEmpty() || !editPhone.text.isNullOrEmpty() || !editAge.text.isNullOrEmpty() || !editBirthday.text.isNullOrEmpty()) {
                    bufferedWriter.write(setTempData(editName))
                    bufferedWriter.write(setTempData(editSurname))
                    bufferedWriter.write(setTempData(editPhone))
                    bufferedWriter.write(setTempData(editAge))
                    bufferedWriter.write(setTempData(editBirthday))
                    bufferedWriter.close()
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

    private fun saveImage() {
        val bitMapImage = BitmapFactory.decodeResource(resources, R.drawable.ic_elderly)
        val bitMapImage2 = BitmapFactory.decodeResource(resources, R.drawable.ic_schoolboy)

        val internalFile = File(INTERNAL_IMAGE_PATH, "elderly.png")
        val externalFile = File(EXTERNAL_IMAGE_PATH, "schoolboy.png")

        var out = FileOutputStream(internalFile)
        try {
            bitMapImage.compress(Bitmap.CompressFormat.PNG, 100, out)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        } finally {
            try {
                out.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        out = FileOutputStream(externalFile)
        try {
            bitMapImage2.compress(Bitmap.CompressFormat.PNG, 100, out)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        } finally {
            try {
                out.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
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

    private fun setTempData(text: TextInputEditText) = text.text.toString().plus("\n")

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
        const val INTERNAL_IMAGE_PATH = "/data/data/com.example.academyhomework/files"
        const val EXTERNAL_IMAGE_PATH = "/sdcard/Android/data/com.example.academyhomework/files/"
    }
}