package com.example.diplomproject.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.diplomproject.data.entities.*
import com.example.diplomproject.data.dao.*

@Database(
    entities =
        [
            CharDescriptionEntity::class,
            CharacterEntity::class,
            CharacterSkillsEntity::class,
            CharacterStatsEntity::class,
            CharacterItemsEntity::class,
            ClassesEntity::class,
            DamageEntity::class,
            DamageTypeEntity::class,
            FeaturesEntity::class,
            ItemsEntity::class,
            ItemsFeaturesEntity::class,
            ItemsSpellsEntity::class,
            MoneyEntity::class,
            MoneyTypeEntity::class,
            RacesEntity::class,
            SkillsEntity::class,
            SpellBookEntity::class,
            SpellsEntity::class,
            StatsEntity::class,
            WeightEntity::class
        ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun CharacterDao() : CharacterDao
    abstract fun ClassesDao() : ClassesDao
    abstract fun RacesDao() : RacesDao
    abstract fun CharacterStatsDao() : CharacterStatsDao
    abstract fun SkillsDao() : SkillsDao
    abstract fun StatsDao() : StatsDao
    abstract fun CharacterSkillsDao() : CharacterSkillsDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            return INSTANCE?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .createFromAsset("DnDdb.db")
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}