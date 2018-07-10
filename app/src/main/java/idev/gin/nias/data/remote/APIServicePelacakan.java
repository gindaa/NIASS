package idev.gin.nias.data.remote;

import idev.gin.nias.data.model.POST_PELACAKAN;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;

public interface APIServicePelacakan {
    @retrofit2.http.POST("/pelacakan")
    @FormUrlEncoded
    Call<POST_PELACAKAN> savePost(
            @Field("tanggal_wawancara") String wawancara,
            @Field("unitpelayanan") String unitPelayanan,
            @Field("kabupaten") String kabupaten,
            @Field("nik") String nik,
            @Field("nama") String nama,
            @Field("resistan") String resistan,
            @Field("alamat") String alamat,
            @Field("nama_kontak") String namaKontak,
            @Field("umur_kontak") String umurKontak,
            @Field("alamat_kontak") String alamatKontak,
            @Field("rtrw") String rtrw,
            @Field("kelurahan") String kelurahan,
            @Field("kecamatan") String kecamatan,
            @Field("kabupaten_kontak") String kabupatenKontak,
            @Field("provinsi") String provinsi,
            @Field("hasil_akhir") String hasilAkhir,
            @Field("tindak_lanjut") String tindakLanjut,
            @Field("tanggal_mulai") String tanggalMulai,
            @Field("hasil_pp_inh") String hasilPpInh,
            @Field("lokasi") String lokasi);
}
