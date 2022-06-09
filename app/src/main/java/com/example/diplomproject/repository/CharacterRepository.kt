package com.example.diplomproject.repository

import androidx.lifecycle.LiveData
import com.example.diplomproject.data.dao.CharacterDao
import com.example.diplomproject.data.entities.CharacterEntity

class CharacterRepository(private val CharacterDao: CharacterDao) {

    // Объявление переменной readAllData который содержит в себе список на основе
    // CharacterEntity и получающий данные с помощью CharacterDao
    val readAllData : LiveData<List<CharacterEntity>> = CharacterDao.getCharacter()

    // Объявление фунуции обновления поля Inspiration в БД
    fun updateCharInsp(Name : String, Inspiration : Int?){
       CharacterDao.updateCharInsp(Name = Name, Inspiration = Inspiration!!)
    }
}