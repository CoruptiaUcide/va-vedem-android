package com.coruptiaucide.vavedem.api.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import java.util.*

/**
 * Created by tiberiugolaes on 01/05/2017.
 */
internal class Adresa(@Expose val id: Int,
                      @Expose val codPostal: Int,
                      @Expose val localitatea: String,
                      @Expose val nr: Int,
                      @Expose val strada: String
) : Parcelable {
    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(id)
        dest?.writeInt(codPostal)
        dest?.writeString(localitatea)
        dest?.writeInt(nr)
        dest?.writeString(strada)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {

        @SuppressWarnings("rawtypes")
        @JvmField
        val CREATOR: Parcelable.Creator<Adresa> = object : Parcelable.Creator<Adresa> {
            override fun createFromParcel(source: Parcel): Adresa? {
                val id = source.readInt()
                val codPostal = source.readInt()
                val localitate = source.readString()
                val nr = source.readInt()
                val strada = source.readString()
                return Adresa(id, codPostal, localitate, nr, strada)
            }

            override fun newArray(size: Int): Array<Adresa?> {
                return arrayOfNulls(size)
            }
        }
    }
}