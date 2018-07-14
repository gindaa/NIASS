package idev.gin.nias.data.remote;

import idev.gin.nias.dao.POST_SKORING;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;

public interface APIServiceSkoring {
    @retrofit2.http.POST("/skoring")
    @FormUrlEncoded
    Call<POST_SKORING> savePost(@Field("kontak_tb") String kontakTb,
                                @Field("uji_tuberkulin") String ujiTuberkulin,
                                @Field("berat_badan") String beratBadan,
                                @Field("demam") String demam,
                                @Field("batuk_kronik") String batukKronik,
                                @Field("pembesaran_kelenjar") String pembesaranKelenjar,
                                @Field("pembengkakan_tulang") String pembengkakanTulang,
                                @Field("foto_toraks") String fotoToraks,
                                @Field("fk_register") String fkRegister);
}
