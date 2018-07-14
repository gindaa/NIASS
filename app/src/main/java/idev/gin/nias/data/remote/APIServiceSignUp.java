package idev.gin.nias.data.remote;

import idev.gin.nias.dao.POST_AKUN;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface APIServiceSignUp {

    @GET("/akunid")
    Call<POST_AKUN> getAkun(@Header("Authorization") String authToken,
                            @Header("email") String Email);



    @POST("/register")
    @FormUrlEncoded
    Call<POST_AKUN> savePost(@Field("email") String email,
                             @Field("nama") String nama,
                             @Field("jenis_kelamin") String jenisKelamin,
                             @Field("tempat_lahir") String tempatLahir,
                             @Field("tanggal_lahir") String tanggaLahir,
                             @Field("no_hp") String noHp,
                             @Field("password") String password,
                             @Field("role") String role);



}
