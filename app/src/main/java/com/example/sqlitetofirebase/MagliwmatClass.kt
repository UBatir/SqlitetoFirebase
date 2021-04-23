package com.example.sqlitetofirebase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="magl")
data class MagliwmatClass(
    @PrimaryKey
    @ColumnInfo(name= "id") val id:Int?,
    @ColumnInfo(name= "title") val title:String?,
    @ColumnInfo(name= "fulltext") val fulltext:String?,
    @ColumnInfo(name= "date") val date:String?,
    @ColumnInfo(name= "catid") val catid:String?,
    @ColumnInfo(name= "hits") val hits:String?
)
