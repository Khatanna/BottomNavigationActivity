package com.example.bottomnavigationactivity.ListOfMusic

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavigationactivity.R

class ListAdapter(
  private val items: ArrayList<Music>,
  private val context: Context
) :
  RecyclerView.Adapter<ListAdapter.ViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder(
      LayoutInflater.from(parent.context).inflate(R.layout.my_music_element, parent, false)
    )
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.itemTitle.text = items[position].title
    holder.moreOptions.setOnClickListener {
      val popupMenu = PopupMenu(context, holder.moreOptions)
      popupMenu.inflate(R.menu.option_music)
      popupMenu.setOnMenuItemClickListener {
        when(it.itemId) {
          R.id.addFavorites -> {
            Toast.makeText(context, "add to favorites", Toast.LENGTH_SHORT).show()
          }
        }
        false
      }

      popupMenu.show()
    }
  }

  override fun getItemCount(): Int {
    return this.items.size
  }

  inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var itemTitle: TextView
    var moreOptions: ImageButton

    init {
      itemTitle = view.findViewById(R.id.titleOfMusic)
      moreOptions = view.findViewById(R.id.moreOptions)
    }
  }
}