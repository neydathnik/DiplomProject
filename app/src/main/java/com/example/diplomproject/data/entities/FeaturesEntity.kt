package com.example.diplomproject.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "Features",
    indices = [
        Index(value = ["Id"],
            unique = true)
    ])

data class FeaturesEntity
    (
    @ColumnInfo(name = "Id")
    @PrimaryKey(autoGenerate = true)
    val Id: Int,

    @ColumnInfo(name = "Features") val Features: String?,
)