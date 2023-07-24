package com.hamtz.bengkellas.Activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.bumptech.glide.Glide
import com.hamtz.bengkellas.R
import org.w3c.dom.Text

class UserActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val getIntentUsername:String = intent.getStringExtra("userrole").toString()

        val imgUserRole = findViewById<ImageView>(R.id.img_user)
        Glide.with(this).load(R.drawable.person).circleCrop().into(imgUserRole)

        val tvUserRole = findViewById<TextView>(R.id.tv_user_role)
        tvUserRole.text = getIntentUsername

        val btBack=findViewById<ImageButton>(R.id.bt_back)
        btBack.setOnClickListener {
            finish()
        }
        val btLogout=findViewById<Button>(R.id.bt_logout)
        btLogout.setOnClickListener {
            Toast.makeText(this@UserActivity, "Berhasil Logout", Toast.LENGTH_SHORT).show()
//
            setResult(Activity.RESULT_OK)
            finish()

        }
    }
}