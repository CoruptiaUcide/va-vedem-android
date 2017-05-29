package com.coruptiaucide.vavedem.api.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import java.util.*

/**
 * Created by tiberiugolaes on 01/05/2017.
 */
internal class Primarie(
        @Expose val id: Int,
        @Expose val adresa: Adresa,
        @Expose val codFiscal: Int,
        @Expose val email: String,
        @Expose val nume: String,
        @Expose val populatie: Int,
        @Expose val telefon: String) : Parcelable {

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(id)
        dest?.writeParcelable(adresa, flags)
        dest?.writeInt(codFiscal)
        dest?.writeString(email)
        dest?.writeString(nume)
        dest?.writeInt(populatie)
        dest?.writeString(telefon)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {

        @JvmField
        @Suppress("INACCESSIBLE_TYPE")
        val CREATOR: Parcelable.Creator<Primarie> = object : Parcelable.Creator<Primarie> {
            override fun createFromParcel(source: Parcel): Primarie? {
                val id = source.readInt()
                val adresa = source.readParcelable<Adresa>(Adresa::class.java.classLoader)
                val codFiscal = source.readInt()
                val email = source.readString()
                val nume = source.readString()
                val populatie = source.readInt()
                val telefon = source.readString()
                return Primarie(id, adresa, codFiscal, email, nume, populatie, telefon)
            }

            override fun newArray(size: Int): Array<Primarie?> {
                return arrayOfNulls(size)
            }
        }
    }
}