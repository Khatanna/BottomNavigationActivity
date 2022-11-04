package com.example.bottomnavigationactivity.ListOfMusic

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.MyMediaPlayer
import com.example.bottomnavigationactivity.PlayMusicActivity
import com.example.bottomnavigationactivity.R

class ListAdapter(
  private val songsList: ArrayList<AudioModel>,
  private val context: Context
) :
  RecyclerView.Adapter<ListAdapter.ViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder(
      LayoutInflater.from(parent.context).inflate(R.layout.my_music_element, parent, false)
    )
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bindData(songsList[position])
    holder.itemView.setOnClickListener {
      MyMediaPlayer.getInstance().reset()
      MyMediaPlayer.currentIndex = position
      val intent = Intent(context, PlayMusicActivity::class.java)
      intent.putExtra("list", songsList)
      context.startActivity(intent)
    }
  }

  override fun getItemCount(): Int {
    return this.songsList.size
  }

  inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var itemTitle: TextView
    var moreOptions: ImageButton

    init {
      itemTitle = view.findViewById(R.id.titleOfMusic)
      moreOptions = view.findViewById(R.id.moreOptions)
    }

    fun bindData(item: AudioModel) {
      itemTitle.text = item.title
      moreOptions.setOnClickListener {
        val popupMenu = PopupMenu(context, moreOptions)
        popupMenu.inflate(R.menu.option_music)
        popupMenu.setOnMenuItemClickListener {
          when (it.itemId) {
            R.id.addFavorites -> {
              Toast.makeText(context, "add to favorites", Toast.LENGTH_SHORT).show()
            }
          }
          false
        }

        popupMenu.show()
      }
    }
  }
}