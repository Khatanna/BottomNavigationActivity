package com.example.bottomnavigationactivity.Repository.PlayList

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Transaction
import com.example.bottomnavigationactivity.Repository.Music.Music
import com.example.bottomnavigationactivity.Repository.PlaylistMusic.PlaylistWithSongs
import com.example.bottomnavigationactivity.Repository.PlaylistMusic.SongWithPlaylists

@Dao
interface PlayListDAO {
  @Transaction
  @Query("SELECT * FROM Playlist")
  fun getPlaylistsWithSongs(): LiveData<List<PlaylistWithSongs>>

  @Transaction
  @Query("SELECT * FROM Music")
  fun getSongsWithPlaylists(): LiveData<List<SongWithPlaylists>>

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  suspend fun insert(playList: List<PlayList>)
}