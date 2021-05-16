package com.example.sqlitetofirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private val db= FirebaseFirestore.getInstance()
    private lateinit var dao:Dao
    lateinit var  data: List<SorawJuwapClass>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dao=PaziyletDatabase.getInstance(this).dao()
        data = dao.getAllData()
        btnOK.setOnClickListener {
            val map:MutableMap<String,Any> = mutableMapOf()
            data.forEach{ str ->
                var soraw = str.soraw.replace("<p><br></p>", "")
                soraw = soraw.removePrefix("<i style=\"color:#900a0a\">Сораў: </i>")
                soraw = soraw.replace("<p>", "")
                soraw = soraw.replace("<o:p></o:p>", "")
                soraw = soraw.replace("<i><span style=\"color: rgb(227, 108, 9);\">Сораў:</span></i><span style=\"color: rgb(227, 108, 9);\"></span>", "")
                soraw = soraw.replace("</p>", "<br><br>")
                soraw = soraw.removeSuffix("<br><br>")
                map["id"] = UUID.randomUUID().toString()
                map["categoryId"]="lJSsuaj1p7uCW5cSw6e7"
                val sdf = SimpleDateFormat("dd.MM.yyyy")
                val date:Date=sdf.parse(sdf.format(Calendar.getInstance().time).toString())
                map["createdAt"]=(date.time)/1000
                map["soraw"] = soraw
                map["juwap"] = str.juwap
                Log.d("soraw", "$soraw \n ${str.juwap}")
                db.collection("questions").document(map["id"].toString()).set(map)
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