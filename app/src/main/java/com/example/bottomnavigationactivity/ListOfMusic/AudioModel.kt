package com.example.bottomnavigationactivity.ListOfMusic

import java.io.Serializable

data class AudioModel(
  val path: String,
  val title: String,
  val duration: String,
  val albumId: Long
): Serializable