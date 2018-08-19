package idev.gin.nias.activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import idev.gin.nias.KasusClass;
import idev.gin.nias.R;
import idev.gin.nias.adapter.NotifikasiNakesAdapter;
import idev.gin.nias.dao.FaskesDao;
import idev.gin.nias.utils.CONSTANT;

public class NotifikasiActivity extends AppCompatActivity {

    private SharedPreferences sharedPref;
    private RecyclerView recyclerView;
    private ArrayList<KasusClass> tbList;
    private NotifikasiNakesAdapter adapter;

    String emailpass;
    String tokenpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifikasi);
        Bundle extras = getIntent().getExtras();
        emailpass = extras.getString("email");
        tokenpass = extras.getString("token");


//        Button btidkasus = (Button)findViewById(R.id.btgetidkasus);
//        btidkasus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPrefs",MODE_PRIVATE);
//                Toast.makeText(getApplicationContext(), "Id kasus adalah di activity :" + pref.getString("idKasus", "Id tidak Ketemu"), Toast.LENGTH_LONG).show();
//            }
//        });

        recyclerView = (RecyclerView) findViewById(R.id.reckasustb);
        recyclerView.setLayoutManager(new  LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        tbList = new ArrayList<>();
        adapter = new NotifikasiNakesAdapter(this,tbList);
        recyclerView.setAdapter(adapter);
        callkasustb();



    }


    private void callkasustb() {
        AndroidNetworking.get(CONSTANT.BASE_URL + "faskes")
                .addHeaders("Authorization", "bearer " + tokenpass)
                .addHeaders("page","1")
                .setTag("Faskes")
                .setPriority(Priority.MEDIUM)
                .build()
//                        .getAsJSONObject(new JSONObjectRequestListener() {
//                            @Override
//                            public void onResponse(JSONObject response) {
//                                Log.i("xxx", response.toString());
//                            }
                .getAsObject(FaskesDao.class, new ParsedRequestListener<FaskesDao>() {
                    @Override
                    public void onResponse(FaskesDao response) {
                        if (response.getResult().getPage().equals("0")) {
                            return;
                        }
                        for (int i = 0; i < response.getResult().getData().size(); i++) {
                            KasusClass isikasus = new KasusClass(
                                    response.getResult().getData().get(i).getId(),
                                    response.getResult().getData().get(i).getNo_registrasi_faskes(),
                                    response.getResult().getData().get(i).getNo_registrasi_tbkota(),
                                    response.getResult().getData().get(i).getProvinsi(),
                                    response.getResult().getData().get(i).getNo_registrasi_faskes(),
                                    response.getResult().getData().get(i).getNo_registrasi_tbkota(),
                                    response.getResult().getData().get(i).getNama_pasien(),
                                    response.getResult().getData().get(i).getNik(),
                                    response.getResult().getData().get(i).getJenis_kelamin(),
                                    response.getResult().getData().get(i).getUmur(),
                                    response.getResult().getData().get(i).getAlamat(),
                                    response.getResult().getData().get(i).getPerujuk(),
                                    response.getResult().getData().get(i).getTipe_diagnosis_tb());
                            tbList.add(isikasus);
                        }
                        adapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(getApplicationContext(),  "Error: " + anError.getErrorBody(), Toast.LENGTH_LONG).show();
                    }

                });
    }
}