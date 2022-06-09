package com.example.diplomproject.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "Items_Spells",
    primaryKeys = ["Id_Item", "Id_Spell"],
    foreignKeys =
    [
        ForeignKey(entity = ItemsEntity::class,
            parentColumns = ["Id"],
            childColumns = ["Id_Item"]),

        ForeignKey(entity = SpellsEntity::class,
            parentColumns = ["Id"],
            childColumns = ["Id_Spell"])
    ]
)

data class ItemsSpellsEntity (
    @ColumnInfo(name = "Id_Item") val Id_Item: Int,

    @ColumnInfo(name = "Id_Spell") val Id_Spell: Int,

    )