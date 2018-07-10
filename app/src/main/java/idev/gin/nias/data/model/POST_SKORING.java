package idev.gin.nias.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class POST_SKORING {

    @SerializedName("kontak_tb")
    @Expose
    private String kontakTb;
    @SerializedName("uji_tuberkulin")
    @Expose
    private String ujiTuberkulin;
    @SerializedName("berat_badan")
    @Expose
    private String beratBadan;
    @SerializedName("demam")
    @Expose
    private String demam;
    @SerializedName("batuk_kronik")
    @Expose
    private String batukKronik;
    @SerializedName("pembesaran_kelenjar")
    @Expose
    private String pembesaranKelenjar;
    @SerializedName("pembengkakan_tulang")
    @Expose
    private String pembengkakanTulang;
    @SerializedName("foto_toraks")
    @Expose
    private String fotoToraks;
    @SerializedName("fk_register")
    @Expose
    private String fkRegister;

    public String getKontakTb() {
        return kontakTb;
    }

    public void setKontakTb(String kontakTb) {
        this.kontakTb = kontakTb;
    }

    public String getUjiTuberkulin() {
        return ujiTuberkulin;
    }

    public void setUjiTuberkulin(String ujiTuberkulin) {
        this.ujiTuberkulin = ujiTuberkulin;
    }

    public String getBeratBadan() {
        return beratBadan;
    }

    public void setBeratBadan(String beratBadan) {
        this.beratBadan = beratBadan;
    }

    public String getDemam() {
        return demam;
    }

    public void setDemam(String demam) {
        this.demam = demam;
    }

    public String getBatukKronik() {
        return batukKronik;
    }

    public void setBatukKronik(String batukKronik) {
        this.batukKronik = batukKronik;
    }

    public String getPembesaranKelenjar() {
        return pembesaranKelenjar;
    }

    public void setPembesaranKelenjar(String pembesaranKelenjar) {
        this.pembesaranKelenjar = pembesaranKelenjar;
    }

    public String getPembengkakanTulang() {
        return pembengkakanTulang;
    }

    public void setPembengkakanTulang(String pembengkakanTulang) {
        this.pembengkakanTulang = pembengkakanTulang;
    }

    public String getFotoToraks() {
        return fotoToraks;
    }

    public void setFotoToraks(String fotoToraks) {
        this.fotoToraks = fotoToraks;
    }

    public String getFkRegister() {
        return fkRegister;
    }

    public void setFkRegister(String fkRegister) {
        this.fkRegister = fkRegister;
    }

    @Override
    public String toString() {
        return "POST_SKORING{" +
                "kontakTb='" + kontakTb +
                ", ujiTuberkulin='" + ujiTuberkulin +
                ", beratBadan='" + beratBadan +
                ", demam='" + demam +
                ", batukKronik='" + batukKronik +
                ", pembesaranKelenjar='" + pembesaranKelenjar +
                ", pembengkakanTulang='" + pembengkakanTulang +
                ", fotoToraks='" + fotoToraks +
                ", fkRegister='" + fkRegister +
                '}';
    }
}
