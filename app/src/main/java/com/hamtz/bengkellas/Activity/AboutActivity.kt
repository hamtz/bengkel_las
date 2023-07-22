package com.hamtz.bengkellas.Activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.hamtz.bengkellas.R

class AboutActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val imgProfile = findViewById<ImageView>(R.id.img_profile)
        Glide.with(this).load(R.drawable.person).circleCrop().into(imgProfile)

    }

}