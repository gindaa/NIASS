package idev.gin.nias.dao

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class POST_PELACAKAN {
    @SerializedName("tanggal_wawancara")
    @Expose
    var tanggalWawancara: String? = null
    @SerializedName("unit_pelayanan")
    @Expose
    var unitPelayanan: String? = null
    @SerializedName("kabupaten")
    @Expose
    var kabupaten: String? = null
    @SerializedName("nama_kontak")
    @Expose
    var namaKontak: String? = null
    @SerializedName("tanggal_lahir_kontak")
    @Expose
    var tanggalLahirKontak: String? = null
    @SerializedName("jeniskelamin_kontak")
    @Expose
    var jeniskelaminKontak: String? = null
    @SerializedName("lat")
    @Expose
    var lat: String? = null
    @SerializedName("long")
    @Expose
    var long: String? = null
    @SerializedName("kontak_serumah")
    @Expose
    var kontakSerumah: String? = null
    @SerializedName("kontak_erat")
    @Expose
    var kontakErat: String? = null
    @SerializedName("gejala_tb")
    @Expose
    var gejalaTb: String? = null
    @SerializedName("tanggal_penyuntikan_uji")
    @Expose
    var tanggalPenyuntikanUji: String? = null
    @SerializedName("tanggal_baca_uji")
    @Expose
    var tanggalBacaUji: String? = null
    @SerializedName("diameter_indurasi")
    @Expose
    var diameterIndurasi: String? = null
    @SerializedName("rontgen_dada")
    @Expose
    var rontgenDada: String? = null
    @SerializedName("tanggal_bta")
    @Expose
    var tanggalBta: String? = null
    @SerializedName("hasil_bta")
    @Expose
    var hasilBta: String? = null
    @SerializedName("tanggal_xpert")
    @Expose
    var tanggalXpert: String? = null
    @SerializedName("hasil_xpert")
    @Expose
    var hasilXpert: String? = null
    @SerializedName("tanggal_biakan")
    @Expose
    var tanggalBiakan: String? = null
    @SerializedName("hasil_biakan")
    @Expose
    var hasilBiakan: String? = null
    @SerializedName("hasil_akhir")
    @Expose
    var hasilAkhir: String? = null
    @SerializedName("faktor_resiko")
    @Expose
    var faktorResiko: String? = null
    @SerializedName("tindak_lanjut")
    @Expose
    var tindakLanjut: String? = null
    @SerializedName("status")
    @Expose
    var status: String? = null
    @SerializedName("fk_register")
    @Expose
    var fkRegister: String? = null

    override fun toString(): String {
        return "POST_PELACAKAN{" +
                "tanggalWawancara='" + tanggalWawancara +
                ", unitPelayanan='" + unitPelayanan +
                ", kabupaten='" + kabupaten +
                ", namaKontak='" + namaKontak +
                ", tanggalLahirKontak='" + tanggalLahirKontak +
                ", jeniskelaminKontak='" + jeniskelaminKontak +
                ", lat='" + lat +
                ", _long='" + long +
                ", kontakSerumah='" + kontakSerumah +
                ", kontakErat='" + kontakErat +
                ", gejalaTb='" + gejalaTb +
                ", tanggalPenyuntikanUji='" + tanggalPenyuntikanUji +
                ", tanggalBacaUji='" + tanggalBacaUji +
                ", diameterIndurasi='" + diameterIndurasi +
                ", rontgenDada='" + rontgenDada +
                ", tanggalBta='" + tanggalBta +
                ", hasilBta='" + hasilBta +
                ", tanggalXpert='" + tanggalXpert +
                ", hasilXpert='" + hasilXpert +
                ", tanggalBiakan='" + tanggalBiakan +
                ", hasilBiakan='" + hasilBiakan +
                ", hasilAkhir='" + hasilAkhir +
                ", faktorResiko='" + faktorResiko +
                ", tindakLanjut='" + tindakLanjut +
                ", status='" + status +
                ", fkRegister='" + fkRegister +
                '}'.toString()
    }
}
