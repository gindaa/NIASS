package idev.gin.nias.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class POST_PELACAKAN {
    @SerializedName("tanggal_wawancara")
    @Expose
    private String tanggalWawancara;
    @SerializedName("unit_pelayanan")
    @Expose
    private String unitPelayanan;
    @SerializedName("kabupaten")
    @Expose
    private String kabupaten;
    @SerializedName("nama_kontak")
    @Expose
    private String namaKontak;
    @SerializedName("tanggal_lahir_kontak")
    @Expose
    private String tanggalLahirKontak;
    @SerializedName("jeniskelamin_kontak")
    @Expose
    private String jeniskelaminKontak;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("long")
    @Expose
    private String _long;
    @SerializedName("kontak_serumah")
    @Expose
    private String kontakSerumah;
    @SerializedName("kontak_erat")
    @Expose
    private String kontakErat;
    @SerializedName("gejala_tb")
    @Expose
    private String gejalaTb;
    @SerializedName("tanggal_penyuntikan_uji")
    @Expose
    private String tanggalPenyuntikanUji;
    @SerializedName("tanggal_baca_uji")
    @Expose
    private String tanggalBacaUji;
    @SerializedName("diameter_indurasi")
    @Expose
    private String diameterIndurasi;
    @SerializedName("rontgen_dada")
    @Expose
    private String rontgenDada;
    @SerializedName("tanggal_bta")
    @Expose
    private String tanggalBta;
    @SerializedName("hasil_bta")
    @Expose
    private String hasilBta;
    @SerializedName("tanggal_xpert")
    @Expose
    private String tanggalXpert;
    @SerializedName("hasil_xpert")
    @Expose
    private String hasilXpert;
    @SerializedName("tanggal_biakan")
    @Expose
    private String tanggalBiakan;
    @SerializedName("hasil_biakan")
    @Expose
    private String hasilBiakan;
    @SerializedName("hasil_akhir")
    @Expose
    private String hasilAkhir;
    @SerializedName("faktor_resiko")
    @Expose
    private String faktorResiko;
    @SerializedName("tindak_lanjut")
    @Expose
    private String tindakLanjut;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("fk_register")
    @Expose
    private String fkRegister;

    public String getTanggalWawancara() {
        return tanggalWawancara;
    }

    public void setTanggalWawancara(String tanggalWawancara) {
        this.tanggalWawancara = tanggalWawancara;
    }

    public String getUnitPelayanan() {
        return unitPelayanan;
    }

    public void setUnitPelayanan(String unitPelayanan) {
        this.unitPelayanan = unitPelayanan;
    }

    public String getKabupaten() {
        return kabupaten;
    }

    public void setKabupaten(String kabupaten) {
        this.kabupaten = kabupaten;
    }

    public String getNamaKontak() {
        return namaKontak;
    }

    public void setNamaKontak(String namaKontak) {
        this.namaKontak = namaKontak;
    }

    public String getTanggalLahirKontak() {
        return tanggalLahirKontak;
    }

    public void setTanggalLahirKontak(String tanggalLahirKontak) {
        this.tanggalLahirKontak = tanggalLahirKontak;
    }

    public String getJeniskelaminKontak() {
        return jeniskelaminKontak;
    }

    public void setJeniskelaminKontak(String jeniskelaminKontak) {
        this.jeniskelaminKontak = jeniskelaminKontak;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLong() {
        return _long;
    }

    public void setLong(String _long) {
        this._long = _long;
    }

    public String getKontakSerumah() {
        return kontakSerumah;
    }

    public void setKontakSerumah(String kontakSerumah) {
        this.kontakSerumah = kontakSerumah;
    }

    public String getKontakErat() {
        return kontakErat;
    }

    public void setKontakErat(String kontakErat) {
        this.kontakErat = kontakErat;
    }

    public String getGejalaTb() {
        return gejalaTb;
    }

    public void setGejalaTb(String gejalaTb) {
        this.gejalaTb = gejalaTb;
    }

    public String getTanggalPenyuntikanUji() {
        return tanggalPenyuntikanUji;
    }

    public void setTanggalPenyuntikanUji(String tanggalPenyuntikanUji) {
        this.tanggalPenyuntikanUji = tanggalPenyuntikanUji;
    }

    public String getTanggalBacaUji() {
        return tanggalBacaUji;
    }

    public void setTanggalBacaUji(String tanggalBacaUji) {
        this.tanggalBacaUji = tanggalBacaUji;
    }

    public String getDiameterIndurasi() {
        return diameterIndurasi;
    }

    public void setDiameterIndurasi(String diameterIndurasi) {
        this.diameterIndurasi = diameterIndurasi;
    }

    public String getRontgenDada() {
        return rontgenDada;
    }

    public void setRontgenDada(String rontgenDada) {
        this.rontgenDada = rontgenDada;
    }

    public String getTanggalBta() {
        return tanggalBta;
    }

    public void setTanggalBta(String tanggalBta) {
        this.tanggalBta = tanggalBta;
    }

    public String getHasilBta() {
        return hasilBta;
    }

    public void setHasilBta(String hasilBta) {
        this.hasilBta = hasilBta;
    }

    public String getTanggalXpert() {
        return tanggalXpert;
    }

    public void setTanggalXpert(String tanggalXpert) {
        this.tanggalXpert = tanggalXpert;
    }

    public String getHasilXpert() {
        return hasilXpert;
    }

    public void setHasilXpert(String hasilXpert) {
        this.hasilXpert = hasilXpert;
    }

    public String getTanggalBiakan() {
        return tanggalBiakan;
    }

    public void setTanggalBiakan(String tanggalBiakan) {
        this.tanggalBiakan = tanggalBiakan;
    }

    public String getHasilBiakan() {
        return hasilBiakan;
    }

    public void setHasilBiakan(String hasilBiakan) {
        this.hasilBiakan = hasilBiakan;
    }

    public String getHasilAkhir() {
        return hasilAkhir;
    }

    public void setHasilAkhir(String hasilAkhir) {
        this.hasilAkhir = hasilAkhir;
    }

    public String getFaktorResiko() {
        return faktorResiko;
    }

    public void setFaktorResiko(String faktorResiko) {
        this.faktorResiko = faktorResiko;
    }

    public String getTindakLanjut() {
        return tindakLanjut;
    }

    public void setTindakLanjut(String tindakLanjut) {
        this.tindakLanjut = tindakLanjut;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFkRegister() {
        return fkRegister;
    }

    public void setFkRegister(String fkRegister) {
        this.fkRegister = fkRegister;
    }

    @Override
    public String toString() {
        return "POST_PELACAKAN{" +
                "tanggalWawancara='" + tanggalWawancara +
                ", unitPelayanan='" + unitPelayanan +
                ", kabupaten='" + kabupaten +
                ", namaKontak='" + namaKontak +
                ", tanggalLahirKontak='" + tanggalLahirKontak +
                ", jeniskelaminKontak='" + jeniskelaminKontak +
                ", lat='" + lat +
                ", _long='" + _long +
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
                '}';
    }
}
