package com.example.diplomproject.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "SpellBook",
    primaryKeys = ["Id_Char", "Id_Spell"],
    foreignKeys =
    [
        ForeignKey(entity = CharacterEntity::class,
            parentColumns = ["Id_Char"],
            childColumns = ["Id_Char"]),

        ForeignKey(entity = SpellsEntity::class,
            parentColumns = ["Id"],
            childColumns = ["Id_Spell"])
    ]
)

data class SpellBookEntity (
    @ColumnInfo(name = "Id_Char") val Id_Char: Int,

    @ColumnInfo(name = "Id_Spell") val Id_Spell: Int,

    )