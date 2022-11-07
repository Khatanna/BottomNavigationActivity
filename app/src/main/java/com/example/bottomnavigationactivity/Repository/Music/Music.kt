package com.example.bottomnavigationactivity.Repository.Music

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Music")
data class Music(
  @PrimaryKey(autoGenerate = false)
  val path: String,
  val title: String,
  val duration: String,
  val albumId: Long,
  val isFavorite: Boolean
) : Serializable