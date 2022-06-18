package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.dto.Todo
import com.example.todolist.viewmodel.TodoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var todoViewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_main)
        setContentView(binding.root)

        todoViewModel = ViewModelProvider(this)[TodoViewModel::class.java]

        //addbtn을 누르면 EditTodoActivity로 이동함
        binding.mainActivityAddBtn.setOnClickListener {
            val intent = Intent(this, EditTodoActivity::class.java).apply {
                putExtra("type", "ADD")
            }
            requestActivity.launch(intent)
            //Toast.makeText(this, "addbtn on", Toast.LENGTH_SHORT).show()
        }
    }

    private val requestActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == RESULT_OK){
            val todo = it.data?.getSerializableExtra("todo") as Todo

            when(it.data?.getIntExtra("flag", -1)){
                0 -> {
                    //데이터베이스에 저장 하는 작업은 시간이 오래 걸리기 때문에 메인스레드에서 동작하지 않게 함
                    //그래서 코루틴을 통해 IO스레드가 저장하는 작업을 수행함
                    //insert 작업은 viewModel에서 담당함
                    CoroutineScope(Dispatchers.IO).launch {
                        todoViewModel.insert(todo) // viewModel -> todoRepository -> todoDao 순으로 타고 들어가 데이터베이스에 저장하게 됨
                    }
                    Toast.makeText(this, "추가되었습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}