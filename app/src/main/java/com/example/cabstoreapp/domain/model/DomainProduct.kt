package com.example.cabstoreapp.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DomainProduct(
    val code: String,
    val name: String,
    val price: Double
): Parcelable
