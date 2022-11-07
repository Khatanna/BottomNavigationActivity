package com.example.bottomnavigationactivity.Repository.PlaylistMusic

import androidx.room.Entity

@Entity(primaryKeys = ["playlistId", "musicId"])
data class PlaylistMusicCrossRef(
  val playlistId: Long,
  val musicId: String
)