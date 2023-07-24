package com.hamtz.bengkellas.Model

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList

data class ResponseUserModel(
    var kode:Int,
    var pesan:String,
    var data: ArrayList<UserModel>
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        TODO("data")
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(kode)
        parcel.writeString(pesan)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ResponseUserModel> {
        override fun createFromParcel(parcel: Parcel): ResponseUserModel {
            return ResponseUserModel(parcel)
        }

        override fun newArray(size: Int): Array<ResponseUserModel?> {
            return arrayOfNulls(size)
        }
    }
}
