package com.example.diplomproject.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.diplomproject.data.entities.StatsEntity

@Dao
interface StatsDao {
    @Query("SELECT * FROM Stats")
    fun getStats(): LiveData<List<StatsEntity>>
}