package com.example.bottomnavigationactivity.ui.favorites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.bottomnavigationactivity.Repository.Music.Music
import com.example.bottomnavigationactivity.Repository.Music.MusicRepository
import com.example.bottomnavigationactivity.Repository.MyDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritesViewModel(application: Application) : AndroidViewModel(application) {
  private val repository: MusicRepository
  val readAllData: LiveData<List<Music>>

  init {
    val favoritesDAO = MyDatabase.getInstance(application).MusicDAO()
    repository = MusicRepository(favoritesDAO)
    readAllData = repository.readAll
  }

  fun setFavorites(music: Music, isFavorite: Boolean) {
    viewModelScope.launch(Dispatchers.IO) {
      music.apply {
        val updateMusic = Music(path, title, duration, albumId, isFavorite)
        repository.updateMusic(updateMusic)
      }
    }
  }
}