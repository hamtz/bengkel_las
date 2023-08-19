package com.hamtz.bengkellas.Activity

import android.content.Intent
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
    private var status_pesanan: String = ""
    private var nilai_lat: String = ""
    private var nilai_lng: String = ""
    private var ongkos: String = ""


    companion object {
        const val REQUEST_CODE = 1
    }

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
        val btCekLoc = findViewById<Button>(R.id.bt_cekLoc)

        val valueLat: Double = intent.getDoubleExtra("LatValue",0.0)
        val valueLng:Double = intent.getDoubleExtra("LngValue",0.0)
//        tvLat.text = valueLat.toString()
//        tvLng.text = valueLng.toString()
//        Toast.makeText(this@TambahActivity, " "+valueLat+" "+valueLng, Toast.LENGTH_SHORT).show()

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
        val btBack=findViewById<ImageButton>(R.id.bt_back)
        btBack.setOnClickListener {
            finish()
        }

        btCekLoc.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE)
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
            status_pesanan = "0"

            
//            nilai_lat = valueLat.toString()
//            nilai_lng = valueLng.toString()

            if (nama.isEmpty()) {
                etNama.error = "Nama Harus Diisi"
            } else if (alamat.isEmpty()) {
                etAlamat.error = "Alamat Harus Diisi"
            } else if (telepon.isEmpty()) {
                etTelepon.error = "No.hp Harus Diisi"
            } else if (panjang.isEmpty()) {
                etPanjang.error = "isi Panjang (cm)"
            } else if (lebar.isEmpty()) {
                etLebar.error = "isi Panjang (cm)"
//           }else if(spBahan.toString().isEmpty()){
//               spBahan.selectedItem. ="isi Panjang (cm)"
//           }else if(spKetebalan.toString().isEmpty()){
//               etPanjang.error="isi Panjang (cm)"
//           }else if(spDesain.toString().isEmpty()){
//               etLebar.error="isi lebar (cm)"
            } else {
                var biaya = getBiaya().toString()

                createData(nama, alamat, telepon, panjang, lebar, bahan, ketebalan, kode_desain,status_pesanan,nilai_lat,nilai_lng,biaya)
                finish()
//               Toast.makeText(this,(nama+" x " +alamat+" x " + telepon +" x "+ panjang +" x "+ lebar+" x " + bahan +" x "+ketebalan +" x "+ kode_desain ),Toast.LENGTH_LONG).show()
//               Toast.makeText(this,nama,Toast.LENGTH_LONG).show()

            }

        }

    }

    private fun getBiaya(): Any {
        val a = panjang.toInt()
        val b = lebar.toInt()
        val c = 120000
        val hasil = (a * b)/10000 * c
        return hasil
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val tvLat = findViewById<TextView>(R.id.tv_lat)
        val tvLng = findViewById<TextView>(R.id.tv_lng)

        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                val returnedValue1 = data?.getDoubleExtra("returned_value1",0.0)
                val returnedValue2 = data?.getDoubleExtra("returned_value2",0.0)

                nilai_lat = returnedValue1.toString()
                nilai_lng = returnedValue2.toString()

                tvLat.text = returnedValue1.toString()
                tvLng.text = returnedValue2.toString()
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
        kode_desain: String,
        status_pesanan: String,
        nilai_lat: String,
        nilai_lng: String,
        biaya: String,

        ) {
//        Toast.makeText(
//            this,
//            ( "biaya "+biaya),
//            Toast.LENGTH_LONG
//        ).show()

        val ardData: APIRequestData = RetroServer.konekRetrofit().create(APIRequestData::class.java)
        val simpanData: Call<ResponseModel> = ardData.ardCreateData(nama,alamat,telepon,panjang,lebar,bahan,ketebalan,kode_desain,status_pesanan,nilai_lat,nilai_lng,biaya)
        simpanData.enqueue(object : Callback<ResponseModel> {
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                val kode = response.body()?.kode
                val pesan = "Pesanan berhasil ditambahkan dengan biaya: $biaya"
                Toast.makeText(this@TambahActivity, " $pesan", Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                val pesan = "gagal menghubungi server " + t.message
                Toast.makeText(this@TambahActivity, "$pesan", Toast.LENGTH_LONG).show()

            }

        })

    }
}