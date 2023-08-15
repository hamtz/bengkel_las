package com.hamtz.bengkellas.Activity

import android.content.Intent
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

class LoginActivity : AppCompatActivity() {
    lateinit var listData: ArrayList<UserModel>
    lateinit var varUsername:String
    private var username: String = ""
    private var password: String = ""
    lateinit var varPassword:String
    var varRole:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val getData: APIRequestData = RetroServer.konekRetrofit().create(APIRequestData::class.java)
        val tampilData: Call<ResponseUserModel> = getData.apiRetrieveUserData()
        val etUsername = findViewById<EditText>(R.id.et_username)
        val etPassword = findViewById<EditText>(R.id.et_password)

        tampilData.enqueue(object : Callback<ResponseUserModel>{
            override fun onResponse(call: Call<ResponseUserModel>,response: Response<ResponseUserModel>) {
                val kode = response.body()?.kode
                val pesan = response.body()?.pesan
//                val pesan = "Belum Ada Pesanan"
                val listUser = response.body()?.data

                varUsername = listUser?.get(0)?.username.toString()
                varPassword = listUser?.get(0)?.password.toString()
                varRole = listUser?.get(0)?.role!!

//                Toast.makeText(this@LoginActivity, "Kode :$kode| Pesan : $pesan | $varUsername | $varPassword | $varRole", Toast.LENGTH_LONG).show()

            }

            override fun onFailure(call: Call<ResponseUserModel>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })


        val btLogin=findViewById<Button>(R.id.bt_login)
        btLogin.setOnClickListener {
            username = etUsername.text.toString()
            password = etPassword.text.toString()

            if (username.isEmpty()) {
                etUsername.error = "Nama Harus Diisi"
            } else if (password.isEmpty()) {
                etPassword.error = "Password Harus Diisi"
            }
            LoginUserManager.username = username

            var getUsername = varUsername

            if (username == getUsername){
                Toast.makeText(this@LoginActivity, "Login Berhasil", Toast.LENGTH_SHORT).show()
//                Toast.makeText(this@LoginActivity, "Username: $varUsername |Password: $varPassword |Role: $varRole", Toast.LENGTH_SHORT).show()
                val intent = Intent(this,MainActivity::class.java)
//                intent.putExtra("userrole","ADMIN")
                finish()
                startActivity(intent)

            }else if (username == "user"){
                Toast.makeText(this@LoginActivity, "Login Berhasil", Toast.LENGTH_SHORT).show()
                val intent = Intent(this,MainActivity::class.java)
//                intent.putExtra("userrole","USER")
                finish()
                startActivity(intent)

            }else{
                Toast.makeText(this@LoginActivity, "Username Tidak Terdaftar", Toast.LENGTH_SHORT).show()
            }


//            val intent = Intent(this, TambahActivity::class.java)
//            startActivity(intent)


        }


    }
}