package com.example.bottomnavigationactivity.Repository.PlayList

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Playlist")
data class PlayList (
  @PrimaryKey(autoGenerate = true)
  val id: Long,
  val name: String
)