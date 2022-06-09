package com.example.diplomproject.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "Damage_Type",
    indices = [
        Index(value = ["Id"],
            unique = true)
    ])

data class DamageTypeEntity
    (
    @ColumnInfo(name = "Id")
    @PrimaryKey(autoGenerate = true)
    val Id: Int,

    @ColumnInfo(name = "Damage_Type") val Damage_Type: String?,
)
