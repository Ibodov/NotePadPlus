package com.smile.notepad.database.room

import androidx.lifecycle.LiveData
import com.smile.notepad.database.DatabaseRepository
import com.smile.notepad.models.AppNote


//Реализация
class AppRoomRepository(private val appRoomDao: AppRoomDao):DatabaseRepository {
    override val allNotes: LiveData<List<AppNote>>
        get() = appRoomDao.getAllNotes()

    override suspend fun insert(note: AppNote, onSuccess: () -> Unit) {
        appRoomDao.insert(note)
        onSuccess()
    }

    override suspend fun delete(note: AppNote, onSuccess: () -> Unit) {
        appRoomDao.delete(note)
        onSuccess()
    }

    override suspend fun update(note: AppNote, onSuccess: () -> Unit) {
        appRoomDao.update(note)
        onSuccess()
    }
}