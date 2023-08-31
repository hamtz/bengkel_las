package com.hamtz.bengkellas.Activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.hamtz.bengkellas.API.APIRequestData
import com.hamtz.bengkellas.API.RetroServer
import com.hamtz.bengkellas.Model.*
import com.hamtz.bengkellas.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private var listUser: List<UserModel>? = null
    lateinit var listData: ArrayList<UserModel>
    lateinit var varUsername:String
    private var username: String = ""
    private var password: String = ""
    lateinit var varPassword:String
    var varRole:Int = 0
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val etUsername = findViewById<EditText>(R.id.et_username)
        val etPassword = findViewById<EditText>(R.id.et_password)
        val txDaftar = findViewById<TextView>(R.id.tv_daftar)



        txDaftar.setOnClickListener(){
            val intent = Intent(this,RegisterActivity::class.java)
//            finish()
            startActivity(intent)
        }

        val btLogin=findViewById<Button>(R.id.bt_login)
        btLogin.setOnClickListener {
            username = etUsername.text.toString()
            password = etPassword.text.toString()

            if (username.isEmpty()) {
                etUsername.error = "Nama Harus Diisi"
            } else if (password.isEmpty()) {
                etPassword.error = "Password Harus Diisi"
            } else {
                // Lakukan validasi dengan memeriksa apakah username dan password sesuai dengan data yang diterima dari server
                val user = listUser?.find { it.username.equals(username, ignoreCase = true) }

                if (user != null && user.password == password) {


                    // Anda dapat mengambil data lain dari objek 'user' seperti role
                    val userLoginName = user.username
                    val userRole = user.role
                    LoginUserManager.username = userLoginName
                    LoginUserManager.role = userRole

                    val intent = Intent(this, MainActivity::class.java)
                    // Pass data role ke activity selanjutnya jika diperlukan
                    intent.putExtra("usernamelogin", userLoginName)
                    intent.putExtra("userrole", userRole)
                    Toast.makeText(this@LoginActivity, "Login Berhasil ", Toast.LENGTH_SHORT).show()

                    finish()
                    startActivity(intent)
                } else {
                    Toast.makeText(this@LoginActivity, "Username atau Password Salah", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()

        val getData: APIRequestData = RetroServer.konekRetrofit().create(APIRequestData::class.java)
        val tampilData: Call<ResponseUserModel> = getData.apiRetrieveUserData()
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
    }
}