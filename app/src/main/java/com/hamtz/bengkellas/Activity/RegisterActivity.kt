package com.hamtz.bengkellas.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.hamtz.bengkellas.API.APIRequestData
import com.hamtz.bengkellas.API.RetroServer
import com.hamtz.bengkellas.Model.*
import com.hamtz.bengkellas.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    private var vusername: String = ""
    private var vpassword: String = ""
    private var listUser: List<UserModel>? = null // Properti kelas untuk menyimpan data

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val getData: APIRequestData = RetroServer.konekRetrofit().create(APIRequestData::class.java)
        val tampilData: Call<ResponseUserModel> = getData.apiRetrieveUserData()

        val etUsername = findViewById<EditText>(R.id.edt_username)
        val etPassword = findViewById<EditText>(R.id.edt_password)

        tampilData.enqueue(object : Callback<ResponseUserModel> {
            override fun onResponse(call: Call<ResponseUserModel>, response: Response<ResponseUserModel>) {
                if (response.isSuccessful) {
                    val responseUserModel: ResponseUserModel? = response.body()
                    if (responseUserModel != null) {
                        listUser = responseUserModel.data // Menyimpan data ke properti kelas
                        // Sekarang variabel listUser dapat diakses dari semua fungsi dalam kelas
                    }
                } else {
                    // Handle jika respons tidak berhasil
                    val errorMessage = "Response error: ${response.code()}"
                    // Tampilkan pesan error atau lakukan sesuatu yang sesuai
                }
            }

            override fun onFailure(call: Call<ResponseUserModel>, t: Throwable) {
                val errorMessage = "Error: ${t.message}"
                // Tampilkan pesan error atau lakukan sesuatu yang sesuai
            }
        })

        val btRegister = findViewById<Button>(R.id.bt_register)
        btRegister.setOnClickListener {
            vusername = etUsername.text.toString()
            vpassword = etPassword.text.toString()

            if (vusername.isEmpty()) {
                etUsername.error = "Nama Harus Diisi"
            } else if (vpassword.isEmpty()) {
                etPassword.error = "Password Harus Diisi"
            } else{
                registerUser(vusername,vpassword)
            }

        }
    }

    private fun registerUser(vusername: String, vpassword: String) {
        if (listUser != null) {
            val desiredUsername = this.vusername
            val isUsernameExists = listUser!!.any { it.username == desiredUsername }

            if (isUsernameExists == true) {
                Toast.makeText(
                    this@RegisterActivity,
                    "Username "+ "${this.vusername}" + " sudah terdaftar",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val inRole = 2
                val ardRegister: APIRequestData = RetroServer.konekRetrofit().create(APIRequestData::class.java)
                val simpanData: Call<ResponseUserModel> = ardRegister.ardRegisterUser(this.vusername,this.vpassword,"2")
                simpanData.enqueue(object : Callback<ResponseUserModel> {
                    override fun onResponse(
                        call: Call<ResponseUserModel>,
                        response: Response<ResponseUserModel>
                    ) {
                        val kode = response.body()?.kode
                        val pesan = "Pendaftaran Berhasil"
                        Toast.makeText(this@RegisterActivity, " $pesan", Toast.LENGTH_SHORT).show()
                    }
                    override fun onFailure(call: Call<ResponseUserModel>, t: Throwable) {
                        val pesan = "gagal menghubungi server " + t.message
                        Toast.makeText(this@RegisterActivity, "$pesan", Toast.LENGTH_LONG).show()

                    }
                })
                finish()
            }
        }
    }
}
