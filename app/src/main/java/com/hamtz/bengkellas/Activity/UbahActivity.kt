package com.hamtz.bengkellas.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.hamtz.bengkellas.API.APIRequestData
import com.hamtz.bengkellas.API.RetroServer
import com.hamtz.bengkellas.Model.DataPesananManager
import com.hamtz.bengkellas.Model.LoginUserManager
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

//        val dataId = DataPesananManager.id
//        val dataNama = DataPesananManager.nama
//        val dataAlamat = DataPesananManager.alamat
//        val dataTelepon = DataPesananManager.telepon
//        val dataStatus = DataPesananManager.status_pesanan

        // Mendapatkan nilai dari Intent
//        val getIntentId: Int = intent.getIntExtra("xId", 0)
//        val getIntentNama: String? = intent.getStringExtra("xNama")
//        val getIntentAlamat: String? = intent.getStringExtra("xAlamat")
//        val getIntentTelepon: String? = intent.getStringExtra("xTelepon")
//        val getIntentStatus: String? = intent.getStringExtra("xStatuspesanan")

// Mendeklarasikan dan mendapatkan referensi ke TextView
        val tvId = findViewById<TextView>(R.id.textViewId)
        val tvNama = findViewById<TextView>(R.id.textViewNama)
        val tvAlamat = findViewById<TextView>(R.id.textViewAlamat)
        val tvTelepon = findViewById<TextView>(R.id.textViewTelepon)
        val tvPanjang = findViewById<TextView>(R.id.textViewPanjang)
        val tvLebar = findViewById<TextView>(R.id.textViewLebar)
        val tvBahan = findViewById<TextView>(R.id.textViewBahan)
        val tvKetebalan = findViewById<TextView>(R.id.textViewKetebalan)
        val tvKode = findViewById<TextView>(R.id.textViewKode)
        val tvBiaya = findViewById<TextView>(R.id.textViewBiaya)
        val tvStatus = findViewById<TextView>(R.id.textViewStatus)
        var biaya = getBiaya()


// Mengisi nilai dari Intent ke dalam EditText
        tvId.text = DataPesananManager.id.toString()
        tvNama.text = DataPesananManager.nama
        tvAlamat.text = DataPesananManager.alamat
        tvTelepon.text =DataPesananManager.telepon
        tvPanjang.text =DataPesananManager.panjang.toString()
        tvLebar.text =DataPesananManager.lebar.toString()
        tvBahan.text =DataPesananManager.bahan
        tvKetebalan.text =DataPesananManager.ketebalan
        tvKode.text =DataPesananManager.kode_desain
        tvBiaya.text =biaya.toString()
        tvStatus.text = DataPesananManager.status_pesanan

        val btSelesai = findViewById<Button>(R.id.bt_selesai)

        btSelesai.setOnClickListener(){
//            id = getIntentId
//            nama = etNama.text.toString()
//            alamat = etAlamat.text.toString()
//            telepon = etTelepon.text.toString()
//            xstatus = etStatus.text.toString()
//
//            updateData(id,nama,alamat,telepon,xstatus)
            finish()
        }

    }

    private fun getBiaya(): Any {
        val a = DataPesananManager.panjang
        val b = DataPesananManager.lebar
        val c = 120000
        val hasil = (a * b)/10000 * c
        return hasil
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