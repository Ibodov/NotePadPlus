package com.smile.notepad.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.smile.notepad.models.AppNote

@Database(entities = [AppNote::class], version = 1)
abstract class AppRoomDatabase:RoomDatabase(){
    abstract fun getAppRoomDao():AppRoomDao

    //Создается singleton,
    companion object{

        @Volatile //БД указывается, что база не должна кешироваться
        private var database:AppRoomDatabase?= null //изначально присваевается null

        @Synchronized //Заприщает к этой функции обращение несколько экземпляро одновременно
        fun getInstance(context: Context):AppRoomDatabase { //фун. будет предоставлять экземпляр БД
            return if (database==null) {
                database = Room.databaseBuilder(
                    context,
                    AppRoomDatabase::class.java,
                    "database"
                ).build()
                database as AppRoomDatabase
            } else database as AppRoomDatabase
        }
    }
}