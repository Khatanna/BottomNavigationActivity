package com.example.bottomnavigationactivity.ui.myMusic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyMusicViewModel : ViewModel() {

  private val _text = MutableLiveData<String>().apply {
    value = "This is my music Fragment"
  }
  val text: LiveData<String> = _text
}