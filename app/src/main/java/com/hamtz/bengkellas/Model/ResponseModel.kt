package com.hamtz.bengkellas.Model

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList

data class ResponseModel(
    var kode:Int,
    var pesan:String,
    var data: ArrayList<DataModel>?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.createTypedArrayList(DataModel)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(kode)
        parcel.writeString(pesan)
        parcel.writeTypedList(data)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ResponseModel> {
        override fun createFromParcel(parcel: Parcel): ResponseModel {
            return ResponseModel(parcel)
        }

        override fun newArray(size: Int): Array<ResponseModel?> {
            return arrayOfNulls(size)
        }
    }
}
