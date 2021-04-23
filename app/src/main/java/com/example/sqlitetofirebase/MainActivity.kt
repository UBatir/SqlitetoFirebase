package com.example.sqlitetofirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val db= FirebaseFirestore.getInstance()
    private lateinit var dao:Dao
    lateinit var  data: List<PostsClass>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dao=PaziyletDatabase.getInstance(this).dao()
        data = dao.getAllData()
        btnOK.setOnClickListener {
            val map:MutableMap<String,Any> = mutableMapOf()
            data.forEach{ it ->
                map["id"] = UUID.randomUUID().toString()
                map["title"] = it.title.toString()
                map["full_text"] = it.full_text.toString()
                map["date"] = it.date.toString()
                map["hits"] = it.hits.toString()
                map["hide"] = it.hide.toString()
                db.collection("posts").document(map["id"].toString()).set(map)
                        .addOnSuccessListener {
                            Toast.makeText(this, "Otlichno", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener {
                            Toast.makeText(this, it.localizedMessage, Toast.LENGTH_SHORT).show()
                        }
            }
        }
    }
}