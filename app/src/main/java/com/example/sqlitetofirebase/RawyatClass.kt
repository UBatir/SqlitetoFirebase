package com.example.sqlitetofirebase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="rawyat")
data class RawyatClass(
    @PrimaryKey
    @ColumnInfo(name= "id") val id:Int?,
    @ColumnInfo(name= "title") val title:String?,
    @ColumnInfo(name= "img") val img:String?,
    @ColumnInfo(name= "full_text") val full_text:String?,
    @ColumnInfo(name= "hits") val hits:String?
)
