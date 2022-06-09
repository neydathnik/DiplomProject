package com.example.diplomproject.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.diplomproject.data.entities.CharacterStatsEntity


@Dao
interface CharacterStatsDao {
    @Query("SELECT * FROM Character_Stats")
    fun getCharacterStats(): LiveData<List<CharacterStatsEntity>>
}