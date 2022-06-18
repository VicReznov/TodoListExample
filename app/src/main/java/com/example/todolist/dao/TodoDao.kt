package com.example.todolist.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todolist.dto.Todo

//DAO(Data Access Object)는 데이터베이스의 data에 접근하기 위한 객체임
//Database에 접근하기 위한 로직&비지니스 로직을 분리하기 위해 사용함
@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dto: Todo)

    //모두 불러오는 list함수의 경우 LiveData를 사용해 추가, 수정, 삭제에 의해 변화하는 값에 대해 즉시 반영이 가능하도록 함
    @Query("select * from todoTable")
    fun list(): LiveData<MutableList<Todo>>

    @Query("select * from todoTable where id = (:id)")
    fun selectOne(id: Long): Todo

    @Update
    suspend fun update(dto: Todo)

    @Delete
    fun delete(dto: Todo)
}