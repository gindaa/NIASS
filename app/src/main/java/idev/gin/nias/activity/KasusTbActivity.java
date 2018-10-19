package idev.gin.nias.activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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
import idev.gin.nias.adapter.NotifikasiNakesAdapter;
import idev.gin.nias.dao.FaskesDao;
import idev.gin.nias.utils.CONSTANT;
import idev.gin.nias.utils.EndlessRecyclerOnScrollListener;


public class KasusTbActivity extends AppCompatActivity {

    private SharedPreferences sharedPref;
    private RecyclerView recyclerView;
    private ArrayList<KasusClass> tbList;
    private KasusTbAdapter adapter;
    private EndlessRecyclerOnScrollListener scrollListener;
    public int pages;
    private ProgressDialog mProgress;
    public int lastpages;
    String emailpass;
    String tokenpass;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProgress = new ProgressDialog(this);
        mProgress.setTitle("Loading..");
        mProgress.setMessage("Mohon Tunggu...");
        mProgress.setCancelable(false);
        mProgress.setIndeterminate(true);
        setContentView(R.layout.activity_kasus_tb);
        Bundle extras = getIntent().getExtras();
        emailpass = extras.getString("email");
        tokenpass = extras.getString("token");
        mProgress.show();
        AndroidNetworking.get(CONSTANT.BASE_URL + "faskes")
                .addHeaders("Authorization", "bearer " + tokenpass)
                .addHeaders("page", "1")
                .setTag("Faskes")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(FaskesDao.class, new ParsedRequestListener<FaskesDao>() {
                    @Override
                    public void onResponse(FaskesDao response) {
                        lastpages = response.getResult().getLastPage();
                        Log.i("halakhirlastt",Integer.toString(lastpages));
                        mProgress.dismiss();

                    }

                    @Override
                    public void onError(ANError anError) {
                        mProgress.dismiss();
                        Toast.makeText(getApplicationContext(), "Error: " + anError.getErrorBody(), Toast.LENGTH_LONG).show();
                    }
                });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        tbList = new ArrayList<>();
        adapter = new KasusTbAdapter(this,tbList);
        RecyclerView rvitem = (RecyclerView) findViewById(R.id.reckasustb);
        rvitem.setAdapter(adapter);
        rvitem.setLayoutManager(new  LinearLayoutManager(this));
        callkasustb("1");
        rvitem.addOnScrollListener(scrollData(pages));
}

    private EndlessRecyclerOnScrollListener scrollData(int pagesi){
        return new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                Log.i("halaman",Integer.toString(pagesi));
                Log.i("halakhir",Integer.toString(lastpages));
                    String pagesstr = Integer.toString(pagesi);
                    callkasustb(pagesstr);
            }
        };
    }

    private void callkasustb(String page) {
        pages = pages + 1;
        String pagestr = Integer.toString(pages);
        AndroidNetworking.get(CONSTANT.BASE_URL + "faskes")
                    .addHeaders("Authorization", "bearer " + tokenpass)
                    .addHeaders("page", pagestr)
                    .setTag("Faskes")
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsObject(FaskesDao.class, new ParsedRequestListener<FaskesDao>() {
                        @Override
                        public void onResponse(FaskesDao response) {
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
                                if (response.getResult().getData().get(i).getStatus() == null){
                                    tbList.add(isikasus);
                                }
                            }
                            lastpages = response.getResult().getLastPage();
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onError(ANError anError) {
                            Toast.makeText(getApplicationContext(), "Error: " + anError.getErrorBody(), Toast.LENGTH_LONG).show();
                        }

                    });
//        } else Toast.makeText(getApplicationContext(), "Last Page", Toast.LENGTH_LONG).show();
    }
}