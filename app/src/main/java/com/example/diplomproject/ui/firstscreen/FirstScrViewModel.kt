package com.example.diplomproject.ui.firstscreen

import android.app.Application
import androidx.lifecycle.*
import com.example.diplomproject.CurCharName
import com.example.diplomproject.data.AppDatabase
import com.example.diplomproject.data.entities.CharacterEntity
import com.example.diplomproject.data.entities.CharacterStatsEntity
import com.example.diplomproject.data.entities.ClassesEntity
import com.example.diplomproject.data.entities.RacesEntity
import com.example.diplomproject.repository.CharacterRepository
import com.example.diplomproject.repository.CharacterStatsRepository
import com.example.diplomproject.repository.ClassesRepository
import com.example.diplomproject.repository.RacesRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.launch

class FirstScrViewModel(application: Application) : AndroidViewModel(application){
    private val compositeDisposable = CompositeDisposable()

    val repositoryChar : CharacterRepository
    val repositoryClass : ClassesRepository
    val repositoryRace : RacesRepository
    val repositoryCharStats : CharacterStatsRepository

    val readAllCharData : LiveData<List<CharacterEntity>>
    val readAllClassData : LiveData<List<ClassesEntity>>
    val readAllRaceData : LiveData<List<RacesEntity>>
    val readAllCharStatsData : LiveData<List<CharacterStatsEntity>>

    init {
        val charDao = AppDatabase.getDatabase(application).CharacterDao()
        val classDao = AppDatabase.getDatabase(application).ClassesDao()
        val racesDao = AppDatabase.getDatabase(application).RacesDao()
        val charStatsDao = AppDatabase.getDatabase(application).CharacterStatsDao()

        repositoryChar = CharacterRepository(charDao)
        repositoryClass = ClassesRepository(classDao)
        repositoryRace = RacesRepository(racesDao)
        repositoryCharStats = CharacterStatsRepository(charStatsDao)

        readAllCharData = repositoryChar.readAllData
        readAllClassData = repositoryClass.readAllData
        readAllRaceData = repositoryRace.readAllData
        readAllCharStatsData = repositoryCharStats.readAllData
    }

    private fun updateInspiration(Name: String, Inspiration: Int?) = viewModelScope.launch {
        repositoryChar.updateCharInsp(Name, Inspiration)
    }

    fun updateCharInsp (Name: String, Inspiration: Int?){
        updateInspiration(Name, Inspiration)
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        compositeDisposable.clear()
        super.onCleared()
    }
}

class FirstScrViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(FirstScrViewModel::class.java)) {
            return FirstScrViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}