package com.java90.movilboxtest.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post_table")
data class Post(
    @ColumnInfo(name = "userId")
    val userId: Int = 0,
    @PrimaryKey @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "title")
    val title: String = "",
    @ColumnInfo(name = "body")
    val body: String = ""
)