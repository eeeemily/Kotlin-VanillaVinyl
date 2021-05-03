
package com.mzheng9.vanillavinyl.ui.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.mzheng9.vanillavinyl.database.Album
import com.mzheng9.vanillavinyl.database.AlbumRepository

/*i don' need it?*
 */
class RecordsDisplayViewModel(application: Application) : AndroidViewModel(application) {

    init {
        AlbumRepository.initialize(application)
    }
    private val albumRepository = AlbumRepository.get()
    val albums = albumRepository.getAllAlbums()

    fun addAlbum(newAlbum: Album) {
        albumRepository.insert(newAlbum)
    }

    fun deleteAlbum(album: Album) {
        albumRepository.deleteAlbum(album)
    }
}
