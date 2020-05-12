package com.java90.movilboxtest.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "posts")
data class Post(
    val body: String,
    @PrimaryKey
    val id: Int,
    val title: String,
    val userId: Int
) : Parcelable