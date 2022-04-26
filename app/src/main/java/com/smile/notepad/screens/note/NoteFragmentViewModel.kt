package com.smile.notepad.screens.note

import android.app.Application
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.smile.notepad.R
import com.smile.notepad.models.AppNote
import com.smile.notepad.utilites.APP_ACTIVITY
import com.smile.notepad.utilites.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteFragmentViewModel(application: Application) : AndroidViewModel(application) {
    fun delete(note: AppNote, onSuccess: () -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            viewModelScope.launch(Dispatchers.Main) {
                REPOSITORY.delete(note) {
                    onSuccess()
                }
            }
        }

    fun update(note: AppNote, onSuccess: () -> Unit) =
        viewModelScope.launch {
            REPOSITORY.update(note) {
                onSuccess()
            }
        }
}