package com.example.accesibility

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_age.*
import kotlinx.android.synthetic.main.view_lateral_menu.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        calculateAge()
    }

    private fun calculateAge() {
        img_substract.setOnClickListener {
            val num: Int = text_quantity.text.toString().toInt() - 1
            text_quantity.text = num.toString()
            text_quantity.contentDescription = "$num años"
        }

        img_add.setOnClickListener {
            val num: Int = text_quantity.text.toString().toInt() + 1
            text_quantity.text = num.toString()
            text_quantity.contentDescription = "$num años"
        }
    }
}