package com.example.cabstoreapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Insert(onConflict = REPLACE)
    suspend fun addProducts(countries: List<ProductEntity>)

    @Query("SELECT * FROM productentity")
    fun getAllProducts(): Flow<List<ProductEntity>?>
}