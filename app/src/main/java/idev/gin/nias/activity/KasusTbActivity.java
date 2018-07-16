package idev.gin.nias.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
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

    private Context context;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private List<KasusClass> tbList;
    private KasusTbAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kasus_tb);
        Bundle extras = getIntent().getExtras();
        String emailpass = extras.getString("email");
        String tokenpass = extras.getString("token");

        recyclerView = (RecyclerView) findViewById(R.id.reckasustb);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        tbList = new ArrayList<>();
        callkasustb(emailpass,tokenpass);

        adapter = new KasusTbAdapter(this,tbList);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if(gridLayoutManager.findLastVisibleItemPosition() == tbList.size()-1){
                    callkasustb(emailpass,tokenpass);
                }
            }
        });

}

    private void callkasustb(String emailpass,String tokenpass) {
//        AsyncTask<Integer,Void,Void> task = new AsyncTask<Integer, Void, Void>() {
//            @Override
//            protected Void doInBackground(Integer... integers) {

                try{
                AndroidNetworking.get(CONSTANT.BASE_URL + "faskes")
                        .addHeaders("Authorization", "bearer " + tokenpass)
                        .addHeaders("page","1")
//                        .addHeaders("email", emailpass)
                        .setTag("Faskes")
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsObject(FaskesDao.class, new ParsedRequestListener<FaskesDao>() {
                            @Override
                            public void onResponse(FaskesDao response) {

                                Log.i("isi",emailpass);
                                Log.i("xxx", "total :"+response.getTotal());
                                for (int i = 0; i < response.getTotal(); i++) {
                                    KasusClass isikasus = new KasusClass(
                                            response.getData().get(i).getNo_registrasi_faskes().toString(),
                                            response.getData().get(i).getNo_registrasi_tbkota().toString(),
                                            response.getData().get(i).getProvinsi().toString(),
                                            response.getData().get(i).getNo_registrasi_faskes().toString(),
                                            response.getData().get(i).getNo_registrasi_tbkota().toString(),
                                            response.getData().get(i).getNama_pasien().toString(),
                                            response.getData().get(i).getNik().toString(),
                                            response.getData().get(i).getJenis_kelamin().toString(),
                                            response.getData().get(i).getUmur().toString(),
                                            response.getData().get(i).getAlamat().toString(),
                                            response.getData().get(i).getPerujuk().toString(),
                                            response.getData().get(i).getTipe_diagnosis_tb().toString());
                                    tbList.add(isikasus);
                                    Log.i("xxx",response.getData().get(i).getNo_registrasi_faskes());
                                }

                            }

                            @Override
                            public void onError(ANError anError) {
                                Toast.makeText(getApplicationContext(),  "Error: " + anError.getErrorBody(), Toast.LENGTH_LONG).show();
                                adapter.notifyDataSetChanged();
                            }

                        });

            }catch (NullPointerException e) {
                    e.printStackTrace();
                }
//            return null;
        };
    };