package com.example.sqlitetofirebase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PatuaClass::class],version = 2)
abstract class PaziyletDatabase: RoomDatabase() {

    companion object {
        private lateinit var INSTANCE: PaziyletDatabase

        fun getInstance(context: Context): PaziyletDatabase =
            if (::INSTANCE.isInitialized) {
                INSTANCE
            } else {
                INSTANCE= Room.databaseBuilder(
                    context,
                    PaziyletDatabase::class.java, "paziylet.db"
                )
                    .allowMainThreadQueries()
                    .createFromAsset("paziylet.db")
                    .build()
                INSTANCE
            }
    }
    abstract fun dao():Dao
}