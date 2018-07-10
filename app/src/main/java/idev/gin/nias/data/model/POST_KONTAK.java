package idev.gin.nias.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class POST_KONTAK {
    @SerializedName("tanggal")
    @Expose
    private String tanggal;
    @SerializedName("unitpelayanan")
    @Expose
    private String unitpelayanan;
    @SerializedName("kabupaten")
    @Expose
    private String kabupaten;
    @SerializedName("nik")
    @Expose
    private Integer nik;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("resistan")
    @Expose
    private String resistan;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("nama_kontak")
    @Expose
    private String namaKontak;
    @SerializedName("umurkontak_l")
    @Expose
    private String umurkontakL;
    @SerializedName("umurkontak_p")
    @Expose
    private String umurkontakP;
    @SerializedName("alamat_kontak")
    @Expose
    private String alamatKontak;
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
    @SerializedName("provinsi")
    @Expose
    private String provinsi;
    @SerializedName("hasil_akhir")
    @Expose
    private String hasilAkhir;
    @SerializedName("tindak_lanjut")
    @Expose
    private String tindakLanjut;
    @SerializedName("tanggalmulai")
    @Expose
    private String tanggalmulai;
    @SerializedName("hasil_pp_inh")
    @Expose
    private String hasilPpInh;
    @SerializedName("lokasi")
    @Expose
    private String lokasi;

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getUnitpelayanan() {
        return unitpelayanan;
    }

    public void setUnitpelayanan(String unitpelayanan) {
        this.unitpelayanan = unitpelayanan;
    }

    public String getKabupaten() {
        return kabupaten;
    }

    public void setKabupaten(String kabupaten) {
        this.kabupaten = kabupaten;
    }

    public Integer getNik() {
        return nik;
    }

    public void setNik(Integer nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getResistan() {
        return resistan;
    }

    public void setResistan(String resistan) {
        this.resistan = resistan;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNamaKontak() {
        return namaKontak;
    }

    public void setNamaKontak(String namaKontak) {
        this.namaKontak = namaKontak;
    }

    public String getUmurkontakL() {
        return umurkontakL;
    }

    public void setUmurkontakL(String umurkontakL) {
        this.umurkontakL = umurkontakL;
    }

    public String getUmurkontakP() {
        return umurkontakP;
    }

    public void setUmurkontakP(String umurkontakP) {
        this.umurkontakP = umurkontakP;
    }

    public String getAlamatKontak() {
        return alamatKontak;
    }

    public void setAlamatKontak(String alamatKontak) {
        this.alamatKontak = alamatKontak;
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

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getHasilAkhir() {
        return hasilAkhir;
    }

    public void setHasilAkhir(String hasilAkhir) {
        this.hasilAkhir = hasilAkhir;
    }

    public String getTindakLanjut() {
        return tindakLanjut;
    }

    public void setTindakLanjut(String tindakLanjut) {
        this.tindakLanjut = tindakLanjut;
    }

    public String getTanggalmulai() {
        return tanggalmulai;
    }

    public void setTanggalmulai(String tanggalmulai) {
        this.tanggalmulai = tanggalmulai;
    }

    public String getHasilPpInh() {
        return hasilPpInh;
    }

    public void setHasilPpInh(String hasilPpInh) {
        this.hasilPpInh = hasilPpInh;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    @Override
    public String toString() {
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
                '}';
    }
}

