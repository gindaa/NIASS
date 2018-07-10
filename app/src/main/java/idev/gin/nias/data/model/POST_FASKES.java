package idev.gin.nias.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class POST_FASKES {

    @SerializedName("tanggal")
    @Expose
    private String tanggal;
    @SerializedName("nama_faskes")
    @Expose
    private String namaFaskes;
    @SerializedName("kabupaten")
    @Expose
    private String kabupaten;
    @SerializedName("provinsi")
    @Expose
    private String provinsi;
    @SerializedName("no_registrasi_faskes")
    @Expose
    private String noRegistrasiFaskes;
    @SerializedName("no_registrasi_tbkota")
    @Expose
    private String noRegistrasiTbkota;
    @SerializedName("nama_pasien")
    @Expose
    private String namaPasien;
    @SerializedName("nik")
    @Expose
    private String nik;
    @SerializedName("jenis_kelamin")
    @Expose
    private String jenisKelamin;
    @SerializedName("umur")
    @Expose
    private String umur;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("rtrw")
    @Expose
    private String rtrw;
    @SerializedName("kelurahan")
    @Expose
    private String kelurahan;
    @SerializedName("kecamatan")
    @Expose
    private String kecamatan;
    @SerializedName("kabupatenkontak")
    @Expose
    private String kabupatenkontak;
    @SerializedName("perujuk")
    @Expose
    private String perujuk;
    @SerializedName("tipe_diagnosis_tb")
    @Expose
    private String tipeDiagnosisTb;
    @SerializedName("klasifikasi_lokasi_anatomi")
    @Expose
    private String klasifikasiLokasiAnatomi;
    @SerializedName("klasifikasi_status_hiv")
    @Expose
    private String klasifikasiStatusHiv;
    @SerializedName("skoring_tb")
    @Expose
    private String skoringTb;
    @SerializedName("tanggal_mulai_pengobatan")
    @Expose
    private String tanggalMulaiPengobatan;
    @SerializedName("panduan_oat")
    @Expose
    private String panduanOat;
    @SerializedName("sumber_obat")
    @Expose
    private String sumberObat;
    @SerializedName("hasil_mikroskopis")
    @Expose
    private String hasilMikroskopis;
    @SerializedName("hasil_tes_cepat")
    @Expose
    private String hasilTesCepat;
    @SerializedName("hasil_biarkan")
    @Expose
    private String hasilBiarkan;
    @SerializedName("noreglab_bulan2")
    @Expose
    private String noreglabBulan2;
    @SerializedName("mikroskopis_bulan2")
    @Expose
    private String mikroskopisBulan2;
    @SerializedName("noreglab_bulan3")
    @Expose
    private String noreglabBulan3;
    @SerializedName("mikroskopis_bulan3")
    @Expose
    private String mikroskopisBulan3;
    @SerializedName("noreglab_bulan5")
    @Expose
    private String noreglabBulan5;
    @SerializedName("mikroskopis_bulan5")
    @Expose
    private String mikroskopisBulan5;
    @SerializedName("noreglab_akhir")
    @Expose
    private String noreglabAkhir;
    @SerializedName("mikroskopis_akhir")
    @Expose
    private String mikroskopisAkhir;
    @SerializedName("tanggal_akhir")
    @Expose
    private String tanggalAkhir;
    @SerializedName("hasil_akhir")
    @Expose
    private String hasilAkhir;
    @SerializedName("tanggal_dianjurkan_hiv")
    @Expose
    private String tanggalDianjurkanHiv;
    @SerializedName("tanggal_tes_hiv")
    @Expose
    private String tanggalTesHiv;
    @SerializedName("hasil_tes_hiv")
    @Expose
    private String hasilTesHiv;
    @SerializedName("ppk")
    @Expose
    private String ppk;
    @SerializedName("art")
    @Expose
    private String art;
    @SerializedName("dm")
    @Expose
    private String dm;
    @SerializedName("terapi_dm")
    @Expose
    private String terapiDm;
    @SerializedName("dipindah_ke_tb")
    @Expose
    private String dipindahKeTb;
    @SerializedName("keterangan")
    @Expose
    private String keterangan;
    @SerializedName("fk_faskes")
    @Expose
    private String fkFaskes;
    @SerializedName("status")
    @Expose
    private String status;

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getNamaFaskes() {
        return namaFaskes;
    }

    public void setNamaFaskes(String namaFaskes) {
        this.namaFaskes = namaFaskes;
    }

    public String getKabupaten() {
        return kabupaten;
    }

    public void setKabupaten(String kabupaten) {
        this.kabupaten = kabupaten;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getNoRegistrasiFaskes() {
        return noRegistrasiFaskes;
    }

    public void setNoRegistrasiFaskes(String noRegistrasiFaskes) {
        this.noRegistrasiFaskes = noRegistrasiFaskes;
    }

    public String getNoRegistrasiTbkota() {
        return noRegistrasiTbkota;
    }

    public void setNoRegistrasiTbkota(String noRegistrasiTbkota) {
        this.noRegistrasiTbkota = noRegistrasiTbkota;
    }

    public String getNamaPasien() {
        return namaPasien;
    }

    public void setNamaPasien(String namaPasien) {
        this.namaPasien = namaPasien;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getRtrw() {
        return rtrw;
    }

    public void setRtrw(String rtrw) {
        this.rtrw = rtrw;
    }

    public String getKelurahan() {
        return kelurahan;
    }

    public void setKelurahan(String kelurahan) {
        this.kelurahan = kelurahan;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getKabupatenkontak() {
        return kabupatenkontak;
    }

    public void setKabupatenkontak(String kabupatenkontak) {
        this.kabupatenkontak = kabupatenkontak;
    }

    public String getPerujuk() {
        return perujuk;
    }

    public void setPerujuk(String perujuk) {
        this.perujuk = perujuk;
    }

    public String getTipeDiagnosisTb() {
        return tipeDiagnosisTb;
    }

    public void setTipeDiagnosisTb(String tipeDiagnosisTb) {
        this.tipeDiagnosisTb = tipeDiagnosisTb;
    }

    public String getKlasifikasiLokasiAnatomi() {
        return klasifikasiLokasiAnatomi;
    }

    public void setKlasifikasiLokasiAnatomi(String klasifikasiLokasiAnatomi) {
        this.klasifikasiLokasiAnatomi = klasifikasiLokasiAnatomi;
    }

    public String getKlasifikasiStatusHiv() {
        return klasifikasiStatusHiv;
    }

    public void setKlasifikasiStatusHiv(String klasifikasiStatusHiv) {
        this.klasifikasiStatusHiv = klasifikasiStatusHiv;
    }

    public String getSkoringTb() {
        return skoringTb;
    }

    public void setSkoringTb(String skoringTb) {
        this.skoringTb = skoringTb;
    }

    public String getTanggalMulaiPengobatan() {
        return tanggalMulaiPengobatan;
    }

    public void setTanggalMulaiPengobatan(String tanggalMulaiPengobatan) {
        this.tanggalMulaiPengobatan = tanggalMulaiPengobatan;
    }

    public String getPanduanOat() {
        return panduanOat;
    }

    public void setPanduanOat(String panduanOat) {
        this.panduanOat = panduanOat;
    }

    public String getSumberObat() {
        return sumberObat;
    }

    public void setSumberObat(String sumberObat) {
        this.sumberObat = sumberObat;
    }

    public String getHasilMikroskopis() {
        return hasilMikroskopis;
    }

    public void setHasilMikroskopis(String hasilMikroskopis) {
        this.hasilMikroskopis = hasilMikroskopis;
    }

    public String getHasilTesCepat() {
        return hasilTesCepat;
    }

    public void setHasilTesCepat(String hasilTesCepat) {
        this.hasilTesCepat = hasilTesCepat;
    }

    public String getHasilBiarkan() {
        return hasilBiarkan;
    }

    public void setHasilBiarkan(String hasilBiarkan) {
        this.hasilBiarkan = hasilBiarkan;
    }

    public String getNoreglabBulan2() {
        return noreglabBulan2;
    }

    public void setNoreglabBulan2(String noreglabBulan2) {
        this.noreglabBulan2 = noreglabBulan2;
    }

    public String getMikroskopisBulan2() {
        return mikroskopisBulan2;
    }

    public void setMikroskopisBulan2(String mikroskopisBulan2) {
        this.mikroskopisBulan2 = mikroskopisBulan2;
    }

    public String getNoreglabBulan3() {
        return noreglabBulan3;
    }

    public void setNoreglabBulan3(String noreglabBulan3) {
        this.noreglabBulan3 = noreglabBulan3;
    }

    public String getMikroskopisBulan3() {
        return mikroskopisBulan3;
    }

    public void setMikroskopisBulan3(String mikroskopisBulan3) {
        this.mikroskopisBulan3 = mikroskopisBulan3;
    }

    public String getNoreglabBulan5() {
        return noreglabBulan5;
    }

    public void setNoreglabBulan5(String noreglabBulan5) {
        this.noreglabBulan5 = noreglabBulan5;
    }

    public String getMikroskopisBulan5() {
        return mikroskopisBulan5;
    }

    public void setMikroskopisBulan5(String mikroskopisBulan5) {
        this.mikroskopisBulan5 = mikroskopisBulan5;
    }

    public String getNoreglabAkhir() {
        return noreglabAkhir;
    }

    public void setNoreglabAkhir(String noreglabAkhir) {
        this.noreglabAkhir = noreglabAkhir;
    }

    public String getMikroskopisAkhir() {
        return mikroskopisAkhir;
    }

    public void setMikroskopisAkhir(String mikroskopisAkhir) {
        this.mikroskopisAkhir = mikroskopisAkhir;
    }

    public String getTanggalAkhir() {
        return tanggalAkhir;
    }

    public void setTanggalAkhir(String tanggalAkhir) {
        this.tanggalAkhir = tanggalAkhir;
    }

    public String getHasilAkhir() {
        return hasilAkhir;
    }

    public void setHasilAkhir(String hasilAkhir) {
        this.hasilAkhir = hasilAkhir;
    }

    public String getTanggalDianjurkanHiv() {
        return tanggalDianjurkanHiv;
    }

    public void setTanggalDianjurkanHiv(String tanggalDianjurkanHiv) {
        this.tanggalDianjurkanHiv = tanggalDianjurkanHiv;
    }

    public String getTanggalTesHiv() {
        return tanggalTesHiv;
    }

    public void setTanggalTesHiv(String tanggalTesHiv) {
        this.tanggalTesHiv = tanggalTesHiv;
    }

    public String getHasilTesHiv() {
        return hasilTesHiv;
    }

    public void setHasilTesHiv(String hasilTesHiv) {
        this.hasilTesHiv = hasilTesHiv;
    }

    public String getPpk() {
        return ppk;
    }

    public void setPpk(String ppk) {
        this.ppk = ppk;
    }

    public String getArt() {
        return art;
    }

    public void setArt(String art) {
        this.art = art;
    }

    public String getDm() {
        return dm;
    }

    public void setDm(String dm) {
        this.dm = dm;
    }

    public String getTerapiDm() {
        return terapiDm;
    }

    public void setTerapiDm(String terapiDm) {
        this.terapiDm = terapiDm;
    }

    public String getDipindahKeTb() {
        return dipindahKeTb;
    }

    public void setDipindahKeTb(String dipindahKeTb) {
        this.dipindahKeTb = dipindahKeTb;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getFkFaskes() {
        return fkFaskes;
    }

    public void setFkFaskes(String fkFaskes) {
        this.fkFaskes = fkFaskes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
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
                '}';
    }
}

