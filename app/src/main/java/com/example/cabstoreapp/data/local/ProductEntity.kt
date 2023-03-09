package com.example.cabstoreapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    val code: String,
    val name: String,
    val price: Double,
    val quantity: Int
)