package com.smile.notepad.screens.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.smile.notepad.database.room.AppRoomDatabase
import com.smile.notepad.database.room.AppRoomRepository
import com.smile.notepad.utilites.REPOSITORY
import com.smile.notepad.utilites.TYPE_ROOM

class MainFragmentViewModel(application: Application): AndroidViewModel(application) {


    val allNotes = REPOSITORY.allNotes


}