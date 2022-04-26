package com.smile.notepad.utilites

import com.smile.notepad.MainActivity
import com.smile.notepad.database.DatabaseRepository

lateinit var APP_ACTIVITY:MainActivity
const val TYPE_DATABASE = "type_database"
const val TYPE_ROOM = "type_room"
lateinit var REPOSITORY:DatabaseRepository