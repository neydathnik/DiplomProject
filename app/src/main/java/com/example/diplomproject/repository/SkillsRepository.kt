package com.example.diplomproject.repository

import androidx.lifecycle.LiveData
import com.example.diplomproject.data.dao.SkillsDao
import com.example.diplomproject.data.entities.SkillsEntity

class SkillsRepository(private val SkillsDao: SkillsDao) {
    val readAllData : LiveData<List<SkillsEntity>> = SkillsDao.getSkills()
}