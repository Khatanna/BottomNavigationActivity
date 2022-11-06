package com.example.bottomnavigationactivity.Repository

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.bottomnavigationactivity.ListOfMusic.AudioModel

@Dao
interface FavoritesDAO {
  @Query("SELECT * FROM Favorites")
  fun getAll(): LiveData<List<Favorites>>

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  suspend fun insert(music: Favorites)

  @Delete
  suspend fun delete(music: Favorites)
}