package com.example.bottomnavigationactivity.ui.myMusic

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavigationactivity.ListOfMusic.ListAdapter
import com.example.bottomnavigationactivity.ListOfMusic.Music

class MyMusicViewModel() : ViewModel() {
  private val _listOfMusic = MutableLiveData<RecyclerView.Adapter<ListAdapter.ViewHolder>>().apply {
    value = null
  }
  val listOfMusic: LiveData<RecyclerView.Adapter<ListAdapter.ViewHolder>> = _listOfMusic

  @SuppressLint("Recycle")
  fun getMusic (context: Context) {
    val projection = arrayOf(
      MediaStore.Audio.Media.TITLE,
      MediaStore.Audio.Media.DATA,
      MediaStore.Audio.Media.DURATION
    )

    val selection = MediaStore.Audio.Media.IS_MUSIC + " != 0"
    val cursor: Cursor? = context.contentResolver?.query(
      MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
      projection,
      selection,
      null,
      null
    )

    val playListAdapter = arrayListOf<Music>()
    val playList = mutableListOf<String>()
    while (cursor!!.moveToNext()) {
      val title = cursor.getString(1).split("/").last()
      if (title.contains(".mp3")) {
        playListAdapter.add(Music(title))
        playList.add(cursor.getString(1))
      }
    }

    _listOfMusic.value = ListAdapter(playListAdapter, context)
  }

}