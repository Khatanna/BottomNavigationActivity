package com.example.bottomnavigationactivity.Repository.Music

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MusicDAO {
  @Query("SELECT * FROM Music")
  fun getAll(): LiveData<List<Music>>

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  suspend fun insert(musics: List<Music>)

  @Delete
  suspend fun delete(music: Music)

  @Update
  suspend fun update(music: Music)
}