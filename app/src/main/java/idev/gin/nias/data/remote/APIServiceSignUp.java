package idev.gin.nias.data.remote;

import idev.gin.nias.data.model.POST_AKUN;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

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
