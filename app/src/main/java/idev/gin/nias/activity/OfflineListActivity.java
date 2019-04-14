package idev.gin.nias.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import idev.gin.nias.R;

public class OfflineListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_list);

        Button btnaddoff = (Button)findViewById(R.id.btnaddoff);
        btnaddoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OfflineListActivity.this,OfflineForm.class);
//                Toast.makeText(getApplicationContext(),"Anda masuk ke Offline Mode", Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });
    }
}
