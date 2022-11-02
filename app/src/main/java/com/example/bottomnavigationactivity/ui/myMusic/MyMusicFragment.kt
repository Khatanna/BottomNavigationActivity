package com.example.bottomnavigationactivity.ui.myMusic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bottomnavigationactivity.databinding.FragmentMyMusicBinding

class MyMusicFragment : Fragment() {

  private var _binding: FragmentMyMusicBinding? = null

  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val viewModel =
      ViewModelProvider(this)[MyMusicViewModel::class.java]

    _binding = FragmentMyMusicBinding.inflate(inflater, container, false)
    val root: View = binding.root

    viewModel.listOfMusic.observe(viewLifecycleOwner) {
      binding.listOfMusic.adapter = it
    }

    viewModel.getMusic(requireContext())

    return root
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}