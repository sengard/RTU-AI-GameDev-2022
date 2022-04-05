package com.app.aigame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val items = listOf("Player", "Computer")
        val adapter = ArrayAdapter(this, R.layout.list_item, items)
        textInputLayout.adapter = adapter

        btnDualPlayer.setOnClickListener {
            val i = Intent(this, GameActivity::class.java)
            i.putExtra("cp", textInputLayout.selectedItem.toString())
            startActivity(i)
        }
        btnSinglePlayer.setOnClickListener {
            val i = Intent(this, ComputerActivity::class.java)
            i.putExtra("cp", textInputLayout.selectedItem.toString())
            startActivity(i)
        }


    }
}