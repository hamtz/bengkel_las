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
    val tvUsername: TextView = itemView.findViewById(R.id.tv_username)
    val tvPassword: TextView = itemView.findViewById(R.id.tv_password)
    val tvRole: TextView = itemView.findViewById(R.id.tv_role)
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
    holder.tvUsername.text = dm.username
    holder.tvPassword.text = dm.password
    holder.tvRole.text = dm.role.toString()

  }
}
