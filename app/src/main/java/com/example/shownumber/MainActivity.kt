package com.example.shownumber

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var errorLog: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.editText)
        val radioEven = findViewById<RadioButton>(R.id.radioButton)
        val radioOdd = findViewById<RadioButton>(R.id.radioButton2)
        val radioSquare = findViewById<RadioButton>(R.id.radioButton3)
        val showButton = findViewById<Button>(R.id.button)
        val listView = findViewById<ListView>(R.id.listView)
        val errorLog = findViewById<TextView>(R.id.textView)

        showButton.setOnClickListener {
            val inputText = editText.text.toString()
            if (inputText.isEmpty()) {
                errorLog.text = "Vui lòng nhập một số nguyên dương!"
                return@setOnClickListener
            }

            val n = inputText.toIntOrNull()
            if (n == null || n <= 0) {
                errorLog.text = "Vui lòng nhập một số nguyên dương hợp lệ!"
                return@setOnClickListener
            }

            val resultList = mutableListOf<Int>()
            when {
                radioEven.isChecked -> {
                    for (i in 0..n) {
                        if (i % 2 == 0) resultList.add(i)
                    }
                }
                radioOdd.isChecked -> {
                    for (i in 1..n) {
                        if (i % 2 != 0) resultList.add(i)
                    }
                }
                radioSquare.isChecked -> {
                    for (i in 0..n) {
                        val sqrt = kotlin.math.sqrt(i.toDouble()).toInt()
                        if (sqrt * sqrt == i) resultList.add(i)
                    }
                }
                else -> {
                    errorLog.text = "Vui lòng chọn loại số!"
                    return@setOnClickListener
                }
            }

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, resultList)
            listView.adapter = adapter
            errorLog.text = "" // Xóa thông báo lỗi nếu có
        }
    }
}
