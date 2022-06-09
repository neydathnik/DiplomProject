package com.example.diplomproject.repository

import androidx.lifecycle.LiveData
import com.example.diplomproject.data.dao.StatsDao
import com.example.diplomproject.data.entities.StatsEntity

class StatsRepository(private val StatsDao: StatsDao) {
    val readAllData : LiveData<List<StatsEntity>> = StatsDao.getStats()
}