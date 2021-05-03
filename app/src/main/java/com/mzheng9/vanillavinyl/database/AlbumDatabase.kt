package com.mzheng9.vanillavinyl.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Album::class], version = 1, exportSchema = true)
@TypeConverters(Converters::class)
abstract class AlbumDatabase : RoomDatabase() {
    abstract fun albumDao(): AlbumDao
}

//val migration_1_2 = object : Migration(1, 2) {
//    override fun migrate(database: SupportSQLiteDatabase) {
//        database.execSQL(
//            "ALTER TABLE album_table ADD COLUMN album_age INTEGER"
//        )
//    }
//}