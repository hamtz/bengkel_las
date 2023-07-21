package com.hamtz.bengkellas.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hamtz.bengkellas.Model.DataModel
import com.hamtz.bengkellas.R

class AdapterData (
  val ctx:Context,
  val listData:ArrayList<DataModel>,
  ):
  RecyclerView.Adapter<AdapterData.HolderData>() {

  class HolderData(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val tvId: TextView = itemView.findViewById(R.id.tv_id)
    val tvNama: TextView = itemView.findViewById(R.id.tv_nama)
    val tvAlamat: TextView = itemView.findViewById(R.id.tv_alamat)
    val tvTelepon: TextView = itemView.findViewById(R.id.tv_telepon)
    val tvPanjang: TextView = itemView.findViewById(R.id.tv_panjang)
    val tvLebar: TextView = itemView.findViewById(R.id.tv_lebar)
    val tvBahan: TextView = itemView.findViewById(R.id.tv_bahan)
    val tvKetebalan: TextView = itemView.findViewById(R.id.tv_ketebalan)
    val tvDesain: TextView = itemView.findViewById(R.id.tv_desain)
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
    holder.tvTelepon.text = dm.telepon.toString()
    holder.tvPanjang.text = dm.panjang.toString()
    holder.tvLebar.text = dm.lebar.toString()
    holder.tvBahan.text = dm.bahan
    holder.tvKetebalan.text = dm.ketebalan
    holder.tvDesain.text = dm.kode_desain


  }
}
