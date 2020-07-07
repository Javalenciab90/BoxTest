package com.java90.movilboxtest.domain.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "posts")
data class Post(
    @PrimaryKey
    val id: Int,
    val userId: Int,
    val body: String,
    val title: String
) : Parcelable