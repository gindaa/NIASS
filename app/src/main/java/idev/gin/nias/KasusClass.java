package idev.gin.nias;

public class KasusClass {
    private String idKasus;
    private String mTextnamafaskes;
    private String mTextKota;
    private String mTextProvinsi;
    private String mTextregis;
    private String mTextregisTbKota;
    private String mTextKecamatan;
    private String mTextKelurahan;
    private String mTextNamaPasien;
    private String mTextNik;
    private String mtextjk;
    private String mTextUmur;
    private String mTextAlamat;
    private String mTextRujuk;
    private String mTextdiagnosistb;


    public String getmTextnamafaskes() {
        return mTextnamafaskes;
    }

    public String getmTextKota() {
        return mTextKota;
    }

    public String getmTextProvinsi() {
        return mTextProvinsi;
    }

    public String getmTextNamaPasien() {
        return mTextNamaPasien;
    }

    public String getmTextNik() {
        return mTextNik;
    }

    public String getMtextjk() {
        return mtextjk;
    }

    public String getmTextUmur() {
        return mTextUmur;
    }

    public String getmTextAlamat() {
        return mTextAlamat;
    }

    public String getmTextRujuk() {
        return mTextRujuk;
    }

    public String getmTextregis() {
        return mTextregis;
    }

    public String getmTextregisTbKota() {
        return mTextregisTbKota;
    }

    public String getmTextdiagnosistb() { return mTextdiagnosistb; }
    public String getIdKasus() { return idKasus; }

    public void setIdKasus(String idKasus) { this.idKasus = idKasus; }


    public void setmTextnamafaskes(String mTextnamafaskes) {
        this.mTextnamafaskes = mTextnamafaskes;
    }

    public void setmTextKota(String mTextKota) {
        this.mTextKota = mTextKota;
    }

    public void setmTextProvinsi(String mTextProvinsi) {
        this.mTextProvinsi = mTextProvinsi;
    }

    public void setmTextregis(String mTextregis) {
        this.mTextregis = mTextregis;
    }

    public void setmTextregisTbKota(String mTextregisTbKota) {
        this.mTextregisTbKota = mTextregisTbKota;
    }

    public void setmTextNamaPasien(String mTextNamaPasien) {
        this.mTextNamaPasien = mTextNamaPasien;
    }

    public void setmTextNik(String mTextNik) {
        this.mTextNik = mTextNik;
    }

    public void setMtextjk(String mtextjk) {
        this.mtextjk = mtextjk;
    }

    public void setmTextUmur(String mTextUmur) {
        this.mTextUmur = mTextUmur;
    }

    public void setmTextAlamat(String mTextAlamat) {
        this.mTextAlamat = mTextAlamat;
    }

    public void setmTextRujuk(String mTextRujuk) {
        this.mTextRujuk = mTextRujuk;
    }

    public void setmTextdiagnosistb(String mTextdiagnosistb) {
        this.mTextdiagnosistb = mTextdiagnosistb;
    }

    public void setmTextKecamatan(String mTextKecamatan) {
        this.mTextKecamatan = mTextKecamatan;
    }

    public void setmTextKelurahan(String mTextKelurahan) {
        this.mTextKelurahan = mTextKelurahan;
    }

    public String getmTextKecamatan() {

        return mTextKecamatan;
    }

    public String getmTextKelurahan() {
        return mTextKelurahan;
    }

    public KasusClass(String idKasus, String mTextnamafaskes, String mTextKota, String mTextProvinsi, String mTextregis, String mTextregiskota, String mTextKecamatan , String mTextKelurahan, String mTextNamaPasien, String mTextNik, String mtextjk, String mTextUmur, String mTextAlamat, String mTextRujuk, String mTextdiagnosistb) {
        this.idKasus= idKasus;
        this.mTextnamafaskes = mTextnamafaskes;
        this.mTextKota = mTextKota;
        this.mTextProvinsi = mTextProvinsi;
        this.mTextregis = mTextregis;
        this.mTextregisTbKota = mTextregiskota;
        this.mTextKecamatan = mTextKecamatan;
        this.mTextKelurahan = mTextKelurahan;

        this.mTextNamaPasien = mTextNamaPasien;
        this.mTextNik = mTextNik;
        this.mtextjk = mtextjk;
        this.mTextUmur = mTextUmur;
        this.mTextAlamat = mTextAlamat;
        this.mTextRujuk = mTextRujuk;
        this.mTextdiagnosistb = mTextdiagnosistb;

    }
}