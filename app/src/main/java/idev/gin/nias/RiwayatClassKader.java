package idev.gin.nias;

public class RiwayatClassKader {
    private String idriwayat;
    private String namaKaderriwayat;
    private String desariwayat;
    private String tanggalriwayat;
    private String namaOrangtuariwayat;
    private String namaAnakriwayat;
    private Integer usiaAnakriwayat;
    private Integer idfaskesriwayat;



    public RiwayatClassKader(String idriwayat, String namaKaderriwayat, String desariwayat, String tanggalriwayat, String namaOrangtuariwayat, String namaAnakriwayat,int usiaAnakriwayat, Integer idfaskesriwayat) {
        this.idriwayat = idriwayat;
        this.namaKaderriwayat = namaKaderriwayat;
        this.desariwayat = desariwayat;
        this.tanggalriwayat = tanggalriwayat;
        this.namaOrangtuariwayat = namaOrangtuariwayat;
        this.namaAnakriwayat = namaAnakriwayat;
        this.usiaAnakriwayat = usiaAnakriwayat;
        this.idfaskesriwayat = idfaskesriwayat;
    }

    public Integer getUsiaAnakriwayat() {
        return usiaAnakriwayat;
    }

    public void setUsiaAnakriwayat(Integer usiaAnakriwayat) {
        this.usiaAnakriwayat = usiaAnakriwayat;
    }

    public Integer getIdfaskesriwayat() {
        return idfaskesriwayat;
    }

    public void setIdfaskesriwayat(Integer idfaskesriwayat) {
        this.idfaskesriwayat = idfaskesriwayat;
    }

    public Integer getIdfaskes() {
        return idfaskesriwayat;
    }

    public void setIdfaskes(int idfaskes) {
        this.idfaskesriwayat = idfaskesriwayat;
    }

    public String getIdriwayat() {
        return idriwayat;
    }

    public void setIdriwayat(String idriwayat) {
        this.idriwayat = idriwayat;
    }

    public String getNamaKaderriwayat() {
        return namaKaderriwayat;
    }

    public void setNamaKaderriwayat(String namaKaderriwayat) {
        this.namaKaderriwayat = namaKaderriwayat;
    }

    public String getDesariwayat() {
        return desariwayat;
    }

    public void setDesariwayat(String desariwayat) {
        this.desariwayat = desariwayat;
    }

    public String getTanggalriwayat() {
        return tanggalriwayat;
    }

    public void setTanggalriwayat(String tanggalriwayat) {
        this.tanggalriwayat = tanggalriwayat;
    }

    public String getNamaOrangtuariwayat() {
        return namaOrangtuariwayat;
    }

    public void setNamaOrangtuariwayat(String namaOrangtuariwayat) {
        this.namaOrangtuariwayat = namaOrangtuariwayat;
    }

    public String getNamaAnakriwayat() {
        return namaAnakriwayat;
    }

    public void setNamaAnakriwayat(String namaAnakriwayat) {
        this.namaAnakriwayat = namaAnakriwayat;
    }
}
