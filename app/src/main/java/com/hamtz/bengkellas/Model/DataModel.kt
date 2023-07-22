package com.hamtz.bengkellas.Model

import android.os.Parcel
import android.os.Parcelable

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
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nama)
        parcel.writeString(alamat)
        parcel.writeString(telepon)
        parcel.writeString(panjang.toString())
        parcel.writeString(lebar.toString())
        parcel.writeString(bahan)
        parcel.writeString(ketebalan)
        parcel.writeString(kode_desain)
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
