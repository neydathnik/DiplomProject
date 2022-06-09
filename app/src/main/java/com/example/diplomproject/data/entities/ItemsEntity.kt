package com.example.diplomproject.data.entities

import androidx.room.*

@Entity(
    tableName = "Items",
    indices = [Index(value = ["Id"], unique = true)],
    foreignKeys =
        [
        ForeignKey(entity = MoneyTypeEntity::class,
            parentColumns = ["Id"],
            childColumns = ["Id_MoneyType"]),

        ForeignKey(entity = WeightEntity::class,
            parentColumns = ["Id"],
            childColumns = ["Id_Weight"]),

        ForeignKey(entity = DamageEntity::class,
            parentColumns = ["Id"],
            childColumns = ["Id_Dmg"]),

        ForeignKey(entity = DamageTypeEntity::class,
            parentColumns = ["Id"],
            childColumns = ["Id_DmgType"]),
        ]
)

data class ItemsEntity (
    @ColumnInfo(name = "Id")
    @PrimaryKey(autoGenerate = true)
    val Id: Int,

    @ColumnInfo(name = "Name") val Name: String?,

    @ColumnInfo(name = "Price") val Price: Int?,

    @ColumnInfo(name = "Id_MoneyType") val Id_MoneyType: Int?,

    @ColumnInfo(name = "Id_Weight") val Id_Weight: Int?,

    @ColumnInfo(name = "Id_Dmg") val Id_Dmg: Int?,

    @ColumnInfo(name = "Id_DmgType") val Id_DmgType: Int?,

    @ColumnInfo(name = "Scroll_LVL") val Scroll_LVL: Int?,

    @ColumnInfo(name = "Rarity") val Rarity: Int?,

    @ColumnInfo(name = "Dice_Roll") val Dice_Roll: Int?,

    @ColumnInfo(name = "Dmg_Bonus") val Dmg_Bonus: Int?,
)
