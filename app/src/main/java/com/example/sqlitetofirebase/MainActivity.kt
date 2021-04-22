package com.example.sqlitetofirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val db= FirebaseFirestore.getInstance()
    private lateinit var dao:Dao
    lateinit var  sorawJuwapClasses: List<SorawJuwapClass>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dao=PaziyletDatabase.getInstance(this).dao()
        sorawJuwapClasses = dao.getAllData()
        btnOK.setOnClickListener {
            val map:MutableMap<String,Any> = mutableMapOf()
            sorawJuwapClasses.forEach{
                map["id"] = it.id.toString()
                map["soraw"] = it.soraw.toString()
                map["juwap"] = it.juwap.toString()
                db.collection("patua").document().set(map)
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