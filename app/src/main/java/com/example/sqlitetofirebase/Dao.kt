package com.example.sqlitetofirebase

import androidx.room.Dao
import androidx.room.Query

@Dao
interface Dao {
    @Query("SELECT * FROM rawyat")
    fun getAllData():List<RawyatClass>
}