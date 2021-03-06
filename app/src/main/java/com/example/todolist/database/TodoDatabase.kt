package com.example.todolist.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todolist.dao.TodoDao
import com.example.todolist.dto.Todo

//entity는 투두 클래스로, RoomDatabase 라이브러리를 사용해 생성함
@Database(entities = arrayOf(Todo::class), version = 1)
abstract class TodoDatabase: RoomDatabase() {
    abstract fun todoDao(): TodoDao
}