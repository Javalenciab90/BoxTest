package com.java90.movilboxtest.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "posts")
data class Post(
    val body: String,
    @PrimaryKey
    val id: Int,
    val title: String,
    val userId: Int
)