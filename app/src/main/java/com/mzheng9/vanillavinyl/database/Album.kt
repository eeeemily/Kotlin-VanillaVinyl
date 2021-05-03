package com.mzheng9.vanillavinyl.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "album_table")
data class Album(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "album_ID")
    var albumID: Long = 0L,

    @ColumnInfo(name = "album_name")
    var albumName: String = "",

    @ColumnInfo(name = "album_artist")
    var albumArtist: String = "",

    @ColumnInfo(name = "album_release")
    var albumRelease: String = "",

    @ColumnInfo(name = "album_cover_link")
    var albumCoverLink: String = "",

    @ColumnInfo(name = "album_comment")
    var albumComment: String = ""
)