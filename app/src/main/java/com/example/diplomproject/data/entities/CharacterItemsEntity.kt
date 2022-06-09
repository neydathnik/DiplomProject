package com.example.diplomproject.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "Character_Items",
    primaryKeys = ["Id_Char", "Id_Item"],
    indices = [Index(value = ["Id_Item"])],
    foreignKeys =
    [
        ForeignKey(entity = CharacterEntity::class,
            parentColumns = ["Id_Char"],
            childColumns = ["Id_Char"]),

        ForeignKey(entity = ItemsEntity::class,
            parentColumns = ["Id"],
            childColumns = ["Id_Item"])
    ]
)

data class CharacterItemsEntity (
    @ColumnInfo(name = "Id_Char") val Id_Char: Int,

    @ColumnInfo(name = "Id_Item") val Id_Item: Int,

    @ColumnInfo(name = "Quantity") val Quantity: Int?,

    )