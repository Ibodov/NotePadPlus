package com.smile.notepad.database.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.smile.notepad.models.AppNote

@Dao
interface AppRoomDao {
    @Query("SELECT * from notes_table")
    fun getAllNotes(): LiveData<List<AppNote>> //Возвращает весь список которое в БД в виде Live Data

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: AppNote) //Добавить записку в БД

    @Delete
    suspend fun delete(note: AppNote) //Удалять записку из БД

    @Update
    suspend fun update(note: AppNote) // Обновить запись в БД
}