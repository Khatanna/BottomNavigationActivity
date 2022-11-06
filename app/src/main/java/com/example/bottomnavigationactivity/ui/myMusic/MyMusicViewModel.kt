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
import com.example.bottomnavigationactivity.ui.favorites.FavoritesViewModel
import com.example.bottomnavigationactivity.ListOfMusic.AudioModel
import com.example.bottomnavigationactivity.ListOfMusic.ListAdapter
import java.io.File

class MyMusicViewModel() : ViewModel() {
  private val _listOfMusic = MutableLiveData<RecyclerView.Adapter<ListAdapter.ViewHolder>>().apply {
    value = null
  }
  val listOfMusic: LiveData<RecyclerView.Adapter<ListAdapter.ViewHolder>> = _listOfMusic

  fun setListOfMusic(
    listOfMusic: ArrayList<AudioModel>,
    context: Context,
    favoritesViewModel: FavoritesViewModel
  ) {
    _listOfMusic.value = ListAdapter(listOfMusic, context, favoritesViewModel)
  }

  fun isEmptyList(): Boolean {
    return _listOfMusic.value == null
  }
}