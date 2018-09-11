package idev.gin.nias.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import idev.gin.nias.R;

public class MenuKaderActivity extends AppCompatActivity {

    private Button btnprofil;
    private Button btnkasustb;
    private Button btinvestigasi;
    private Button btnotifikasi;
    private Button btgps;
    private Button btpoin;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_kader);
        Bundle extras = getIntent().getExtras();
        String emailpass = extras.getString("email");
        String tokenpass = extras.getString("token");
        sharedPref = getApplicationContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPref.edit();
        edit.putString("email", emailpass);
        edit.putString("token", tokenpass);
        edit.apply();

        Button btnprofil = (Button)findViewById(R.id.btnprofilkdr);
        Button btnkasustb = (Button)findViewById(R.id.btnkasuskdr);
        Button btinvestigasi = (Button)findViewById(R.id.btninveskdr);
        Button btnotifikasi = (Button)(findViewById(R.id.btnnotifkdr));
        Button btgps = (Button)(findViewById(R.id.btngpskdr));
        Button btpoin = (Button)(findViewById(R.id.btnpoinkdr));


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
        btinvestigasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuKaderActivity.this,RiwayatTbActivity.class);
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
        btgps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuKaderActivity.this,MapsActivity.class);
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


    }
}
