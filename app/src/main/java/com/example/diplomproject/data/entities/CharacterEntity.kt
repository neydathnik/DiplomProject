package com.example.diplomproject.data.entities

import androidx.room.*


@Entity(
    tableName = "Character",
    indices = [Index(value = ["Id_Char"], unique = true)],
    foreignKeys =
        [
        ForeignKey(entity = ClassesEntity::class,
            parentColumns = ["Id"],
            childColumns = ["Id_Class"]),

        ForeignKey(entity = RacesEntity::class,
             parentColumns = ["Id"],
             childColumns = ["Id_Race"])
        ]
    )

data class CharacterEntity (

    // Создание класса данных с полями как в БД
    @ColumnInfo(name = "Id_Char")
    @PrimaryKey(autoGenerate = true)
    val Id_Char: Int,

    @ColumnInfo(name = "Name") val Name: String,

    @ColumnInfo(name = "Id_Race") val Id_Race: Int?,

    @ColumnInfo(name = "Id_Class") val Id_Class: Int?,

    @ColumnInfo(name = "EP") val EP: Int?,

    @ColumnInfo(name = "Lvl") val Lvl: Int?,

    @ColumnInfo(name = "Hp") val Hp: Int?,

    @ColumnInfo(name = "Max_Hp") val Max_Hp: Int?,

    @ColumnInfo(name = "Inspiration") val Inspiration: Int?,

    @ColumnInfo(name = "Abilitys") val Abilitys: String?,
)