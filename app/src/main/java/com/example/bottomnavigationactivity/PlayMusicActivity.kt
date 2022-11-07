package com.example.bottomnavigationactivity

import android.annotation.SuppressLint
import android.content.ContentUris
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.bottomnavigationactivity.Repository.Music.Music
import com.example.bottomnavigationactivity.databinding.ActivityPlayMusicBinding
import java.io.IOException
import java.util.concurrent.TimeUnit


class PlayMusicActivity : AppCompatActivity() {
  private val mediaPlayer = MyMediaPlayer.getInstance()
  private lateinit var songsList: Array<Music>
  private lateinit var currentSong: Music

  companion object {
    val sArtworkUri: Uri = Uri
      .parse("content://media/external/audio/albumart")
  }

  private lateinit var binding: ActivityPlayMusicBinding
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityPlayMusicBinding.inflate(layoutInflater)
    setContentView(binding.root)
    songsList = intent.getSerializableExtra("list") as Array<Music>

    setResourcesWithMusic()

    this.runOnUiThread(object : Runnable {
      override fun run() {
        if (mediaPlayer != null) {
          binding.duration.progress = mediaPlayer.currentPosition
          binding.currentTime.text = convertToMMSS("${mediaPlayer.currentPosition}")
          if (mediaPlayer.isPlaying) {
            binding.run {
              btnPlay.visibility = View.GONE
              btnPause.visibility = View.VISIBLE
            }
          } else {
            binding.run {
              btnPlay.visibility = View.VISIBLE
              btnPause.visibility = View.GONE
            }
          }
        }
        Handler(Looper.getMainLooper()).postDelayed(this, 100)
      }
    })
    binding.duration.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
      override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        if (mediaPlayer != null && fromUser) {
          mediaPlayer.seekTo(progress)
        }
      }

      override fun onStartTrackingTouch(seekBar: SeekBar?) {}

      override fun onStopTrackingTouch(seekBar: SeekBar?) {}
    })
  }

  private fun setResourcesWithMusic() {
    currentSong = songsList[MyMediaPlayer.currentIndex]

    val uri = ContentUris.withAppendedId(sArtworkUri, currentSong.albumId)
    Glide.with(this).load(uri).into(binding.cover)

    binding.title.text = currentSong.title
    binding.totalTime.text = convertToMMSS(currentSong.duration)

    binding.btnPlay.setOnClickListener { btnPlay() }
    binding.btnPause.setOnClickListener { btnPause() }
    binding.btnNext.setOnClickListener { playNextSong() }
    binding.btnPrevious.setOnClickListener { playPreviousSong() }
    binding.btnFirstList.setOnClickListener { playInitSongList() }
    binding.btnEndList.setOnClickListener { playEndSongList() }

    playMusic()
  }

  private fun playMusic() {
    mediaPlayer.reset()
    try {
      mediaPlayer.setDataSource(currentSong.path)
      mediaPlayer.prepare()
      mediaPlayer.start()
      binding.duration.progress = 0
      binding.duration.max = mediaPlayer.duration
    } catch (e: IOException) {
      e.printStackTrace()
    }
  }

  private fun playNextSong() {
    if (MyMediaPlayer.currentIndex == songsList.size - 1) return
    MyMediaPlayer.currentIndex++
    mediaPlayer.reset()
    setResourcesWithMusic()
  }

  private fun playPreviousSong() {
    if (MyMediaPlayer.currentIndex == 0) return
    MyMediaPlayer.currentIndex--
    mediaPlayer.reset()
    setResourcesWithMusic()
  }

  private fun playInitSongList() {
    MyMediaPlayer.currentIndex = 0;
    mediaPlayer.reset()
    setResourcesWithMusic()
  }

  private fun playEndSongList() {
    MyMediaPlayer.currentIndex = songsList.size - 1;
    mediaPlayer.reset()
    setResourcesWithMusic()
  }

  private fun btnPause() {
    if (mediaPlayer.isPlaying) {
      mediaPlayer.pause()
    }
  }

  private fun btnPlay() {
    if (!mediaPlayer.isPlaying) {
      mediaPlayer.start()
    }
  }

  @SuppressLint("DefaultLocale")
  fun convertToMMSS(duration: String): String? {
    val millis = duration.toLong()
    return java.lang.String.format(
      "%02d:%02d",
      TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
      TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1)
    )
  }
}