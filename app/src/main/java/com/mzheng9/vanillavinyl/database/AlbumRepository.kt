package com.mzheng9.vanillavinyl.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import java.util.concurrent.Executors

class AlbumRepository private constructor(context: Context) {

    private val database: AlbumDatabase = Room.databaseBuilder(
        context.applicationContext,
        AlbumDatabase::class.java,
        "album_database"
    )
//        .addMigrations(migration_1_2)
        .build()

    private val albumDao = database.albumDao()
    private val executor = Executors.newSingleThreadExecutor()

    fun getAllAlbums(): LiveData<List<Album>> = albumDao.getAllAlbums()
//    fun getOneAlbums(id: Long): LiveData<List<Album>> = albumDao.getAnyAlbum(id)

    fun insert(album: Album) {
        executor.execute {
            albumDao.insert(album)
        }
    }

    fun deleteAlbum(album: Album) {
        executor.execute {
            albumDao.deleteAlbum(album)
        }
    }

    companion object {
        private var INSTANCE: AlbumRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = AlbumRepository(context)
            }
        }

        fun get(): AlbumRepository {
            return INSTANCE
                ?: throw java.lang.IllegalStateException("AlbumRepository must be initialized.")
        }
    }
}
