package com.hamtz.bengkellas.Activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.hamtz.bengkellas.API.APIRequestData
import com.hamtz.bengkellas.API.RetroServer
import com.hamtz.bengkellas.Adapter.AdapterData
import com.hamtz.bengkellas.Model.DataModel
import com.hamtz.bengkellas.Model.ResponseModel
import com.hamtz.bengkellas.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

//    lateinit var rvData: RecyclerView
//    lateinit var listData: ArrayList<DataModel>
//    lateinit var srlData: SwipeRefreshLayout
//    lateinit var pbData:ProgressBar

    // Konstanta untuk kode permintaan (request code) saat memanggil UserActivity
    companion object {
        private const val REQUEST_LOGOUT = 1
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.hamtz.bengkellas.R.layout.activity_main)

        val getIntentUsername:String = intent.getStringExtra("userrole").toString()
        var nowLogged = getIntentUsername

        val ibUser = findViewById<ImageView>(R.id.ib_user)
        Glide.with(this).load(R.drawable.person).circleCrop().into(ibUser)
        ibUser.setOnClickListener{
            val intent = Intent(this,UserActivity::class.java)
            intent.putExtra("userrole",getIntentUsername)
//            startActivity(intent,REQUEST_LOGOUT)
//            startActivityForResult(intent, REQUEST_LOGOUT)
            startActivityIfNeeded(intent, REQUEST_LOGOUT)
        }

        val imgLogoApp = findViewById<ImageView>(R.id.img_logo_app)
        Glide.with(this).load(R.drawable.logo).circleCrop().into(imgLogoApp)


        val btProduk=findViewById<Button>(com.hamtz.bengkellas.R.id.bt_produk)
        btProduk.setOnClickListener {
            val intent = Intent(this, ProdukActivity::class.java)
            startActivity(intent)
        }
        val btOrder=findViewById<Button>(com.hamtz.bengkellas.R.id.bt_order)
        btOrder.setOnClickListener {
            val intent = Intent(this, TambahActivity::class.java)
            startActivity(intent)
        }
        val btPesanan=findViewById<Button>(com.hamtz.bengkellas.R.id.bt_pesanan)

        btPesanan.setOnClickListener {
            val intent = Intent(this, PesananActivity::class.java)
            startActivity(intent)

//        if (nowLogged.equals("Admin", ignoreCase = true)) {
//            btPesanan.setOnClickListener {
//                val intent = Intent(this, EditPesananActivity::class.java)
//                startActivity(intent)
//            }
//        }else{
//            btPesanan.setOnClickListener {
//                val intent = Intent(this, PesananActivity::class.java)
//                startActivity(intent)
//            }
        }


        val btAbout=findViewById<Button>(com.hamtz.bengkellas.R.id.bt_tentang)
        btAbout.setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }

    }

    // Metode untuk menangani hasil dari aktivitas yang dijalankan dengan startActivityForResult
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_LOGOUT && resultCode == Activity.RESULT_OK) {
            // Jika pengguna telah logout, buka LoginActivity
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
            // Hentikan MainActivity agar tidak bisa kembali lagi dengan tombol back
            finish()
        }
    }

}
