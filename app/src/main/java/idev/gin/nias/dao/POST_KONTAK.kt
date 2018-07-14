package idev.gin.nias.dao

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class POST_KONTAK {
    @SerializedName("tanggal")
    @Expose
    var tanggal: String? = null
    @SerializedName("unitpelayanan")
    @Expose
    var unitpelayanan: String? = null
    @SerializedName("kabupaten")
    @Expose
    var kabupaten: String? = null
    @SerializedName("nik")
    @Expose
    var nik: Int? = null
    @SerializedName("nama")
    @Expose
    var nama: String? = null
    @SerializedName("resistan")
    @Expose
    var resistan: String? = null
    @SerializedName("alamat")
    @Expose
    var alamat: String? = null
    @SerializedName("nama_kontak")
    @Expose
    var namaKontak: String? = null
    @SerializedName("umurkontak_l")
    @Expose
    var umurkontakL: String? = null
    @SerializedName("umurkontak_p")
    @Expose
    var umurkontakP: String? = null
    @SerializedName("alamat_kontak")
    @Expose
    var alamatKontak: String? = null
    @SerializedName("rtrw")
    @Expose
    var rtrw: String? = null
    @SerializedName("kelurahan")
    @Expose
    var kelurahan: String? = null
    @SerializedName("kecamatan")
    @Expose
    var kecamatan: String? = null
    @SerializedName("kabupatenkontak")
    @Expose
    var kabupatenkontak: String? = null
    @SerializedName("provinsi")
    @Expose
    var provinsi: String? = null
    @SerializedName("hasil_akhir")
    @Expose
    var hasilAkhir: String? = null
    @SerializedName("tindak_lanjut")
    @Expose
    var tindakLanjut: String? = null
    @SerializedName("tanggalmulai")
    @Expose
    var tanggalmulai: String? = null
    @SerializedName("hasil_pp_inh")
    @Expose
    var hasilPpInh: String? = null
    @SerializedName("lokasi")
    @Expose
    var lokasi: String? = null

    override fun toString(): String {
        return "Post{" +
                "tanggal='" + tanggal +
                ", unitpelayanan='" +
                ", kabupaten='" + kabupaten +
                ", nik=" + nik +
                ", nama='" + nama +
                ", resistan='" + resistan +
                ", alamat='" + alamat +
                ", namaKontak='" + namaKontak +
                ", umurkontakL='" + umurkontakL +
                ", umurkontakP='" + umurkontakP +
                ", alamatKontak='" + alamatKontak +
                //", rtrw='" + rtrw +
                //", kelurahan='" + kelurahan +
                //", kecamatan='" + kecamatan +
                //", kabupatenkontak='" + kabupatenkontak +
                //", provinsi='" + provinsi +
                ", hasilAkhir='" + hasilAkhir +
                ", tindakLanjut='" + tindakLanjut +
                ", tanggalmulai='" + tanggalmulai +
                ", hasilPpInh='" + hasilPpInh +
                ", lokasi='" + lokasi +
                '}'.toString()
    }
}

