package com.example.diplomproject.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "Spells",
    indices = [Index(value = ["Id"], unique = true)]
)

data class SpellsEntity (
    @ColumnInfo(name = "Id")
    @PrimaryKey(autoGenerate = true)
    val Id: Int,

    @ColumnInfo(name = "Name") val Name: String?,

    @ColumnInfo(name = "Spell_Lvl") val Spell_Lvl: Int?,

    @ColumnInfo(name = "Spell_Type") val Spell_Type: String?,

    @ColumnInfo(name = "Impose_Time") val Impose_Time: String?,

    @ColumnInfo(name = "Range") val Range: String?,

    @ColumnInfo(name = "Components") val Components: String?,

    @ColumnInfo(name = "Duration") val Duration: String?,

    @ColumnInfo(name = "Hero_Class") val Hero_Class: String?,

    @ColumnInfo(name = "Archetype") val Archetype: String?,

    @ColumnInfo(name = "Description") val Description: String?,
)
