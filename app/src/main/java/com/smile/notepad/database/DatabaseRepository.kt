package com.smile.notepad.database

import androidx.lifecycle.LiveData
import com.smile.notepad.database.room.AppRoomDao
import com.smile.notepad.models.AppNote

interface DatabaseRepository {
    //что должен делать репозитории
    val allNotes: LiveData<List<AppNote>>       //1. получить список всех конт
    suspend fun insert(note: AppNote, onSuccess:() -> Unit)   // 2. вставлять запись в БД
    suspend fun delete(note: AppNote, onSuccess: () -> Unit) //3. Удалить запись с БД
    suspend fun update(note: AppNote, onSuccess: () -> Unit) //4. обновить запись в



}