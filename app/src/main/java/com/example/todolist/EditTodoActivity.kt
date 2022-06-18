package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todolist.databinding.ActivityEditTodoBinding
import com.example.todolist.dto.Todo
import java.text.SimpleDateFormat

class EditTodoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditTodoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditTodoBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_edit_todo)
        setContentView(binding.root)

        val type = intent.getStringExtra("type")
        //intent를 통해 type에 담긴 "type"이 "ADD"면 추가하기 버튼이 "추가하기"로, 아니면 "수정하기"로
        if(type.equals("ADD")){
            binding.editTodoActivityBtnSave.text = "추가하기"
        } else {
            binding.editTodoActivityBtnSave.text = "수정하기"
        }

        binding.editTodoActivityBtnSave.setOnClickListener { 
            val title = binding.editTodoActivityTodoTitle.text.toString()
            val content = binding.editTodoActivityTodoContent.text.toString()
            val currentDate = SimpleDateFormat("yyyy-MM-dd HH:mm").format(System.currentTimeMillis())
            
            if(type.equals("ADD")){
                if(title.isNotEmpty() && content.isNotEmpty()){
                    val todo = Todo(0, title, content, currentDate, false)
                    val intent = Intent().apply { 
                        putExtra("todo", todo)
                        putExtra("flag", 0) // 0 -> "추가", 1 -> "수정"
                    }
                    setResult(RESULT_OK, intent) // MainActivity로 투두 객체와 추가 or 수정 구분할 flag 넘김
                    finish()
                }
            } else {
                //수정
            }
        }
    }
}