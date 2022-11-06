package com.example.bottomnavigationactivity.ui.favorites

import android.app.Application
import android.provider.MediaStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.bottomnavigationactivity.ListOfMusic.AudioModel
import com.example.bottomnavigationactivity.Repository.Favorites
import com.example.bottomnavigationactivity.Repository.FavoritesDAO
import com.example.bottomnavigationactivity.Repository.FavoritesRepository
import com.example.bottomnavigationactivity.Repository.MyDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritesViewModel(application: Application) : AndroidViewModel(application) {
  val readAllData: LiveData<List<Favorites>>
  private val repository: FavoritesRepository

  init {
    val favoritesDAO = MyDatabase.getInstance(application).favoritesDAO()
    repository = FavoritesRepository(favoritesDAO)
    readAllData = repository.readAll
  }

  fun addFavorites(music: Favorites) {
    viewModelScope.launch(Dispatchers.IO) {
      repository.addFavorite(music)
    }
  }

  fun deleteFavorites(music: Favorites) {
    viewModelScope.launch(Dispatchers.IO) {
      repository.deleteFavorite(music)
    }
  }
}