package com.mzheng9.vanillavinyl.ui.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mzheng9.vanillavinyl.R
import com.mzheng9.vanillavinyl.database.Album
import com.mzheng9.vanillavinyl.databinding.RecyclerviewItemBinding
import com.mzheng9.vanillavinyl.ui.details.toast

var highlightedIndex: Int = -1

class AlbumAdapter : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {
var albums: List<Album> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AlbumViewHolder(
        RecyclerviewItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) =
        holder.bind(albums[position])


    override fun getItemCount() = albums.size

    fun updateAlbums(newAlbums: List<Album>) {
        this.albums = newAlbums
        notifyDataSetChanged()
    }

    fun getAlbumAtPosition(position: Int): Album {
        return albums[position]
    }

    class AlbumViewHolder(private val binding: RecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
                private lateinit var album: Album
        private val albumTextView: TextView = itemView.findViewById(R.id.item_textView)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(album: Album) {
            this.album = album
            albumTextView.text =
                    "Artist: " + album.albumArtist + " released: " + album.albumRelease
        }

        override fun onClick(v: View?) {
            /*if one album is already highlighted*/
            if (highlightedIndex != -1) {
                /*if one album is already highlighted*/
                if (adapterPosition == highlightedIndex) {
                    highlightedIndex = -1
                    albumTextView.setBackgroundResource(R.drawable.album_cell_background)
                } else {
                    albumTextView.context?.toast("unselect/delete the selected Album first!")
                }
            }
            /*if no album is selected*/
            else {
                highlightedIndex = adapterPosition
                albumTextView.setBackgroundResource(R.drawable.album_cell_selected_background)
          }
        }

    }
}
