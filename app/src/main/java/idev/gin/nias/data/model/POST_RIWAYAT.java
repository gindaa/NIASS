package idev.gin.nias.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class POST_RIWAYAT {

    @SerializedName("nama_kader")
    @Expose
    private String namaKader;
    @SerializedName("desa")
    @Expose
    private String desa;
    @SerializedName("tanggal")
    @Expose
    private String tanggal;
    @SerializedName("nama_orantua")
    @Expose
    private String namaOrantua;
    @SerializedName("nama_anak")
    @Expose
    private String namaAnak;
    @SerializedName("usia_anak")
    @Expose
    private String usiaAnak;
    @SerializedName("jumlah_anak")
    @Expose
    private String jumlahAnak;
    @SerializedName("alamat_desa")
    @Expose
    private String alamatDesa;
    @SerializedName("kontak_tb")
    @Expose
    private String kontakTb;
    @SerializedName("berat_badan_59bulan")
    @Expose
    private String beratBadan59bulan;
    @SerializedName("berat_badan_14tahun")
    @Expose
    private String beratBadan14tahun;
    @SerializedName("demam")
    @Expose
    private String demam;
    @SerializedName("batuk")
    @Expose
    private String batuk;
    @SerializedName("pembesaran_kelenjar_limfe")
    @Expose
    private String pembesaranKelenjarLimfe;
    @SerializedName("pembesaran_tulang")
    @Expose
    private String pembesaranTulang;

    public String getNamaKader() {
        return namaKader;
    }

    public void setNamaKader(String namaKader) {
        this.namaKader = namaKader;
    }

    public String getDesa() {
        return desa;
    }

    public void setDesa(String desa) {
        this.desa = desa;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getNamaOrantua() {
        return namaOrantua;
    }

    public void setNamaOrantua(String namaOrantua) {
        this.namaOrantua = namaOrantua;
    }

    public String getNamaAnak() {
        return namaAnak;
    }

    public void setNamaAnak(String namaAnak) {
        this.namaAnak = namaAnak;
    }

    public String getUsiaAnak() {
        return usiaAnak;
    }

    public void setUsiaAnak(String usiaAnak) {
        this.usiaAnak = usiaAnak;
    }

    public String getJumlahAnak() {
        return jumlahAnak;
    }

    public void setJumlahAnak(String jumlahAnak) {
        this.jumlahAnak = jumlahAnak;
    }

    public String getAlamatDesa() {
        return alamatDesa;
    }

    public void setAlamatDesa(String alamatDesa) {
        this.alamatDesa = alamatDesa;
    }

    public String getKontakTb() {
        return kontakTb;
    }

    public void setKontakTb(String kontakTb) {
        this.kontakTb = kontakTb;
    }

    public String getBeratBadan59bulan() {
        return beratBadan59bulan;
    }

    public void setBeratBadan59bulan(String beratBadan59bulan) {
        this.beratBadan59bulan = beratBadan59bulan;
    }

    public String getBeratBadan14tahun() {
        return beratBadan14tahun;
    }

    public void setBeratBadan14tahun(String beratBadan14tahun) {
        this.beratBadan14tahun = beratBadan14tahun;
    }

    public String getDemam() {
        return demam;
    }

    public void setDemam(String demam) {
        this.demam = demam;
    }

    public String getBatuk() {
        return batuk;
    }

    public void setBatuk(String batuk) {
        this.batuk = batuk;
    }

    public String getPembesaranKelenjarLimfe() {
        return pembesaranKelenjarLimfe;
    }

    public void setPembesaranKelenjarLimfe(String pembesaranKelenjarLimfe) {
        this.pembesaranKelenjarLimfe = pembesaranKelenjarLimfe;
    }

    public String getPembesaranTulang() {
        return pembesaranTulang;
    }

    public void setPembesaranTulang(String pembesaranTulang) {
        this.pembesaranTulang = pembesaranTulang;
    }

    @Override
    public String toString() {
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
                '}';
    }
}
