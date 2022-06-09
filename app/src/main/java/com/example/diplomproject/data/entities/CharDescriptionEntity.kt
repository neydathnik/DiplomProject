package com.example.diplomproject.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "CharDescriptionEntity",
    foreignKeys =
    [
        ForeignKey(entity = CharacterEntity::class,
            parentColumns = ["Id_Char"],
            childColumns = ["Id_Char"])
    ]
)


data class CharDescriptionEntity (

    @ColumnInfo (name = "Id_Description")
    @PrimaryKey (autoGenerate = true)
    val Id_Description: Int,

    @ColumnInfo(name = "Id_Char") val Id_Char: Int,

    @ColumnInfo(name = "Traits") val Traits: String?,

    @ColumnInfo(name = "Ideals") val Ideals: String?,

    @ColumnInfo(name = "Ties") val Ties: String?,

    @ColumnInfo(name = "Flaws") val Flaws: String?,

    @ColumnInfo(name = "Characteristic") val Characteristic: String?,
)