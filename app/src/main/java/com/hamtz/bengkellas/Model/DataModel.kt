package com.hamtz.bengkellas.Model

import android.os.Parcel
import android.os.Parcelable

//@Parcelize
data class DataModel(
    var id: Int,
    var nama: String,
    var alamat:String,
    var telepon:String,
    var panjang:Int,
    var lebar:Int,
    var bahan:String,
    var ketebalan:String,
    var kode_desain:String,
    var status_pesanan:String,
    var nilai_lat:String,
    var nilai_lng:String,
    var ongkos:Int
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nama)
        parcel.writeString(alamat)
        parcel.writeString(telepon)
        parcel.writeInt(panjang)
        parcel.writeInt(lebar)
        parcel.writeString(bahan)
        parcel.writeString(ketebalan)
        parcel.writeString(kode_desain)
        parcel.writeString(status_pesanan)
        parcel.writeString(nilai_lat)
        parcel.writeString(nilai_lng)
        parcel.writeInt(ongkos)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DataModel> {
        override fun createFromParcel(parcel: Parcel): DataModel {
            return DataModel(parcel)
        }

        override fun newArray(size: Int): Array<DataModel?> {
            return arrayOfNulls(size)
        }
    }
}