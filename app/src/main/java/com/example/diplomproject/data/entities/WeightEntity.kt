package com.example.diplomproject.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "Weight",
    indices = [
        Index(value = ["Id"],
            unique = true)
    ])

data class WeightEntity
    (
    @ColumnInfo(name = "Id")
    @PrimaryKey(autoGenerate = true)
    val Id: Int,

    @ColumnInfo(name = "Weight") val Features: Int?,
)