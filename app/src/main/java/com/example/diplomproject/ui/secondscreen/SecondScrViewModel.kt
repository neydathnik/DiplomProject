package com.example.diplomproject.ui.secondscreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.diplomproject.data.AppDatabase
import com.example.diplomproject.data.entities.*
import com.example.diplomproject.repository.*
import io.reactivex.rxjava3.disposables.CompositeDisposable

class SecondScrViewModel (application: Application) : AndroidViewModel(application){
    private val compositeDisposable = CompositeDisposable()

    val repositorySkills : SkillsRepository
    val repositoryStats : StatsRepository
    val repositoryCharSkills : CharacterSkillsRepository
    val repositoryCharStats : CharacterStatsRepository
    val repositoryChar : CharacterRepository

    val readAllSkillsData : LiveData<List<SkillsEntity>>
    val readAllStatsData : LiveData<List<StatsEntity>>
    val readAllCharSkillsData : LiveData<List<CharacterSkillsEntity>>
    val readAllCharStatsData : LiveData<List<CharacterStatsEntity>>
    val readAllCharData : LiveData<List<CharacterEntity>>

    init {

        val skillsDao = AppDatabase.getDatabase(application).SkillsDao()
        val statsDao = AppDatabase.getDatabase(application).StatsDao()
        val charSkillsDao = AppDatabase.getDatabase(application).CharacterSkillsDao()
        val charStatsDao = AppDatabase.getDatabase(application).CharacterStatsDao()
        val charDao = AppDatabase.getDatabase(application).CharacterDao()

        repositorySkills = SkillsRepository(skillsDao)
        repositoryStats = StatsRepository(statsDao)
        repositoryCharSkills = CharacterSkillsRepository(charSkillsDao)
        repositoryCharStats = CharacterStatsRepository(charStatsDao)
        repositoryChar = CharacterRepository(charDao)

        readAllSkillsData = repositorySkills.readAllData
        readAllStatsData = repositoryStats.readAllData
        readAllCharSkillsData = repositoryCharSkills.readAllData
        readAllCharStatsData = repositoryCharStats.readAllData
        readAllCharData = repositoryChar.readAllData
    }


    override fun onCleared() {
        compositeDisposable.dispose()
        compositeDisposable.clear()
        super.onCleared()
    }
}

class SecondScrViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(SecondScrViewModel::class.java)) {
            return SecondScrViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}