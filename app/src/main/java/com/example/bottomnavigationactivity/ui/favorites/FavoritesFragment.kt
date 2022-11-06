package com.example.bottomnavigationactivity.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bottomnavigationactivity.ListOfMusic.AudioModel
import com.example.bottomnavigationactivity.databinding.FragmentFavoritesBinding
import com.example.bottomnavigationactivity.ListOfMusic.ListAdapter

class FavoritesFragment : Fragment() {

  private var _binding: FragmentFavoritesBinding? = null

  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val favoritesViewModel =
      ViewModelProvider(this)[FavoritesViewModel::class.java]

    _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
    val root: View = binding.root

    favoritesViewModel.readAllData.observe(viewLifecycleOwner) {
      val arrayList = ArrayList<AudioModel>()

      it.forEach { favorite ->
        favorite.apply {
          arrayList.add(AudioModel(path, title, duration, albumId))
        }
      }
      binding.listOfFavorites.adapter = ListAdapter(arrayList, requireContext(), favoritesViewModel)
    }
    return root
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}