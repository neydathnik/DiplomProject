package com.example.diplomproject.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index


@Entity(
    tableName = "Character_Skills",
    primaryKeys = ["Id_Char", "Id_Skills"],
    indices = [Index(value = ["Id_Char"])],
    foreignKeys =
    [
        ForeignKey(entity = CharacterEntity::class,
            parentColumns = ["Id_Char"],
            childColumns = ["Id_Char"]),

        ForeignKey(entity = SkillsEntity::class,
            parentColumns = ["Id"],
            childColumns = ["Id_Skills"])
    ]
)

data class CharacterSkillsEntity (

    @ColumnInfo(name = "Id_Char") val Id_Char: Int,

    @ColumnInfo(name = "Id_Skills") val Id_Skills: Int,

    @ColumnInfo(name = "Skill_Focus") val Skill_Focus: Int?,

    )