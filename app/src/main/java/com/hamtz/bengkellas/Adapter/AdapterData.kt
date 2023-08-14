package com.hamtz.bengkellas.Adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.hamtz.bengkellas.API.APIRequestData
import com.hamtz.bengkellas.API.RetroServer
import com.hamtz.bengkellas.Activity.PesananActivity
import com.hamtz.bengkellas.Activity.UbahActivity
import com.hamtz.bengkellas.Model.DataModel
import com.hamtz.bengkellas.Model.ResponseModel
import com.hamtz.bengkellas.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdapterData (
  val ctx:Context,
  val listData:ArrayList<DataModel>,
//  val listPesanan:ArrayList<DataModel>,
//  val idPesanan:Int

  ):
  RecyclerView.Adapter<AdapterData.HolderData>() {

  class HolderData(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val ctx: Context = itemView.context
    val tvId: TextView = itemView.findViewById(R.id.tv_id)
    val tvNama: TextView = itemView.findViewById(R.id.tv_nama)
    val tvAlamat: TextView = itemView.findViewById(R.id.tv_alamat)
    val tvTelepon: TextView = itemView.findViewById(R.id.tv_telepon)
    val tvPanjang: TextView = itemView.findViewById(R.id.tv_panjang)
    val tvLebar: TextView = itemView.findViewById(R.id.tv_lebar)
    val tvBahan: TextView = itemView.findViewById(R.id.tv_bahan)
    val tvKetebalan: TextView = itemView.findViewById(R.id.tv_ketebalan)
    val tvDesain: TextView = itemView.findViewById(R.id.tv_desain)

    init {
      itemView.setOnLongClickListener {
        val dialogPesan = AlertDialog.Builder(ctx)
        dialogPesan.setMessage("Apakah anda ingin membatalkan pesanan?")
        dialogPesan.setCancelable(true)

        val idPesanan = tvId.text.toString().toInt()


        dialogPesan.setPositiveButton("Ya") { dialog, i ->
          // Tindakan ketika tombol "Hapus" di klik
          deleteData(idPesanan)
          val hand = Handler()
          hand.postDelayed({
            (ctx as PesananActivity).retrieveData()
          }, 1000)


        }
//
//        dialogPesan.setNegativeButton("Ubah") { dialog, i ->
//          getData(idPesanan)
//        }

        dialogPesan.show()
        false
      }
    }

    private fun deleteData(idPesanan:Int) {
      val ardData: APIRequestData = RetroServer.konekRetrofit()!!.create(APIRequestData::class.java)
      val hapusData: Call<ResponseModel> = ardData.ardDeleteData(idPesanan)

      hapusData.enqueue(object : Callback<ResponseModel>{
        override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
          val kode = response.body()?.kode
//          val pesan = response.body()?.pesan
          val pesan = "Berhasil membatalkan pesanan"
          Toast.makeText(ctx, "$pesan", Toast.LENGTH_LONG).show()

        }

        override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
          val pesan = "Gagal menghubungi server"
          Toast.makeText(ctx, "$pesan", Toast.LENGTH_LONG).show()

        }

      })
    }

    private fun getData(idPesanan:Int) {
      val ardData: APIRequestData = RetroServer.konekRetrofit()!!.create(APIRequestData::class.java)
      val ambilData: Call<ResponseModel> = ardData.ardGetData(idPesanan)

      ambilData.enqueue(object : Callback<ResponseModel>{
        override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
          val kode = response.body()?.kode
//          val pesan = response.body()?.pesan
          val pesan = "Berhasil membatalkan pesanan"
          val listPesanan = response.body()?.data

          var varIdPesanan= listPesanan?.get(0)?.id
          var varNamaPesanan= listPesanan?.get(0)?.nama
          var varAlamatPesanan= listPesanan?.get(0)?.alamat
          var varTeleponPesanan= listPesanan?.get(0)?.telepon
          var varStatusPesanan= listPesanan?.get(0)?.status_pesanan
          Toast.makeText(ctx, "id "+varIdPesanan+ ", alamat"+varAlamatPesanan+", telepon"+varTeleponPesanan+", status "+varStatusPesanan+ " ", Toast.LENGTH_LONG).show()

          val intent = Intent(ctx, UbahActivity::class.java)
          intent.putExtra("xId",varIdPesanan)
          intent.putExtra("xNama",varNamaPesanan)
          intent.putExtra("xAlamat",varAlamatPesanan)
          intent.putExtra("xTelepon",varTeleponPesanan)
          intent.putExtra("xStatuspesanan",varStatusPesanan)
          startActivity(ctx,intent, Bundle())

        }

        override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
          val pesan = "Gagal menghubungi server"
          Toast.makeText(ctx, "$pesan", Toast.LENGTH_LONG).show()

        }

      })
    }


  }


  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderData {
    val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
    return HolderData(itemView)
  }

  override fun getItemCount(): Int {
    return listData.size
  }

  override fun onBindViewHolder(holder: HolderData, position: Int) {
    val dm = listData[position]

    holder.tvId.text = dm.id.toString()
    holder.tvNama.text = dm.nama
    holder.tvAlamat.text = dm.alamat
    holder.tvTelepon.text = dm.telepon
    holder.tvPanjang.text = dm.panjang.toString()
    holder.tvLebar.text = dm.lebar.toString()
    holder.tvBahan.text = dm.bahan
    holder.tvKetebalan.text = dm.ketebalan
    holder.tvDesain.text = dm.kode_desain


  }
}
