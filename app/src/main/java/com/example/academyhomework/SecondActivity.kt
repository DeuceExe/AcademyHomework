package com.example.academyhomework

import android.content.res.Configuration
import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.academyhomework.MainActivity.Companion.DATA
import com.example.academyhomework.databinding.ActivitySecondBinding
import java.io.*

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private val externalState = Environment.getExternalStorageState()

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
        val adapter = ListAdapter(newList) {
            newList.removeAt(it)
            newList
        }
        binding.rv.adapter = adapter

        isFileExist()
    }

    private fun isFileExist() {
        try {
            if (File(MainActivity.EXTERNAL_PATH).exists()) {
                val builder = AlertDialog.Builder(this)
                builder.setTitle(R.string.dialog_title)
                builder.setMessage(R.string.dialog_message)
                builder.setPositiveButton(R.string.dialog_positive_button) { dialog, _ ->
                    writeToFile()
                    dialog.dismiss()
                }
                builder.setNegativeButton(R.string.dialog_negative_button) { dialog, _ ->
                    if (externalState == Environment.MEDIA_MOUNTED) {
                        val file = File(this.getExternalFilesDir(null), MainActivity.EXTERNAL_FILE)
                        BufferedWriter(FileWriter(file)).use {
                            it.write("")
                        }
                    }
                    dialog.dismiss()
                }
                builder.show()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun writeToFile() {
        var externalList = ""

        if (externalState == Environment.MEDIA_MOUNTED) {
            val file = File(this.getExternalFilesDir(null), MainActivity.EXTERNAL_FILE)

            var currentUserData = ""
            binding.root.context.openFileInput(MainActivity.FILENAME).bufferedReader()
                .useLines { lines ->
                    lines.forEach {
                        currentUserData += "$it\n"
                    }
                }

            FileInputStream(file).bufferedReader().use {
                externalList = it.readText()
            }

            BufferedWriter(FileWriter(file)).use {
                it.write(currentUserData)
                it.write(externalList)
            }
        }
    }
}