package com.mzheng9.vanillavinyl.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AlbumDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(album: Album)

    @Query("DELETE FROM album_table")
    fun deleteAll()

    @Delete
    fun deleteAlbum(album: Album)

//    @Query("SELECT * FROM album_table WHERE album_order=(:id)")
//    fun getItem(id: Long): LiveData<Album?>

    @Query("SELECT * FROM album_table LIMIT 1")
    fun getAnyAlbum(): Array<Album>

    @Query("SELECT * FROM album_table ORDER BY album_ID DESC")
    fun getAllAlbums(): LiveData<List<Album>>
}