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
import idev.gin.nias.adapter.KasusTbAdapter;
import idev.gin.nias.adapter.NotifikasiNakesAdapter;
import idev.gin.nias.dao.FaskesDao;
import idev.gin.nias.utils.CONSTANT;
import idev.gin.nias.utils.EndlessRecyclerOnScrollListener;


public class KasusTbActivity extends AppCompatActivity {

    private SharedPreferences sharedPref;
    private RecyclerView recyclerView;
    private int mLoadedItems = 10;
    private ArrayList<KasusClass> tbList;
    private KasusTbAdapter adapter;
    private EndlessRecyclerOnScrollListener scrollListener;

    String emailpass;
    String tokenpass;
    int pages;
    String selectedid;
    Button btidkasus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kasus_tb);
        Bundle extras = getIntent().getExtras();
        emailpass = extras.getString("email");
        tokenpass = extras.getString("token");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        tbList = new ArrayList<>();
        adapter = new KasusTbAdapter(this,tbList);
        RecyclerView rvitem = (RecyclerView) findViewById(R.id.reckasustb);
        rvitem.setAdapter(adapter);
        rvitem.setLayoutManager(new  LinearLayoutManager(this));
        callkasustb("1");


        String pagenumber = Integer.toString(pages);
        rvitem.addOnScrollListener(scrollData(pagenumber));
//        scrollListener = new EndlessRecyclerOnScrollListener(linearLayoutManager){
//            @Override
//            public void onLoadMore() {
//                callkasustb(2);
//            }
//        };


//        callkasustb(1);
}

    private EndlessRecyclerOnScrollListener scrollData(String page){
        return new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                callkasustb(page);

            }
        };
    }

    private void callkasustb(String page) {
//        String textpage = Integer.toString(page);
                AndroidNetworking.get(CONSTANT.BASE_URL + "faskes")
                        .addHeaders("Authorization", "bearer " + tokenpass)
                        .addHeaders("page",page)
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