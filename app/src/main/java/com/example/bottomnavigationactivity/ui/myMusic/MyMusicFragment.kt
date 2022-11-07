package com.example.bottomnavigationactivity.ui.myMusic

import android.database.Cursor
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bottomnavigationactivity.Adapters.ListAdapter
import com.example.bottomnavigationactivity.Repository.Music.Music
import com.example.bottomnavigationactivity.ui.favorites.FavoritesViewModel
import com.example.bottomnavigationactivity.databinding.FragmentMyMusicBinding
import java.io.File

class MyMusicFragment : Fragment() {
  private lateinit var myMusicViewModel: MyMusicViewModel

  private var _binding: FragmentMyMusicBinding? = null
  private val binding get() = _binding!!

  @RequiresApi(Build.VERSION_CODES.R)
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    myMusicViewModel = ViewModelProvider(this)[MyMusicViewModel::class.java]
    val favoritesViewModel =
      ViewModelProvider(this)[FavoritesViewModel::class.java]

    _binding = FragmentMyMusicBinding.inflate(inflater, container, false)
    val root: View = binding.root

    myMusicViewModel.listOfMusic.observe(viewLifecycleOwner) { it ->
      binding.listOfMusic.adapter = ListAdapter(
        if (myMusicViewModel.databaseIsEmpty()) {
          getListOfMusicOfLocalStorage()
        } else {
          it
        },
        requireContext()
      ) { music, value ->
        favoritesViewModel.setFavorites(music, value)
      }
    }

    return root
  }

  private fun getListOfMusicOfLocalStorage(): List<Music> {
    val songsList = mutableListOf<Music>()

    val projection = arrayOf(
      MediaStore.Audio.Media.TITLE,
      MediaStore.Audio.Media.DATA,
      MediaStore.Audio.Media.DURATION,
      MediaStore.Audio.Albums.ALBUM_ID,
    )

    val selection = MediaStore.Audio.Media.IS_MUSIC + " != 0"
    val cursor: Cursor? = context?.contentResolver?.query(
      MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
      projection,
      selection,
      null,
      null
    )

    while (cursor!!.moveToNext()) {
      val songData = Music(
        cursor.getString(1),
        cursor.getString(0),
        cursor.getString(2),
        cursor.getString(3).toLong(),
        false
      )
      if (File(songData.path).exists()) {
        songsList.add(songData)
      }
    }

    myMusicViewModel.addMusic(songsList)
    return songsList
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}