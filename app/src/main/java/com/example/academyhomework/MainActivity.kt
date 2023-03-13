package com.example.academyhomework

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.academyhomework.DataBaseHelper.Companion.TABLE_NAME
import com.example.academyhomework.databinding.ActivityMainBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialDatePicker.INPUT_MODE_TEXT
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val dataList = mutableListOf<DataList>()
    private lateinit var fieldsAdapter: ListAdapter
    lateinit var sqlDb: SQLiteDatabase
    lateinit var cursor: Cursor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dbHelper = DataBaseHelper(this)
        sqlDb = dbHelper.readableDatabase
        cursor = sqlDb.rawQuery("SELECT * FROM $TABLE_NAME", null)

        cursor.moveToFirst()
        sqlDb.needUpgrade(1)
        readDb()

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = layoutManager

        fieldsAdapter = ListAdapter(dataList) { dbPosition, _ ->
            val item = dataList.find { it.itemId == dbPosition }
            dataList.remove(item)
            with(DataBaseHelper) {
                sqlDb.delete(TABLE_NAME, "$COLUMN_ID=$dbPosition", null)
            }
            dataList
        }
        binding.recyclerView.adapter = fieldsAdapter

        setClickListeners(fieldsAdapter)
    }

    private fun readDb() {
        val dbList = mutableListOf<String>()
        val column = cursor.columnNames
        cursor.moveToFirst()
        if (cursor.move(0)) {
            do {
                for (name in column) {
                    val columnIndex = cursor.getColumnIndex(name)
                    dbList.add(cursor.getString(columnIndex))
                }
            } while (cursor.moveToNext())

            dbList.chunked(6) {
                dataList.add(
                    DataList(
                        itemId = it[0].toInt(),
                        name = it[1],
                        surname = it[2],
                        phone = it[3],
                        age = it[4].toInt(),
                        imageId = setImage(it[4].toInt()),
                        birthday = it[5]
                    )
                )
            }
            dbList.clear()
        }
    }

    private fun setClickListeners(fieldsAdapter: ListAdapter) {
        with(binding) {
            setTextWatcher()
            val dbList = mutableListOf<String>()

            btnSetData.setOnClickListener {
                val cv = ContentValues()
                with(binding) {
                    if (!editName.text.isNullOrEmpty() && !editSurname.text.isNullOrEmpty() &&
                        !editPhone.text.isNullOrEmpty() && !editAge.text.isNullOrEmpty() &&
                        !editBirthday.text.isNullOrEmpty()
                    ) {
                        with(DataBaseHelper) {
                            cv.put(COLUMN_NAME, editName.text.toString())
                            cv.put(COLUMN_LASTNAME, editSurname.text.toString())
                            cv.put(COLUMN_PHONE, editPhone.text.toString())
                            cv.put(COLUMN_AGE, editAge.text.toString().toInt())
                            cv.put(COLUMN_BIRTHDAY, editBirthday.text.toString())
                            sqlDb.insert(TABLE_NAME, null, cv)
                        }
                    }
                }
                clearFields()
                dbList.clear()
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

            binding.btnSortByName.setOnClickListener {
                dataList.sortWith(compareBy(String.CASE_INSENSITIVE_ORDER) { it.name })
                fieldsAdapter.notifyDataSetChanged()
            }

            binding.btnSortByAge.setOnClickListener {
                dataList.sortBy { it.age }
                fieldsAdapter.notifyDataSetChanged()
            }

            binding.btnFirstFive.setOnClickListener {
                showFirstFive()
            }
        }
    }

    private fun showFirstFive() {
        dataList.clear()
        fieldsAdapter.notifyDataSetChanged()
        val fiveList = mutableListOf<String>()
        val column = cursor.columnNames
        cursor.moveToFirst()
        for (n in 1..5) {
            for (name in column) {
                val columnIndex = cursor.getColumnIndex(name)
                fiveList.add(cursor.getString(columnIndex))
            }
            cursor.moveToNext()
        }
        fiveList.chunked(6) {
            dataList.add(
                DataList(
                    itemId = it[0].toInt(),
                    name = it[1],
                    surname = it[2],
                    phone = it[3],
                    age = it[4].toInt(),
                    imageId = setImage(it[4].toInt()),
                    birthday = it[5]
                )
            )
        }
    }

    private fun refreshRecycler() {
        dataList.clear()
        fieldsAdapter.notifyDataSetChanged()
        val newDataList = mutableListOf<String>()
        val column = cursor.columnNames
        cursor.moveToFirst()
        do {
            for (name in column) {
                val columnIndex = cursor.getColumnIndex(name)
                newDataList.add(cursor.getString(columnIndex))
            }
        } while (cursor.moveToNext())

        newDataList.chunked(6) {
            dataList.add(
                DataList(
                    itemId = it[0].toInt(),
                    name = it[1],
                    surname = it[2],
                    phone = it[3],
                    age = it[4].toInt(),
                    imageId = setImage(it[4].toInt()),
                    birthday = it[5]
                )
            )
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

        const val DATA = "Data"
    }
}