package idev.gin.nias.data.remote;

import idev.gin.nias.dao.POST_RIWAYAT;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;

public interface APIServiceRiwayat {
    @retrofit2.http.POST("/riwayat")
    @FormUrlEncoded
    Call<POST_RIWAYAT> savePost(@Field("nama_kader") String namaKader,
                                @Field("desa") String desa,
                                @Field("tanggal") String tanggal,
                                @Field("nama_orangtua") String namaOrangTua,
                                @Field("nama_anak") String namaAnak,
                                @Field("usia_anak") String usiaAnak,
                                @Field("jumlah_anak") String jumlahAnak,
                                @Field("alamat_desa") String alamatDesa,
                                @Field("kontak_tb") String kontakTb,
                                @Field("berat_badan_59bulan") String beratBadan59Bulan,
                                @Field("berat_badan_14tahun") String beratBadan14Tahun,
                                @Field("demam") String demam,
                                @Field("batuk") String batuk,
                                @Field("pembesaran_kelenjar_linfie") String pembesaranKelenjarLinfie,
                                @Field("pembesaran_tulang") String pembesaranTulang
    );

}
