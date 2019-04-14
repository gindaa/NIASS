package idev.gin.nias.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import idev.gin.nias.R;
import idev.gin.nias.dao.AkunidDao;
import idev.gin.nias.utils.CONSTANT;

public class MenuKaderActivity extends AppCompatActivity {

    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_kader);
        Bundle extras = getIntent().getExtras();
        String emailpass = extras.getString("email");
        String tokenpass = extras.getString("token");
        final TextView namamenu = (TextView) findViewById(R.id.namakadermenu);
        final TextView rolemenu = (TextView) findViewById(R.id.rolekadermenu);
        sharedPref = getApplicationContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPref.edit();
        edit.putString("email", emailpass);
        edit.putString("token", tokenpass);
        edit.apply();

        Button btnprofil = (Button)findViewById(R.id.btnprofilkdr);
        Button btnkasustb = (Button)findViewById(R.id.btnkasuskdr);
        Button btnotifikasi = (Button)(findViewById(R.id.btnnotifkdr));
        Button btpoin = (Button)(findViewById(R.id.btnpoinkdr));
        Button btexit = (Button)(findViewById(R.id.btnexitkdr));

        AndroidNetworking.get(CONSTANT.BASE_URL + "akunid")
                .addHeaders("Authorization", "bearer " + tokenpass)
                .addHeaders("email", emailpass)
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(AkunidDao.class, new ParsedRequestListener<AkunidDao>() {
                    @Override
                    public void onResponse(AkunidDao response) {
                        namamenu.setText(response.getResult().get(0).getNama());
                        rolemenu.setText(response.getResult().get(0).getRole());
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(getApplicationContext(),  "Error: " + anError.getErrorBody(), Toast.LENGTH_LONG).show();
                    }
                });


        btnprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuKaderActivity.this,ProfileAcivity.class);
                intent.putExtra("email",emailpass);
                intent.putExtra("token",tokenpass);
                startActivity(intent);
            }
        });

        btnkasustb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuKaderActivity.this,KasusTbActivity.class);
                intent.putExtra("email",emailpass);
                intent.putExtra("token",tokenpass);
                startActivity(intent);
            }
        });
        btnotifikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuKaderActivity.this,NotifikasiKaderActivity.class);
                intent.putExtra("email",emailpass);
                intent.putExtra("token",tokenpass);
                startActivity(intent);
            }
        });
        btpoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuKaderActivity.this,PoinActivity.class);
                intent.putExtra("email",emailpass);
                intent.putExtra("token",tokenpass);
                startActivity(intent);
            }
        });

        btexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });


    }
}
