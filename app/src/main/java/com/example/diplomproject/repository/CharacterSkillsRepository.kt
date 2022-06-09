package com.example.diplomproject.repository

import androidx.lifecycle.LiveData
import com.example.diplomproject.data.dao.CharacterSkillsDao
import com.example.diplomproject.data.entities.CharacterSkillsEntity

class CharacterSkillsRepository(private val CharacterSkillsDao: CharacterSkillsDao) {
    val readAllData : LiveData<List<CharacterSkillsEntity>> = CharacterSkillsDao.getCharacterSkills()
}