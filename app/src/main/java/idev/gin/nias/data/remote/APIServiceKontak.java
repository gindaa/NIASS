package idev.gin.nias.data.remote;

import idev.gin.nias.dao.POST_KONTAK;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;

public interface APIServiceKontak {

    @retrofit2.http.POST("/kontak")
    @FormUrlEncoded
    Call<POST_KONTAK> savePost(@Field("tanggal") String tanggal,
                               @Field("unitpelayanan") String unitPelayanan,
                               @Field("kabupaten") String kabupaten,
                               @Field("nik") String nik,
                               @Field("nama") String nama,
                               @Field("resistan") String resistan,
                               @Field("alamat") String alamat,
                               @Field("nama_kontak") String namaKontak,
                               @Field("umur_kontak_l") String umurKontakL,
                               @Field("umur_kontak_p") String umurKontakP,
                               @Field("alamat_kontak") String alamatKontak,
                               //@Field("rtrw") String rtrw,
                               //@Field("kelurahan") String kelurahan,
                               //@Field("kecamatan") String kecamatan,
                               //@Field("kabupaten_kontak") String kabupatenKontak,
                               //@Field("provinsi") String provinsi,
                               @Field("hasil_akhir") String hasilAkhir,
                               @Field("tindak_lanjut") String tindakLanjut,
                               @Field("tanggal_mulai") String tanggalMulai,
                               @Field("hasil_pp_inh") String hasilPpInh,
                               @Field("lokasi") String lokasi);
}
