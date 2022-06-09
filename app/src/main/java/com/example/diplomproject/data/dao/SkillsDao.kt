package com.example.diplomproject.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.diplomproject.data.entities.SkillsEntity

@Dao
interface SkillsDao {
    @Query("SELECT * FROM Skills")
    fun getSkills(): LiveData<List<SkillsEntity>>
}