package com.example.academyhomework

import androidx.room.Entity
import java.io.Serializable

@Entity
data class DataList(
    val itemId: Int,
    val imageId: Int,
    val name: String,
    val surname: String,
    val phone: String,
    val age: Int,
    val birthday: String
) : Serializable