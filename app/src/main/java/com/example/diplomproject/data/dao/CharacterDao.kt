package com.example.diplomproject.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.example.diplomproject.data.entities.CharacterEntity

@Dao
interface CharacterDao {
    // Запрос на получение всех данных из таблицы Character
    @Query("SELECT * FROM Character")
    fun getCharacter(): LiveData<List<CharacterEntity>>

    // Изменение значения в поле Inspiration таблицы Character
    @Query("UPDATE Character SET Inspiration = :Inspiration WHERE (Name = :Name)")
    fun updateCharInsp(Name: String, Inspiration : Int?)
}