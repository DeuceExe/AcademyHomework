package com.example.academyhomework

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL(
            "CREATE TABLE $TABLE_NAME (" +
                    "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$COLUMN_NAME TEXT, " +
                    "$COLUMN_LASTNAME TEXT, " +
                    "$COLUMN_PHONE TEXT, " +
                    "$COLUMN_AGE INTEGER, " +
                    "$COLUMN_BIRTHDAY);"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    companion object {

        const val DATABASE_NAME = "users.db"
        const val DATABASE_VERSION = 1

        const val TABLE_NAME = "user"
        const val COLUMN_ID = "_id"
        const val COLUMN_NAME = "name"
        const val COLUMN_LASTNAME = "surname"
        const val COLUMN_PHONE = "phone"
        const val COLUMN_AGE = "age"
        const val COLUMN_BIRTHDAY = "birthday"
    }
}