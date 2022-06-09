package com.example.diplomproject.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "Character_Stats",
    primaryKeys = ["Id_Char", "Id_Stats"],
    foreignKeys =
    [
        ForeignKey(entity = CharacterEntity::class,
            parentColumns = ["Id_Char"],
            childColumns = ["Id_Char"]),

        ForeignKey(entity = StatsEntity::class,
            parentColumns = ["Id"],
            childColumns = ["Id_Stats"])
    ]
)

data class CharacterStatsEntity (
    @ColumnInfo(name = "Id_Char") val Id_Char: Int,

    @ColumnInfo(name = "Id_Stats") val Id_Stats: Int,

    @ColumnInfo(name = "Stat_Value") val Stat_Value: Int?,

    @ColumnInfo(name = "Stat_Focus") val Stat_Focus: Int?,

    )