package com.example.sqlitetofirebase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="patua")
data class PatuaClass(
    @PrimaryKey
    @ColumnInfo(name= "id") val id:Int?,
    @ColumnInfo(name= "title") val title:String?,
    @ColumnInfo(name= "full_text") val full_text:String?,
    @ColumnInfo(name= "hits") val hits:String?
)
