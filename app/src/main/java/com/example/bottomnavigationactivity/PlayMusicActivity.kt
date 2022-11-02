package com.example.bottomnavigationactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bottomnavigationactivity.ListOfMusic.Music
import com.example.bottomnavigationactivity.databinding.ActivityPlayMusicBinding

class PlayMusicActivity : AppCompatActivity() {
  private lateinit var binding: ActivityPlayMusicBinding
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityPlayMusicBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val element = intent.getSerializableExtra("ListElement") as Music

    binding.title.text = element.title
  }
}