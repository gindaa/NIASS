package idev.gin.nias.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class MenuNakesActivity extends AppCompatActivity {

    SharedPreferences sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_nakes);
        Bundle extras = getIntent().getExtras();
        String emailpassnakes = extras.getString("email");
        String tokenpassnakes = extras.getString("token");
        final TextView namamenu = (TextView) findViewById(R.id.namanakesmenu);
        final TextView rolemenu = (TextView) findViewById(R.id.rolenakesmenu);
        sharedPref = getApplicationContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPref.edit();
        edit.putString("email", emailpassnakes);
        edit.putString("token", tokenpassnakes);
        edit.apply();

        Button btnprofil =  (Button)(findViewById(R.id.btnprofilnks));
        Button btnotifikasi =  (Button)(findViewById(R.id.btnnotifnks));
        Button skoringtb =  (Button)(findViewById(R.id.btnskrnks));
        Button btpoin =  (Button)(findViewById(R.id.btnpoinnks));

        AndroidNetworking.get(CONSTANT.BASE_URL + "akunid")
                .addHeaders("Authorization", "bearer " + tokenpassnakes)
                .addHeaders("email", emailpassnakes)
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
                Intent intent = new Intent(MenuNakesActivity.this,ProfileAcivity.class);
                intent.putExtra("email",emailpassnakes);
                intent.putExtra("token",tokenpassnakes);
                startActivity(intent);
            }
        });
        btnotifikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuNakesActivity.this,NotifikasiNakesActivity.class);
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
