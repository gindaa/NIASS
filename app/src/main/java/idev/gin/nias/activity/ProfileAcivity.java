package idev.gin.nias.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import idev.gin.nias.R;
import idev.gin.nias.dao.AkunidDao;
import idev.gin.nias.dao.SignupDao;
import idev.gin.nias.data.remote.APIServiceSignUp;
import idev.gin.nias.utils.CONSTANT;
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

            AndroidNetworking.get(CONSTANT.BASE_URL + "akunid")
                    .addHeaders("Authorization", "bearer " + tokenpass)
                    .addHeaders("email", emailpass)
                    .setTag("test")
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsObject(AkunidDao.class, new ParsedRequestListener<AkunidDao>() {
                        @Override
                        public void onResponse(AkunidDao response) {
                            namatv.setText(response.getResult().get(0).getNama());
                            emailtv.setText(response.getResult().get(0).getEmail());
                            jktv.setText(response.getResult().get(0).getJenis_kelamin());
                            tltv.setText(response.getResult().get(0).getTanggal_lahir());
                            tptlhrtv.setText(response.getResult().get(0).getTempat_lahir());
                            hptv.setText(response.getResult().get(0).getNo_hp());
                            roletv.setText(response.getResult().get(0).getRole());

                        }

                        @Override
                        public void onError(ANError anError) {
                            Toast.makeText(getApplicationContext(),  "Error: " + anError.getErrorBody(), Toast.LENGTH_LONG).show();
                        }
                    });
    }

    }
