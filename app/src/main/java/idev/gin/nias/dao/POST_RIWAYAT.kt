package idev.gin.nias.dao

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class POST_RIWAYAT {

    @SerializedName("nama_kader")
    @Expose
    var namaKader: String? = null
    @SerializedName("desa")
    @Expose
    var desa: String? = null
    @SerializedName("tanggal")
    @Expose
    var tanggal: String? = null
    @SerializedName("nama_orantua")
    @Expose
    var namaOrantua: String? = null
    @SerializedName("nama_anak")
    @Expose
    var namaAnak: String? = null
    @SerializedName("usia_anak")
    @Expose
    var usiaAnak: String? = null
    @SerializedName("jumlah_anak")
    @Expose
    var jumlahAnak: String? = null
    @SerializedName("alamat_desa")
    @Expose
    var alamatDesa: String? = null
    @SerializedName("kontak_tb")
    @Expose
    var kontakTb: String? = null
    @SerializedName("berat_badan_59bulan")
    @Expose
    var beratBadan59bulan: String? = null
    @SerializedName("berat_badan_14tahun")
    @Expose
    var beratBadan14tahun: String? = null
    @SerializedName("demam")
    @Expose
    var demam: String? = null
    @SerializedName("batuk")
    @Expose
    var batuk: String? = null
    @SerializedName("pembesaran_kelenjar_limfe")
    @Expose
    var pembesaranKelenjarLimfe: String? = null
    @SerializedName("pembesaran_tulang")
    @Expose
    var pembesaranTulang: String? = null

    override fun toString(): String {
        return "POST_RIWAYAT{" +
                "namaKader='" + namaKader +
                ", desa='" + desa +
                ", tanggal='" + tanggal +
                ", namaOrantua='" + namaOrantua +
                ", namaAnak='" + namaAnak +
                ", usiaAnak='" + usiaAnak +
                ", jumlahAnak='" + jumlahAnak +
                ", alamatDesa='" + alamatDesa +
                ", kontakTb='" + kontakTb +
                ", beratBadan59bulan='" + beratBadan59bulan +
                ", beratBadan14tahun='" + beratBadan14tahun +
                ", demam='" + demam +
                ", batuk='" + batuk +
                ", pembesaranKelenjarLimfe='" + pembesaranKelenjarLimfe +
                ", pembesaranTulang='" + pembesaranTulang +
                '}'
    }
}
