package com.example.diplomproject.repository

import androidx.lifecycle.LiveData
import com.example.diplomproject.data.dao.CharacterStatsDao
import com.example.diplomproject.data.entities.CharacterStatsEntity

class CharacterStatsRepository(private val CharacterStatsDao: CharacterStatsDao) {
    val readAllData : LiveData<List<CharacterStatsEntity>> = CharacterStatsDao.getCharacterStats()
}