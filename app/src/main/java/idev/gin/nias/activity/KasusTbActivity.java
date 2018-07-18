package idev.gin.nias.activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import idev.gin.nias.adapter.KasusTbAdapter;
import idev.gin.nias.dao.FaskesDao;
import idev.gin.nias.utils.CONSTANT;

public class KasusTbActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<KasusClass> tbList;
    private KasusTbAdapter adapter;

    String emailpass;
    String tokenpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kasus_tb);
        Bundle extras = getIntent().getExtras();
        emailpass = extras.getString("email");
        tokenpass = extras.getString("token");

        recyclerView = (RecyclerView) findViewById(R.id.reckasustb);
        recyclerView.setLayoutManager(new  LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        tbList = new ArrayList<>();
        adapter = new KasusTbAdapter(this,tbList);
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
                                Log.i("xxx", "total :"+response.getResult().getTotal());
                                Log.i("isi",response.getResult().getData().get(1).getNo_registrasi_faskes());
                                Log.i("long",String.valueOf(response.getResult().getData().size()));
                                for (int i = 0; i < response.getResult().getData().size(); i++) {
                                    KasusClass isikasus = new KasusClass(
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

                            }

                            @Override
                            public void onError(ANError anError) {
                                Toast.makeText(getApplicationContext(),  "Error: " + anError.getErrorBody(), Toast.LENGTH_LONG).show();
                                adapter.notifyDataSetChanged();
                            }

                        });
        }
    }