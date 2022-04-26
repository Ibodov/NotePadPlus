package com.smile.notepad.screens.add_new_note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.smile.notepad.models.AppNote
import com.smile.notepad.utilites.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNewNoteFragmentViewModel(application: Application):AndroidViewModel(application) {
    fun insert(note:AppNote,onSuccess:()->Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.insert(note) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }

            }
        }



}