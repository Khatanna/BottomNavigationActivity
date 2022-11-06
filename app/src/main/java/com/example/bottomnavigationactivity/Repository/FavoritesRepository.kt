package com.example.bottomnavigationactivity.Repository

import com.example.bottomnavigationactivity.ListOfMusic.AudioModel

class FavoritesRepository(private val favoritesDAO: FavoritesDAO) {
  val readAll = favoritesDAO.getAll()

  suspend fun addFavorite(music: Favorites) {
    favoritesDAO.insert(music)
  }

  suspend fun deleteFavorite(music: Favorites) {
    favoritesDAO.delete(music)
  }
}