package idev.gin.nias.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import idev.gin.nias.R;

public class MenuNakesActivity extends AppCompatActivity {

    SharedPreferences sharedPref;
    private Button btnprofil;
    private Button btnotifikasi;
    private Button skoringtb;
    private Button btnkasustb;
    private Button btpoin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_nakes);
        Bundle extras = getIntent().getExtras();
        String emailpassnakes = extras.getString("email");
        String tokenpassnakes = extras.getString("token");
        sharedPref = getApplicationContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPref.edit();
        edit.putString("email", emailpassnakes);
        edit.putString("token", tokenpassnakes);
        edit.apply();

        Button btnprofil =  (Button)(findViewById(R.id.btnprofilnks));
        Button btnotifikasi =  (Button)(findViewById(R.id.btnnotifnks));
        Button skoringtb =  (Button)(findViewById(R.id.btnskrnks));
        Button btnkasustb =  (Button)(findViewById(R.id.btnkasusnks));
        Button btpoin =  (Button)(findViewById(R.id.btnpoinnks));


        btnprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuNakesActivity.this,ProfileAcivity.class);
                intent.putExtra("email",emailpassnakes);
                intent.putExtra("token",tokenpassnakes);
                startActivity(intent);
            }
        });
        btnotifikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuNakesActivity.this,NotifikasiActivity.class);
                intent.putExtra("email",emailpassnakes);
                intent.putExtra("token",tokenpassnakes);
                startActivity(intent);
            }
        });
        skoringtb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuNakesActivity.this,ScoringActivity.class);
                intent.putExtra("email",emailpassnakes);
                intent.putExtra("token",tokenpassnakes);
                Log.i("xxx",emailpassnakes);
                Log.i("token",tokenpassnakes);
                startActivity(intent);
            }
        });
        btnkasustb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuNakesActivity.this,KasusTbActivity.class);
                intent.putExtra("email",emailpassnakes);
                intent.putExtra("token",tokenpassnakes);
                startActivity(intent);
            }
        });
        btpoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuNakesActivity.this,PoinActivity.class);
                intent.putExtra("email",emailpassnakes);
                intent.putExtra("token",tokenpassnakes);
                startActivity(intent);
            }
        });
    }
}
