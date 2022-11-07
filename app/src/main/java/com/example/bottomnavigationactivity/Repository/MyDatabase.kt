package com.example.bottomnavigationactivity.Repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bottomnavigationactivity.Repository.Music.Music
import com.example.bottomnavigationactivity.Repository.Music.MusicDAO
import com.example.bottomnavigationactivity.Repository.PlayList.PlayList
import com.example.bottomnavigationactivity.Repository.PlaylistMusic.PlaylistMusicCrossRef

@Database(
  entities = [
    Music::class,
    PlayList::class
  ],
  version = 1
)
abstract class MyDatabase : RoomDatabase() {
  abstract fun MusicDAO(): MusicDAO

  companion object {
    private var INSTANCE: MyDatabase? = null

    fun getInstance(context: Context): MyDatabase {
      val tempInstance = INSTANCE
      if (tempInstance != null) {
        return tempInstance
      }
      synchronized(this) {
        val instance = Room.databaseBuilder(
          context.applicationContext,
          MyDatabase::class.java,
          "Musicapp"
        ).build()
        INSTANCE = instance
        return instance
      }
    }
  }
}