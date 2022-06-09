package com.example.diplomproject.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "Money",
    primaryKeys = ["Id_Char", "Money_Type"],
    foreignKeys =
    [
        ForeignKey(entity = CharacterEntity::class,
            parentColumns = ["Id_Char"],
            childColumns = ["Id_Char"]),

        ForeignKey(entity = MoneyTypeEntity::class,
            parentColumns = ["Id"],
            childColumns = ["Money_Type"])
    ]
)

data class MoneyEntity (
    @ColumnInfo(name = "Id_Char") val Id_Char: Int,

    @ColumnInfo(name = "Money_Type") val Money_Type: Int,

    @ColumnInfo(name = "Value") val Value: Int?,

    )