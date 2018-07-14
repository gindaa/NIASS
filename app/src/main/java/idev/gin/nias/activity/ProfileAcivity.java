package idev.gin.nias.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import idev.gin.nias.R;
import idev.gin.nias.dao.POST_AKUN;
import idev.gin.nias.data.remote.APIServiceSignUp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static idev.gin.nias.data.remote.RetrofitClient.retrofit;

public class ProfileAcivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Bundle extras = getIntent().getExtras();
        String emailpass = extras.getString("email");
        String tokenpass = extras.getString("token");

        final TextView namatv = (TextView) findViewById(R.id.namaprofile);
        final TextView emailtv = (TextView) findViewById(R.id.emailprofile);
        final TextView jktv = (TextView) findViewById(R.id.jkprofile);
        final TextView tltv = (TextView) findViewById(R.id.tgllhrprofile);
        final TextView tptlhrtv = (TextView) findViewById(R.id.tptlhrprofile);
        final TextView hptv = (TextView) findViewById(R.id.hpprofile);
        final TextView roletv = (TextView) findViewById(R.id.jobprofile);

        APIServiceSignUp profileAPI = retrofit.create(APIServiceSignUp.class);
        Call<POST_AKUN> call = profileAPI.getAkun(tokenpass,emailpass);
        call.enqueue(new Callback<POST_AKUN>() {
            @Override
            public void onResponse(Call<POST_AKUN> call, Response<POST_AKUN> response) {
                namatv.setText(response.body().getNama());
                emailtv.setText(response.body().getEmail());
                jktv.setText((response.body().getJenisKelamin()));
                tltv.setText(response.body().getTanggalLahir());
                tptlhrtv.setText(response.body().getTempatLahir());
                hptv.setText(response.body().getNoHp());
                roletv.setText(response.body().getRole());
            }

            @Override
            public void onFailure(Call<POST_AKUN> call, Throwable t) {
                Toast.makeText(ProfileAcivity.this,"Cek koneksi anda",Toast.LENGTH_LONG).show();

            }
        });







    }
}
