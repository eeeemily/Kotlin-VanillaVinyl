package com.mzheng9.vanillavinyl.ui.details

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mzheng9.vanillavinyl.MainActivity
import com.mzheng9.vanillavinyl.R
import com.mzheng9.vanillavinyl.database.Album
import com.mzheng9.vanillavinyl.databinding.RecyclerviewItemBinding
import com.mzheng9.vanillavinyl.ui.details.toast
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.*
import jp.wasabeef.picasso.transformations.gpu.InvertFilterTransformation
import jp.wasabeef.picasso.transformations.gpu.PixelationFilterTransformation

var highlightedIndex: Int = -1
private const val TAG = "WebviewFragment"
var effectGlobalIndex: Int = -1
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
    fun updateAlbumImageView(effectIntdex: Int) {
            effectGlobalIndex=effectIntdex
            notifyDataSetChanged()
    }

    fun getAlbumAtPosition(position: Int): Album {
        return albums[position]
    }

    class AlbumViewHolder(private val binding: RecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
                private lateinit var album: Album
        private val albumTextView: TextView = itemView.findViewById(R.id.item_textView)
        private val albumNameTextView: TextView = itemView.findViewById(R.id.album_name_textview)
        private val artistTextView: TextView = itemView.findViewById(R.id.artist_textview)
        private val releasedTextView: TextView = itemView.findViewById(R.id.released_textview)
        private val commentTextView: TextView = itemView.findViewById(R.id.comment_textview)
        private val albumCoverImageView: ImageView = itemView.findViewById(R.id.album_imageview)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(album: Album) {
            this.album = album
            albumNameTextView.text = albumTextView.context.getString(R.string.details_album_name, album.albumName)
            artistTextView.text = artistTextView.context.getString(R.string.details_artist,album.albumArtist)
            releasedTextView.text = releasedTextView.context.getString(R.string.details_released,album.albumRelease)
            commentTextView.text = commentTextView.context.getString(R.string.details_comment,album.albumComment)
            when(album.albumCoverLink){
                ""->loadUrl("https://coverartarchive.org/release-group/1237b040-fb8f-4f23-8000-fb6909486c83/front.jpg")
                else->loadUrl(album.albumCoverLink)
//                else->loadUrl(album.albumCoverLink)
            }
//            albumTextView.text =
//                    "Artist: " + album.albumArtist + " released: " + album.albumRelease
        }

        override fun onClick(v: View?) {
            /*if one album is already highlighted*/
            if (highlightedIndex != -1) {
                /*if one album is already highlighted*/
                if (adapterPosition == highlightedIndex) {
                    highlightedIndex = -1
                    albumTextView.setBackgroundResource(R.drawable.album_cell_background)
                } else {
                    albumTextView.context?.toast(albumTextView.context.getString(R.string.unselect_first))
                }
            }
            /*if no album is selected*/
            else {
                highlightedIndex = adapterPosition
                albumTextView.setBackgroundResource(R.drawable.album_cell_selected_background)
          }
        }
        private fun loadUrl(request: String) {


            val effect = when (effectGlobalIndex) {
                0 -> CropSquareTransformation()
                1 -> CropCircleTransformation()
                2 -> BlurTransformation(binding?.albumImageview.context, 15, 1)
                3 -> PixelationFilterTransformation(binding?.albumImageview.context, 48.0f)
                4 -> InvertFilterTransformation(binding?.albumImageview.context)
//                5 -> GrayscaleTransformation()
                else -> RoundedCornersTransformation(85, 32)
            }
            val picasso = Picasso.get()
            if(effectGlobalIndex!=-1){
                picasso.load(request)
                    .placeholder(R.drawable.placeholder_ari)
                    .transform(effect)
                    .into(binding?.albumImageview)
            }else{
                picasso.load(request)
                    .placeholder(R.drawable.placeholder_ari)
                    .transform(GrayscaleTransformation())
                    .into(binding?.albumImageview)
            }

        }


    }
}
