package com.hamtz.bengkellas.Activity

import android.annotation.SuppressLint
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

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.hamtz.bengkellas.R.layout.activity_main)

        val imgLogoApp = findViewById<ImageView>(R.id.img_logo_app)
        Glide.with(this).load(R.drawable.logo).circleCrop().into(imgLogoApp)

//        srlData= findViewById(com.hamtz.bengkellas.R.id.srl_data)
//        pbData=findViewById(com.hamtz.bengkellas.R.id.pb_data)
//
//
//        rvData = findViewById(com.hamtz.bengkellas.R.id.rv_data)
//        rvData.layoutManager = LinearLayoutManager(this)
//        rvData.setHasFixedSize(true)

//        listData = ArrayList()


//        srlData.setOnRefreshListener {
//            srlData.isRefreshing = true
//            retrieveData()
//            srlData.isRefreshing = false
//        }

//<------bukan------------->
//        val adapter = AdapterData(listData,this)
//        val lmData = LinearLayoutManager(this)

//        DataUtilities.getAPIRequestData()?.getUserData()
//            ?.enqueue(object:Call<List<DataModel>>,Callback<List<DataModel>>{
//
//        })
//<<-------bukan----------->


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
        }
        val btAbout=findViewById<Button>(com.hamtz.bengkellas.R.id.bt_tentang)
        btAbout.setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }

    }

//    override fun onResume() {
//        super.onResume()
//        retrieveData()
//    }


//    private fun retrieveData(){
//        pbData.setVisibility(View.VISIBLE)
//        val ardData: APIRequestData = RetroServer.konekRetrofit()!!.create(APIRequestData::class.java)
//        val tampilData: Call<ResponseModel> = ardData.ardRetrieveData()
//
//        tampilData.enqueue(object : Callback<ResponseModel> {
//            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
//                val kode = response.body()?.kode
//                val pesan = response.body()?.pesan
//
//                Toast.makeText(this@MainActivity, "Kode :$kode| Pesan : $pesan", Toast.LENGTH_LONG).show()
//                val responseData = response.body()?.data
//
//                listData.clear()
//                responseData?.let {
//                    listData.addAll(it)
//                    var adData = AdapterData(this@MainActivity,listData)
//                    rvData.adapter = adData
//                    adData.notifyDataSetChanged()
//
//                    pbData.setVisibility(View.INVISIBLE)
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
//                val pesan = "gagal menghubungi server " + t.message
//                Toast.makeText(this@MainActivity, pesan, Toast.LENGTH_LONG).show()
//                pbData.setVisibility(View.INVISIBLE)
//            }
//        })
//
//
//
//    }




}
