package com.example.diplomproject.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "Items_Features",
    primaryKeys = ["Id_Item", "Id_Features"],
    foreignKeys =
    [
        ForeignKey(entity = ItemsEntity::class,
            parentColumns = ["Id"],
            childColumns = ["Id_Item"]),

        ForeignKey(entity = FeaturesEntity::class,
            parentColumns = ["Id"],
            childColumns = ["Id_Features"])
    ]
)

data class ItemsFeaturesEntity (
    @ColumnInfo(name = "Id_Item") val Id_Item: Int,

    @ColumnInfo(name = "Id_Features") val Id_Features: Int,

    )