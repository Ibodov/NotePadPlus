package com.smile.notepad.screens.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.smile.notepad.database.room.AppRoomDatabase
import com.smile.notepad.database.room.AppRoomRepository
import com.smile.notepad.utilites.REPOSITORY
import com.smile.notepad.utilites.TYPE_ROOM

class StartFragmentViewModel(application: Application): AndroidViewModel(application) {
    private val mContext = application

    fun initDatabase(type: String, onSuccess:()->Unit){
        when(type) {
            TYPE_ROOM -> {
                val dao = AppRoomDatabase.getInstance(mContext).getAppRoomDao()
                REPOSITORY = AppRoomRepository(dao)
                onSuccess()
            }
        }
    }
}