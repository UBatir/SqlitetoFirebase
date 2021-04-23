package com.example.sqlitetofirebase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="posts")
data class PostsClass(
    @PrimaryKey
    @ColumnInfo(name= "id") val id:Int?,
    @ColumnInfo(name= "title") val title:String?,
    @ColumnInfo(name= "full_text") val full_text:String?,
    @ColumnInfo(name= "date") val date:String?,
    @ColumnInfo(name= "hits") val hits:String?,
    @ColumnInfo(name= "hide") val hide:String?
)
