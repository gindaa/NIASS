package idev.gin.nias.data.remote;

import idev.gin.nias.data.model.Login;
import idev.gin.nias.data.model.LoginTokenCall;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIServiceLogin {

    @POST("login")
    Call<LoginTokenCall> login(@Body Login login);
}