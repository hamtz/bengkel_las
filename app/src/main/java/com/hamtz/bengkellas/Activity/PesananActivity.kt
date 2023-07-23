package com.hamtz.bengkellas.Activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.hamtz.bengkellas.API.APIRequestData
import com.hamtz.bengkellas.API.RetroServer
import com.hamtz.bengkellas.Adapter.AdapterData
import com.hamtz.bengkellas.Model.DataModel
import com.hamtz.bengkellas.Model.ResponseModel
import com.hamtz.bengkellas.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PesananActivity : AppCompatActivity() {

    lateinit var rvData: RecyclerView
    lateinit var listData: ArrayList<DataModel>
    lateinit var srlData: SwipeRefreshLayout
    lateinit var pbData: ProgressBar

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pesanan)

        srlData= findViewById(R.id.srl_data)
        pbData=findViewById(R.id.pb_data)


        rvData = findViewById(R.id.rv_data)
        rvData.layoutManager = LinearLayoutManager(this)
        rvData.setHasFixedSize(true)


        listData = ArrayList()

        srlData.setOnRefreshListener {
            srlData.isRefreshing = true
            retrieveData()
            srlData.isRefreshing = false
        }

        val btBack=findViewById<ImageButton>(R.id.bt_back)
        btBack.setOnClickListener {
           finish()
        }

    }

    override fun onResume() {
        super.onResume()
        retrieveData()
    }

    private fun retrieveData(){
        pbData.setVisibility(View.VISIBLE)
        val ardData: APIRequestData = RetroServer.konekRetrofit().create(APIRequestData::class.java)
        val tampilData: Call<ResponseModel> = ardData.ardRetrieveData()

        tampilData.enqueue(object : Callback<ResponseModel> {
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                val kode = response.body()?.kode
//                val pesan = response.body()?.pesan
                val pesan = "Belum Ada Pesanan"
//
//                Toast.makeText(this@PesananActivity, "Kode :$kode| Pesan : $pesan", Toast.LENGTH_LONG).show()
                val responseData = response.body()?.data

                if (responseData == null){
                    Toast.makeText(this@PesananActivity, " $pesan", Toast.LENGTH_LONG).show()
                }

                listData.clear()
                responseData?.let {
                    listData.addAll(it)
                    var adData = AdapterData(this@PesananActivity,listData)
                    rvData.adapter = adData
                    adData.notifyDataSetChanged()

                    pbData.visibility = View.INVISIBLE

                }
                    pbData.visibility = View.INVISIBLE
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                val pesan = "gagal menghubungi server " + t.message
                Toast.makeText(this@PesananActivity, pesan, Toast.LENGTH_LONG).show()
                pbData.visibility = View.INVISIBLE
            }
        })

    }

}