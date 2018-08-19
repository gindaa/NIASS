package idev.gin.nias.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import idev.gin.nias.R;
import idev.gin.nias.dao.AddPoinDao;
import idev.gin.nias.utils.CONSTANT;

public class ScoringActivity extends AppCompatActivity {

    private Button hitungsc;
    private Button kirimsc;
    private int hasilscore;
    private TextView scoring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoring);
        Bundle extras = getIntent().getExtras();
        String emailpass = extras.getString("email");
        String tokenpass = extras.getString("token");
        String idkasus = extras.getString("idKasus");

        final Spinner kontak = (Spinner) findViewById(R.id.spkontaksc);
        final Spinner tuberkulin = (Spinner) findViewById(R.id.spujituberkulinsc);
        final Spinner berat = (Spinner) findViewById(R.id.spberat14sc);
        final Spinner demam = (Spinner) findViewById(R.id.spdemamsc);
        final Spinner batuk = (Spinner) findViewById(R.id.spbatuksc);
        final Spinner kelenjar = (Spinner) findViewById(R.id.sppembesarankelenjarsc);
        final Spinner tulang = (Spinner) findViewById(R.id.sppembekakansc);
        final Spinner toraks = (Spinner) findViewById(R.id.sptorakssc);


        hitungsc = (Button) findViewById(R.id.bthitung);
        kirimsc = (Button) findViewById(R.id.btkirimscore);
        hitungsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hasilscore = 0;
                if(kontak.getSelectedItem().toString().toLowerCase().contains("ada , bta(-)")){
                    hasilscore += 2;
                }
                if(kontak.getSelectedItem().toString().toLowerCase().contains("ada , bta(+)")){
                    hasilscore += 3;
                }
                if(tuberkulin.getSelectedItem().toString().toLowerCase().contains("positif")){
                    hasilscore += 3;
                }
                if(berat.getSelectedItem().toString().toLowerCase().contains("bb/tb<90% atau bb/u<80%")) {
                    hasilscore += 1;
                }
                if(berat.getSelectedItem().toString().toLowerCase().contains("klinis gizi buruk")) {
                    hasilscore += 2;
                }
                if(demam.getSelectedItem().toString().toLowerCase().contains(">= 2 minggu")) {
                    hasilscore += 1;
                }
                if(batuk.getSelectedItem().toString().toLowerCase().contains(">= 2 minggu")){
                    hasilscore += 1;
                }
                if(kelenjar.getSelectedItem().toString().toLowerCase().contains("lebih dari 1 cm") ){
                    hasilscore += 1;
                }
                if(tulang.getSelectedItem().toString().toLowerCase().contains("ada pembengkakan") ){
                    hasilscore += 1;
                }
                if(toraks.getSelectedItem().toString().toLowerCase().contains("gambaran sugestif")) {
                    hasilscore += 1;
                }
                scoring = (TextView) findViewById(R.id.hasilscore);
                String scoretext = Integer.toString(hasilscore);
                scoring.setText(scoretext);

            }
        });
        kirimsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AndroidNetworking.post(CONSTANT.BASE_URL + "adduserspoint")
                        .addHeaders("Authorization", "bearer " + tokenpass)
                        .addHeaders("email", emailpass)
                        .setTag("addpoint")
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsObject(AddPoinDao.class, new ParsedRequestListener() {
                            @Override
                            public void onResponse(Object response) {
                                Toast.makeText(getApplicationContext(), "Poin Berhasil Ditambah "+response.toString(), Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onError(ANError anError) {
                                Toast.makeText(getApplicationContext(), "Poin gagal Ditambah" + anError.getErrorBody(), Toast.LENGTH_LONG).show();

                            }
                        });
            }
        });

        Spinner spkontak = (Spinner) findViewById(R.id.spkontaksc);
        ArrayAdapter<String> adapter1sc = new ArrayAdapter<String>(ScoringActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.kontak));
        adapter1sc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spkontak.setAdapter((adapter1sc));

        Spinner sptuberkulin = (Spinner) findViewById(R.id.spujituberkulinsc);
        ArrayAdapter<String> adapter8sc = new ArrayAdapter<String>(ScoringActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.tuberkulin));
        adapter8sc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sptuberkulin.setAdapter((adapter8sc));

        Spinner spberat = (Spinner) findViewById(R.id.spberat14sc);
        ArrayAdapter<String> adapter3sc = new ArrayAdapter<String>(ScoringActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.bbgizi));
        adapter3sc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spberat.setAdapter((adapter3sc));

        Spinner spdemam = (Spinner) findViewById(R.id.spdemamsc);
        ArrayAdapter<String> adapter4sc = new ArrayAdapter<String>(ScoringActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.minggu));
        adapter4sc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spdemam.setAdapter((adapter4sc));

        Spinner spbatuk = (Spinner) findViewById(R.id.spbatuksc);
        ArrayAdapter<String> adapter5sc = new ArrayAdapter<String>(ScoringActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.minggu));
        adapter5sc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spbatuk.setAdapter((adapter5sc));

        Spinner spkelenjar = (Spinner) findViewById(R.id.sppembesarankelenjarsc);
        ArrayAdapter<String> adapter6sc = new ArrayAdapter<String>(ScoringActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.pembengkakansc));
        adapter6sc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spkelenjar.setAdapter((adapter6sc));

        Spinner sptulang = (Spinner) findViewById(R.id.sppembekakansc);
        ArrayAdapter<String> adapter7sc = new ArrayAdapter<String>(ScoringActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.tulangsc));
        adapter7sc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sptulang.setAdapter((adapter7sc));

        Spinner sptoraks = (Spinner) findViewById(R.id.sptorakssc);
        ArrayAdapter<String> adapter9sc = new ArrayAdapter<String>(ScoringActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.toraks));
        adapter9sc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sptoraks.setAdapter((adapter9sc));
    }



}
