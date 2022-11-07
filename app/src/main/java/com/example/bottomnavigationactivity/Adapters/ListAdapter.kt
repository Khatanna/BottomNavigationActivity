package com.example.bottomnavigationactivity.Adapters

import android.annotation.SuppressLint
import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bottomnavigationactivity.MyMediaPlayer
import com.example.bottomnavigationactivity.PlayMusicActivity
import com.example.bottomnavigationactivity.R
import com.example.bottomnavigationactivity.Repository.Music.Music

class ListAdapter(
  private val songsList: List<Music>,
  private val context: Context,
  private val favoriteListener: (Music, Boolean) -> Unit
) :
  RecyclerView.Adapter<ListAdapter.ViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder(
      LayoutInflater.from(parent.context).inflate(R.layout.my_music_element, parent, false)
    )
  }

  @SuppressLint("RecyclerView")
  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bindData(songsList[position])

    holder.itemTitle.setTextColor(
      if (MyMediaPlayer.currentIndex == position) {
        Color.parseColor("#FF0000")
      } else {
        Color.parseColor("#000000")
      }
    )

    holder.itemView.setOnClickListener {
      MyMediaPlayer.getInstance().reset()
      MyMediaPlayer.currentIndex = position
      val intent = Intent(context, PlayMusicActivity::class.java)
      intent.putExtra("list", songsList.toTypedArray())
      context.startActivity(intent)
    }
  }

  override fun getItemCount(): Int {
    return this.songsList.size
  }

  inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var itemTitle: TextView
    var moreOptions: ImageButton
    val favoritesOption: ImageButton
    val removeFavorites: ImageButton
    val cover: ImageView

    init {
      itemTitle = view.findViewById(R.id.titleOfMusic)
      moreOptions = view.findViewById(R.id.moreOptions)
      favoritesOption = view.findViewById(R.id.addFavorites)
      removeFavorites = view.findViewById(R.id.removeFavorites)
      cover = view.findViewById(R.id.cover)
    }

    fun bindData(item: Music) {
      val uri = ContentUris.withAppendedId(PlayMusicActivity.sArtworkUri, item.albumId)
      Glide.with(context).load(uri).into(cover)

      itemTitle.text = item.title

      if (item.isFavorite) {
        removeFavorites.visibility = View.VISIBLE
        favoritesOption.visibility = View.GONE
      } else {
        removeFavorites.visibility = View.GONE
        favoritesOption.visibility = View.VISIBLE
      }

      favoritesOption.setOnClickListener {
        favoriteListener(item, true)
        Toast.makeText(context, "add to favorites 😍", Toast.LENGTH_SHORT).show()
      }
      removeFavorites.setOnClickListener {
        favoriteListener(item, false)
        Toast.makeText(context, "remove to favorites 😢", Toast.LENGTH_SHORT).show()
      }

      moreOptions.setOnClickListener {
        val popupMenu = PopupMenu(context, moreOptions)
        popupMenu.inflate(R.menu.option_music)
        popupMenu.setOnMenuItemClickListener {
          when (it.itemId) {
            /*
            R.id.addFavorites -> {
              item.apply {
                favoritesViewModel.addFavorites(Favorites(path, title, duration, albumId))
              }
              Toast.makeText(context, "add to favorites", Toast.LENGTH_SHORT).show()
            }
            */
          }
          false
        }

        popupMenu.show()
      }
    }
  }
}