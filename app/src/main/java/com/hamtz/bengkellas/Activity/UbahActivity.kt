package com.hamtz.bengkellas.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.hamtz.bengkellas.API.APIRequestData
import com.hamtz.bengkellas.API.RetroServer
import com.hamtz.bengkellas.Model.ResponseModel
import com.hamtz.bengkellas.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UbahActivity : AppCompatActivity() {

    private var id:Int = 0
    private var nama: String = ""
    private var alamat: String = ""
    private var telepon: String = ""
    private var xstatus: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ubah)

        // Mendapatkan nilai dari Intent
        val getIntentId: Int = intent.getIntExtra("xId", 0)
        val getIntentNama: String? = intent.getStringExtra("xNama")
        val getIntentAlamat: String? = intent.getStringExtra("xAlamat")
        val getIntentTelepon: String? = intent.getStringExtra("xTelepon")
        val getIntentStatus: String? = intent.getStringExtra("xStatuspesanan")

// Mendeklarasikan dan mendapatkan referensi ke EditText
        val etNama = findViewById<EditText>(R.id.et_nama)
        val etAlamat = findViewById<EditText>(R.id.et_alamat)
        val etTelepon = findViewById<EditText>(R.id.et_telepon)
        val etStatus = findViewById<EditText>(R.id.et_status)

// Mengisi nilai dari Intent ke dalam EditText
        etNama.setText(getIntentNama)
        etAlamat.setText(getIntentAlamat)
        etTelepon.setText(getIntentTelepon)
        etStatus.setText(getIntentStatus)

        val btUbah = findViewById<Button>(R.id.bt_ubah)

        btUbah.setOnClickListener(){
            id = getIntentId
            nama = etNama.text.toString()
            alamat = etAlamat.text.toString()
            telepon = etTelepon.text.toString()
            xstatus = etStatus.text.toString()

            updateData(id,nama,alamat,telepon,xstatus)
            finish()
        }

    }

    private fun updateData(
        id: Int,
        nama: String,
        alamat: String,
        telepon: String,
        status_pesanan: String) {
        val ardData: APIRequestData = RetroServer.konekRetrofit().create(APIRequestData::class.java)
        val updateData: Call<ResponseModel> = ardData.ardUpdateData(id.toString(),nama,alamat,telepon,status_pesanan)
       updateData.enqueue(object : Callback<ResponseModel> {
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                val kode = response.body()?.kode
                val pesan = "Pesanan berhasil diubah"
                Toast.makeText(this@UbahActivity, " $pesan", Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                val pesan = "gagal menghubungi server " + t.message + id +nama +alamat+telepon+status_pesanan
                Toast.makeText(this@UbahActivity, pesan, Toast.LENGTH_LONG).show()

            }

        })
    }
}