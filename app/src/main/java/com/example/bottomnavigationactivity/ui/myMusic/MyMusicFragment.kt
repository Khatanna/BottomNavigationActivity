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
import com.example.bottomnavigationactivity.ListOfMusic.AudioModel
import com.example.bottomnavigationactivity.ui.favorites.FavoritesViewModel
import com.example.bottomnavigationactivity.databinding.FragmentMyMusicBinding
import java.io.File

class MyMusicFragment : Fragment() {
  private lateinit var viewModel: MyMusicViewModel

  private var _binding: FragmentMyMusicBinding? = null
  private val binding get() = _binding!!

  @RequiresApi(Build.VERSION_CODES.R)
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    viewModel = ViewModelProvider(this)[MyMusicViewModel::class.java]
    val favoritesViewModel =
      ViewModelProvider(this)[FavoritesViewModel::class.java]

    _binding = FragmentMyMusicBinding.inflate(inflater, container, false)
    val root: View = binding.root

    viewModel.listOfMusic.observe(viewLifecycleOwner) {
      binding.listOfMusic.adapter = it
    }

    if(viewModel.isEmptyList()) {
      viewModel.setListOfMusic(getListOfMusic(), requireContext(), favoritesViewModel)
    }

    return root
  }

  private fun getListOfMusic(): ArrayList<AudioModel> {
    val songsList = ArrayList<AudioModel>()

    val projection = arrayOf(
      MediaStore.Audio.Media.TITLE,
      MediaStore.Audio.Media.DATA,
      MediaStore.Audio.Media.DURATION,
      MediaStore.Audio.Albums.ALBUM_ID
    )

    val selection = MediaStore.Audio.Media.IS_MUSIC + " != 0"
    val cursor: Cursor? = requireContext().contentResolver?.query(
      MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
      projection,
      selection,
      null,
      null
    )

    while (cursor!!.moveToNext()) {
      val songData = AudioModel(
        cursor.getString(1),
        cursor.getString(0),
        cursor.getString(2),
        cursor.getString(3).toLong()
      )
      if (File(songData.path).exists()) {
        songsList.add(songData)
      }
    }

    return songsList
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}