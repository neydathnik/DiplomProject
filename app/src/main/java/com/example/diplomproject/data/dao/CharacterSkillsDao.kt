package com.example.diplomproject.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.diplomproject.data.entities.CharacterSkillsEntity

@Dao
interface CharacterSkillsDao {
    @Query("SELECT * FROM Character_Skills")
    fun getCharacterSkills(): LiveData<List<CharacterSkillsEntity>>
}