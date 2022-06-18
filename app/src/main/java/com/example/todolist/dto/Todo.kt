package com.example.todolist.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

//DTO(Data Transfer Object) 는 계층 간 데이터 교환을 하기 위해 사용하는 객체로, DTO는 로직을 가지지 않는 순수한 데이터 객체(setter & getter 만 가진 클래스)임
//데이터를 DTO에 넣어서 전송 -> DTO를 받은 서버가 DAO를 이용하여 데이터베이스로 데이터를 넣음
@Entity(tableName = "todoTable") //테이블 이름
class Todo(@ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id: Long = 0,
            @ColumnInfo(name = "title") val title: String,//제목
            @ColumnInfo(name = "content") val content: String,//내용
            @ColumnInfo(name = "timestamp") val timestamp: String,// 생성/수정 날짜
            @ColumnInfo(name = "isChecked") var isChecked: Boolean): Serializable {//체크박스 클릭(완료 여부)여부
}