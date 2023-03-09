package com.example.cabstoreapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ProductEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun countryDao(): ProductDao
    companion object {
        const val DATABASE_NAME = "COUNTRIES_DATA_BASE"
    }
}