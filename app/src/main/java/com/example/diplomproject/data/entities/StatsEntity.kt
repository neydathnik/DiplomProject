package com.example.diplomproject.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "Stats",
    indices = [
        Index(value = ["Id"],
            unique = true)
    ])

data class StatsEntity
    (
    @ColumnInfo(name = "Id")
    @PrimaryKey(autoGenerate = true)
    val Id: Int,

    @ColumnInfo(name = "Name") val Name: String?,
)