package com.hamtz.bengkellas.Activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import com.hamtz.bengkellas.R

class ProdukActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_produk)

        val imgPgdSatu = findViewById<ImageView>(R.id.img_pagar_dorong_satu)
        val imgPgdDua = findViewById<ImageView>(R.id.img_pagar_dorong_dua)
        val imgKanopiSatu = findViewById<ImageView>(R.id.img_kanopi_satu)
        val imgKanopiDua = findViewById<ImageView>(R.id.img_kanopi_dua)
        val imgPgSatu = findViewById<ImageView>(R.id.img_pagar_satu)
        val imgPgDua = findViewById<ImageView>(R.id.img_pagar_dua)

        imgPgdSatu.setOnClickListener{
            Toast.makeText(this,"Desain Pagar Dorong 1",Toast.LENGTH_SHORT).show()
        }
        imgPgdDua.setOnClickListener{
            Toast.makeText(this,"Desain Pagar Dorong 2",Toast.LENGTH_SHORT).show()
        }
        imgKanopiSatu.setOnClickListener{
            Toast.makeText(this,"Desain Kanopi 1",Toast.LENGTH_SHORT).show()
        }
        imgKanopiDua.setOnClickListener{
            Toast.makeText(this,"Desain Kanopi 2",Toast.LENGTH_SHORT).show()
        }
        imgPgSatu.setOnClickListener{
            Toast.makeText(this,"Desain Pagar 1",Toast.LENGTH_SHORT).show()
        }
        imgPgDua.setOnClickListener{
            Toast.makeText(this,"Desain Pagar 2",Toast.LENGTH_SHORT).show()
        }

        val btBack=findViewById<ImageButton>(R.id.bt_back)
        btBack.setOnClickListener {
            finish()
        }

    }
}
