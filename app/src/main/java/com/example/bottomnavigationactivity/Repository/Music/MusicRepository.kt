package com.example.bottomnavigationactivity.Repository.Music

class MusicRepository(private val favoritesDAO: MusicDAO) {
  val readAll = favoritesDAO.getAll()

  suspend fun addMusic(music: List<Music>) {
    favoritesDAO.insert(music)
  }

  suspend fun updateMusic(music: Music) {
    favoritesDAO.update(music)
  }

  suspend fun deleteMusic(music: Music) {
    favoritesDAO.delete(music)
  }
}