package com.example.sqlitetofirebase

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    private val storage = FirebaseStorage.getInstance()

    private lateinit var dao: Dao
    lateinit var data: List<RawyatClass>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dao = PaziyletDatabase.getInstance(this).dao()
        btnOK.setOnClickListener {
            data = dao.getAllData()
            data.forEach { rawyat ->
                uploadPhoto(rawyat)
            }
        }
    }

    private fun uploadPhoto(rawyat: RawyatClass) {
        var imgName = rawyat.img.toString().substringAfterLast('/')
        imgName = imgName.replace('-', '_')
        imgName = imgName.removeRange(imgName.length-4, imgName.length)
        val uri = Uri.parse("android.resource://$packageName/drawable/$imgName")
        val ref = storage.reference.child("rawiyat/" + UUID.randomUUID().toString())
        ref.putFile(uri).addOnSuccessListener {
            ref.downloadUrl.addOnSuccessListener {it->
                val map: MutableMap<String, Any> = mutableMapOf()
                map["id"] = UUID.randomUUID().toString()
                map["title"] = rawyat.title.toString()
                map["image"] = it.toString()
                map["text"] = rawyat.full_text.toString()
                map["views"] = rawyat.hits.toString()
                db.collection("rawiyatlar").document(map["id"].toString()).set(map)
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