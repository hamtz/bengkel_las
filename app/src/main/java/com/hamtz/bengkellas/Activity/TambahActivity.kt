package com.hamtz.bengkellas.Activity

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.hamtz.bengkellas.API.APIRequestData
import com.hamtz.bengkellas.API.RetroServer
import com.hamtz.bengkellas.Model.ResponseModel
import com.hamtz.bengkellas.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TambahActivity : AppCompatActivity() {

    private var nama: String = ""
    private var alamat: String = ""
    private var telepon: String = ""
    private var panjang: String = ""
    private var lebar: String = ""
    private var bahan: String = ""
    private var ketebalan: String = ""
    private var kode_desain: String = ""
//
//    lateinit var alamat: TextView
//    lateinit var telepon: TextView
//    lateinit var panjang: TextView
//    lateinit var lebar: TextView
//    lateinit var bahan: Spinner
//    lateinit var ketebalan: Spinner
//    lateinit var kode_desain: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah)

        //button
        val btPesan = findViewById<Button>(R.id.bt_pesan)
        val etNama = findViewById<EditText>(R.id.et_nama)
        val etAlamat = findViewById<EditText>(R.id.et_alamat)
        val etTelepon = findViewById<EditText>(R.id.et_telepon)
        val etPanjang = findViewById<EditText>(R.id.et_panjang)
        val etLebar = findViewById<EditText>(R.id.et_lebar)
        val spBahan = findViewById<Spinner>(R.id.sp_bahan)
        val spKetebalan = findViewById<Spinner>(R.id.sp_ketebalan)
        val spDesain = findViewById<Spinner>(R.id.sp_kode)


        // access the items of the list
        val listBahan = resources.getStringArray(R.array.List_Bahan)
        val listKetebalan = resources.getStringArray(R.array.List_Tebal)
        val listDesain = resources.getStringArray(R.array.List_Desain)

        // access the spinner
//        val spBahan = findViewById<Spinner>(R.id.sp_bahan)
        if (spBahan != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, listBahan
            )
            spBahan.adapter = adapter

            spBahan.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {

//                    var bahan = spBahan
//                    Toast.makeText(this@TambahActivity,
//                        getString(R.string.selected_item) + " " +
//                                "" + listBahan[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
        if (spKetebalan != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, listKetebalan
            )
            spKetebalan.adapter = adapter

            spKetebalan.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {

//                    ketebalan = spKetebalan
//                    Toast.makeText(this@TambahActivity,
//                        getString(R.string.selected_item) + " " +
//                                "" + listKetebalan[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
        if (spDesain != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, listDesain
            )
            spDesain.adapter = adapter

            spDesain.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {

//                    kode_desain=spDesain
//                    Toast.makeText(this@TambahActivity,
//                        getString(R.string.selected_item) + " " +
//                                "" + listDesain[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        btPesan.setOnClickListener {
            nama = etNama.text.toString()
            alamat = etAlamat.text.toString()
            telepon = etTelepon.text.toString()
            panjang = etPanjang.text.toString()
            lebar = etLebar.text.toString()
            bahan = spBahan.selectedItem.toString()
            ketebalan = spKetebalan.selectedItem.toString()
            kode_desain = spDesain.selectedItem.toString()

            if (nama.isEmpty()) {
                etNama.error = "Nama Harus Diisi"
            } else if (alamat.isEmpty()) {
                etAlamat.error = "Alamat Harus Diisi"
            } else if (telepon.toString().isEmpty()) {
                etTelepon.error = "No.hp Harus Diisi"
            } else if (panjang.toString().isEmpty()) {
                etPanjang.error = "isi Panjang (cm)"
            } else if (lebar.toString().isEmpty()) {
                etLebar.error = "isi Panjang (cm)"
//           }else if(spBahan.toString().isEmpty()){
//               spBahan.selectedItem. ="isi Panjang (cm)"
//           }else if(spKetebalan.toString().isEmpty()){
//               etPanjang.error="isi Panjang (cm)"
//           }else if(spDesain.toString().isEmpty()){
//               etLebar.error="isi lebar (cm)"
            } else {
                createData(nama, alamat, telepon, panjang, lebar, bahan, ketebalan, kode_desain)
//               Toast.makeText(this,(nama+" x " +alamat+" x " + telepon +" x "+ panjang +" x "+ lebar+" x " + bahan +" x "+ketebalan +" x "+ kode_desain ),Toast.LENGTH_LONG).show()
//               Toast.makeText(this,nama,Toast.LENGTH_LONG).show()

            }

        }

    }

    private fun createData(
        nama: String,
        alamat: String,
        telepon: String,
        panjang: String,
        lebar: String,
        bahan: String,
        ketebalan: String,
        kode_desain: String
    ) {
//        Toast.makeText(
//            this,
//            (nama + " x " + alamat + " x " + telepon + " x " + panjang + " x " + lebar + " x " + bahan + " x " + ketebalan + " x " + kode_desain),
//            Toast.LENGTH_LONG
//        ).show()

        val ardData: APIRequestData = RetroServer.konekRetrofit().create(APIRequestData::class.java)
        val simpanData: Call<ResponseModel> = ardData.ardCreateData(nama,alamat,telepon,panjang,lebar,bahan,ketebalan,kode_desain)
//
        simpanData.enqueue(object : Callback<ResponseModel> {
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                val kode = response.body()?.kode
                val pesan = response.body()?.pesan
                Toast.makeText(this@TambahActivity, "Kode :$kode| Pesan : $pesan", Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                val pesan = "gagal menghubungi server " + t.message
//                Toast.makeText(this@TambahActivity,(nama + bahan +ketebalan + kode_desain ),Toast.LENGTH_LONG).show()
                Toast.makeText(this@TambahActivity, pesan, Toast.LENGTH_LONG).show()

            }

        })

    }
}