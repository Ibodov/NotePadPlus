package com.smile.notepad.utilites

import android.widget.Toast

//Здесь хранятся утилитарные функции
fun showToast(message:String) {
    Toast.makeText(APP_ACTIVITY,message,Toast.LENGTH_SHORT).show()
}