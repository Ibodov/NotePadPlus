package com.smile.notepad.screens.update

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.smile.notepad.models.AppNote
import com.smile.notepad.utilites.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdateNoteFragmentViewModel(application: Application) : AndroidViewModel(application) {
    fun delete(note: AppNote, onSuccess: () -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            viewModelScope.launch(Dispatchers.Main) {
                REPOSITORY.update(note) {
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