package com.example.sqlitetofirebase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="sorawjuwap")
data class SorawJuwapClass(
    @PrimaryKey
    @ColumnInfo(name= "id") val id:Int,
    @ColumnInfo(name= "soraw") val soraw:String,
    @ColumnInfo(name= "juwap") val juwap:String,
)
