package com.example.bottomnavigationactivity.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bottomnavigationactivity.databinding.FragmentFavoritesBinding
import com.example.bottomnavigationactivity.Adapters.ListAdapter

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

    favoritesViewModel.readAllData.observe(viewLifecycleOwner) { it ->
      binding.listOfFavorites.adapter = ListAdapter(
        it.filter { music ->
          music.isFavorite
        },
        requireContext()
      ) { music, value ->
        favoritesViewModel.setFavorites(music, value)
      }
    }

    return root
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}