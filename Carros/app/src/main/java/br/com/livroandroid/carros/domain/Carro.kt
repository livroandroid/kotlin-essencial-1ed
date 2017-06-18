package br.com.livroandroid.carros.domain

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Entity(tableName = "carro")
class Carro : Parcelable {
    @PrimaryKey
    var id: Long = 0
    var tipo = ""
    var nome = ""
    var desc = ""
    var urlFoto = ""
    var urlInfo = ""
    var urlVideo = ""

    var latitude: String = ""
        get() = if (field.trim().isEmpty()) "0.0" else field

    var longitude = ""
        get() = if (field.trim().isEmpty()) "0.0" else field

    override fun toString(): String {
        return "Carro{nome='$nome'}"
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        // Escreve os dados para serem serializados
        dest.writeLong(id)
        dest.writeString(this.tipo)
        dest.writeString(this.nome)
        dest.writeString(this.desc)
        dest.writeString(this.urlFoto)
        dest.writeString(this.urlInfo)
        dest.writeString(this.urlVideo)
        dest.writeString(this.latitude)
        dest.writeString(this.longitude)
    }

    fun readFromParcel(parcel: Parcel) {
        // Lê os dados na mesma ordem em que foram escritos
        this.id = parcel.readLong()
        this.tipo = parcel.readString()
        this.nome = parcel.readString()
        this.desc = parcel.readString()
        this.urlFoto = parcel.readString()
        this.urlInfo = parcel.readString()
        this.urlVideo = parcel.readString()
        this.latitude = parcel.readString()
        this.longitude = parcel.readString()
    }

    companion object {
        private val serialVersionUID = 6601006766832473959L
        @JvmField val CREATOR: Parcelable.Creator<Carro> = object : Parcelable.Creator<Carro> {
            override fun createFromParcel(p: Parcel): Carro {
                // Cria o objeto carro com um Parcel
                val c = Carro()
                c.readFromParcel(p)
                return c
            }

            override fun newArray(size: Int): Array<Carro?> {
                // Retorna um array vazio
                return arrayOfNulls(size)
            }
        }
    }
}
