package com.example.diplomproject.data.entities

import androidx.room.*


@Entity(
    tableName = "Skills",
    indices = [Index(value = ["Id"], unique = true)],

    foreignKeys = [
       ForeignKey(entity = StatsEntity::class,
            parentColumns = ["Id"],
            childColumns = ["Id_Stat"])
    ]
)

data class SkillsEntity
    (
    @ColumnInfo(name = "Id")
    @PrimaryKey(autoGenerate = true)
    val Id: Int,

    @ColumnInfo(name = "Name") val Name: String?,

    @ColumnInfo(name = "Id_Stat") val Id_Stat : Int
)