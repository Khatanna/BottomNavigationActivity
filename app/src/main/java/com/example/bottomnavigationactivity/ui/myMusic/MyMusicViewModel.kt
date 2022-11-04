package com.example.bottomnavigationactivity.ui.myMusic

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.os.Build
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavigationactivity.ListOfMusic.AudioModel
import com.example.bottomnavigationactivity.ListOfMusic.ListAdapter
import java.io.File


class MyMusicViewModel() : ViewModel() {
  private val _listOfMusic = MutableLiveData<RecyclerView.Adapter<ListAdapter.ViewHolder>>().apply {
    value = null
  }
  val listOfMusic: LiveData<RecyclerView.Adapter<ListAdapter.ViewHolder>> = _listOfMusic

  private val songsList = arrayListOf<AudioModel>()

  @RequiresApi(Build.VERSION_CODES.R)
  @SuppressLint("Recycle")
  fun getMusic(context: Context) {
    val projection = arrayOf(
      MediaStore.Audio.Media.TITLE,
      MediaStore.Audio.Media.DATA,
      MediaStore.Audio.Media.DURATION,
      MediaStore.Audio.Albums.ALBUM_ID
    )

    val selection = MediaStore.Audio.Media.IS_MUSIC + " != 0"
    val cursor: Cursor? = context.contentResolver?.query(
      MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
      projection,
      selection,
      null,
      null
    )

    while (cursor!!.moveToNext()) {
      val songData = AudioModel(cursor.getString(1), cursor.getString(0), cursor.getString(2), cursor.getString(3).toLong())
      if (File(songData.path).exists()) songsList.add(songData)
    }

    _listOfMusic.value = ListAdapter(songsList, context)
  }
}