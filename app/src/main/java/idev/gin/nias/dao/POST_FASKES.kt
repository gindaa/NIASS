package idev.gin.nias.dao

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class POST_FASKES {

    @SerializedName("tanggal")
    @Expose
    var tanggal: String? = null
    @SerializedName("nama_faskes")
    @Expose
    var namaFaskes: String? = null
    @SerializedName("kabupaten")
    @Expose
    var kabupaten: String? = null
    @SerializedName("provinsi")
    @Expose
    var provinsi: String? = null
    @SerializedName("no_registrasi_faskes")
    @Expose
    var noRegistrasiFaskes: String? = null
    @SerializedName("no_registrasi_tbkota")
    @Expose
    var noRegistrasiTbkota: String? = null
    @SerializedName("nama_pasien")
    @Expose
    var namaPasien: String? = null
    @SerializedName("nik")
    @Expose
    var nik: String? = null
    @SerializedName("jenis_kelamin")
    @Expose
    var jenisKelamin: String? = null
    @SerializedName("umur")
    @Expose
    var umur: String? = null
    @SerializedName("alamat")
    @Expose
    var alamat: String? = null
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
    @SerializedName("perujuk")
    @Expose
    var perujuk: String? = null
    @SerializedName("tipe_diagnosis_tb")
    @Expose
    var tipeDiagnosisTb: String? = null
    @SerializedName("klasifikasi_lokasi_anatomi")
    @Expose
    var klasifikasiLokasiAnatomi: String? = null
    @SerializedName("klasifikasi_status_hiv")
    @Expose
    var klasifikasiStatusHiv: String? = null
    @SerializedName("skoring_tb")
    @Expose
    var skoringTb: String? = null
    @SerializedName("tanggal_mulai_pengobatan")
    @Expose
    var tanggalMulaiPengobatan: String? = null
    @SerializedName("panduan_oat")
    @Expose
    var panduanOat: String? = null
    @SerializedName("sumber_obat")
    @Expose
    var sumberObat: String? = null
    @SerializedName("hasil_mikroskopis")
    @Expose
    var hasilMikroskopis: String? = null
    @SerializedName("hasil_tes_cepat")
    @Expose
    var hasilTesCepat: String? = null
    @SerializedName("hasil_biarkan")
    @Expose
    var hasilBiarkan: String? = null
    @SerializedName("noreglab_bulan2")
    @Expose
    var noreglabBulan2: String? = null
    @SerializedName("mikroskopis_bulan2")
    @Expose
    var mikroskopisBulan2: String? = null
    @SerializedName("noreglab_bulan3")
    @Expose
    var noreglabBulan3: String? = null
    @SerializedName("mikroskopis_bulan3")
    @Expose
    var mikroskopisBulan3: String? = null
    @SerializedName("noreglab_bulan5")
    @Expose
    var noreglabBulan5: String? = null
    @SerializedName("mikroskopis_bulan5")
    @Expose
    var mikroskopisBulan5: String? = null
    @SerializedName("noreglab_akhir")
    @Expose
    var noreglabAkhir: String? = null
    @SerializedName("mikroskopis_akhir")
    @Expose
    var mikroskopisAkhir: String? = null
    @SerializedName("tanggal_akhir")
    @Expose
    var tanggalAkhir: String? = null
    @SerializedName("hasil_akhir")
    @Expose
    var hasilAkhir: String? = null
    @SerializedName("tanggal_dianjurkan_hiv")
    @Expose
    var tanggalDianjurkanHiv: String? = null
    @SerializedName("tanggal_tes_hiv")
    @Expose
    var tanggalTesHiv: String? = null
    @SerializedName("hasil_tes_hiv")
    @Expose
    var hasilTesHiv: String? = null
    @SerializedName("ppk")
    @Expose
    var ppk: String? = null
    @SerializedName("art")
    @Expose
    var art: String? = null
    @SerializedName("dm")
    @Expose
    var dm: String? = null
    @SerializedName("terapi_dm")
    @Expose
    var terapiDm: String? = null
    @SerializedName("dipindah_ke_tb")
    @Expose
    var dipindahKeTb: String? = null
    @SerializedName("keterangan")
    @Expose
    var keterangan: String? = null
    @SerializedName("fk_faskes")
    @Expose
    var fkFaskes: String? = null
    @SerializedName("status")
    @Expose
    var status: String? = null

    override fun toString(): String {
        return "POST_FASKES{" +
                "tanggal='" + tanggal +
                ", namaFaskes='" + namaFaskes +
                ", kabupaten='" + kabupaten +
                ", provinsi='" + provinsi +
                ", noRegistrasiFaskes='" + noRegistrasiFaskes +
                ", noRegistrasiTbkota='" + noRegistrasiTbkota +
                ", namaPasien='" + namaPasien +
                ", nik='" + nik +
                ", jenisKelamin='" + jenisKelamin +
                ", umur='" + umur +
                ", alamat='" + alamat +
                ", rtrw='" + rtrw +
                ", kelurahan='" + kelurahan +
                ", kecamatan='" + kecamatan +
                ", kabupatenkontak='" + kabupatenkontak +
                ", perujuk='" + perujuk +
                ", tipeDiagnosisTb='" + tipeDiagnosisTb +
                ", klasifikasiLokasiAnatomi='" + klasifikasiLokasiAnatomi +
                ", klasifikasiStatusHiv='" + klasifikasiStatusHiv +
                ", skoringTb='" + skoringTb +
                ", tanggalMulaiPengobatan='" + tanggalMulaiPengobatan +
                ", panduanOat='" + panduanOat +
                ", sumberObat='" + sumberObat +
                ", hasilMikroskopis='" + hasilMikroskopis +
                ", hasilTesCepat='" + hasilTesCepat +
                ", hasilBiarkan='" + hasilBiarkan +
                ", noreglabBulan2='" + noreglabBulan2 +
                ", mikroskopisBulan2='" + mikroskopisBulan2 +
                ", noreglabBulan3='" + noreglabBulan3 +
                ", mikroskopisBulan3='" + mikroskopisBulan3 +
                ", noreglabBulan5='" + noreglabBulan5 +
                ", mikroskopisBulan5='" + mikroskopisBulan5 +
                ", noreglabAkhir='" + noreglabAkhir +
                ", mikroskopisAkhir='" + mikroskopisAkhir +
                ", tanggalAkhir='" + tanggalAkhir +
                ", hasilAkhir='" + hasilAkhir +
                ", tanggalDianjurkanHiv='" + tanggalDianjurkanHiv +
                ", tanggalTesHiv='" + tanggalTesHiv +
                ", hasilTesHiv='" + hasilTesHiv +
                ", ppk='" + ppk +
                ", art='" + art +
                ", dm='" + dm +
                ", terapiDm='" + terapiDm +
                ", dipindahKeTb='" + dipindahKeTb +
                ", keterangan='" + keterangan +
                ", fkFaskes='" + fkFaskes +
                ", status='" + status +
                '}'.toString()
    }
}

