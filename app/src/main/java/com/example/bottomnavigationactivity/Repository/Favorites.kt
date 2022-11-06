package com.example.bottomnavigationactivity.Repository

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Favorites")
data class Favorites (
  @PrimaryKey(autoGenerate = false)
  val path: String,
  val title: String,
  val duration: String,
  val albumId: Long
)