package com.example.bottomnavigationactivity.ui.myMusic

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.bottomnavigationactivity.Repository.Music.Music
import com.example.bottomnavigationactivity.Repository.Music.MusicRepository
import com.example.bottomnavigationactivity.Repository.MyDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyMusicViewModel(application: Application) : AndroidViewModel(application) {
  private val musicRepository: MusicRepository
  val listOfMusic: LiveData<List<Music>>

  init {
    val musicDAO = MyDatabase.getInstance(application).MusicDAO()
    musicRepository = MusicRepository(musicDAO)
    listOfMusic = musicRepository.readAll
  }

  fun databaseIsEmpty (): Boolean {
    return musicRepository.readAll.value?.isEmpty() ?: true
  }

  fun addMusic(musics: List<Music>) {
    viewModelScope.launch(Dispatchers.IO) {
      musicRepository.addMusic(musics)
    }
  }
}