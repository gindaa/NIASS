package idev.gin.nias.activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.support.v7.widget.SearchView;
import android.widget.Spinner;
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
    private SearchView searchView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kasus_tb);
        mProgress = new ProgressDialog(this);
        mProgress.setTitle("Loading..");
        mProgress.setMessage("Mohon Tunggu...");
        mProgress.setCancelable(false);
        mProgress.setIndeterminate(true);
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

        final Spinner kecktb = (Spinner) findViewById(R.id.filterbykecamatan);
        final Spinner kelktb = (Spinner) findViewById(R.id.filterbykelurahan);
        Button filterkel = (Button) findViewById(R.id.btnfilter);
        Button carikel = (Button) findViewById(R.id.searchfilter);
        final TextInputEditText searchfield = (TextInputEditText) findViewById(R.id.searchfield);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        tbList = new ArrayList<>();
        adapter = new KasusTbAdapter(this,tbList);
        RecyclerView rvitem = (RecyclerView) findViewById(R.id.reckasustb);
        rvitem.setAdapter(adapter);
        rvitem.setLayoutManager(new  LinearLayoutManager(this));
        callkasustb("1");
        rvitem.addOnScrollListener(scrollData(pages));

        ArrayAdapter<String> adapterfana = new ArrayAdapter<String>(KasusTbActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.fanayama));
        adapterfana.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> adaptermin = new ArrayAdapter<String>(KasusTbActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.miniamolo));
        adaptermin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        kecktb.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String kecamatans = kecktb.getSelectedItem().toString().toLowerCase();
                if (kecamatans.equals("fanayama")){
                    kelktb.setAdapter(adapterfana);
                }
                else if(kecamatans.equals("maniamolo")){
                    kelktb.setAdapter(adaptermin);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        filterkel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tbList.clear();
                String filterlurahs = kelktb.getSelectedItem().toString().toLowerCase();
                filterkelurahan(filterlurahs);

            }
        });
        carikel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tbList.clear();
                String carilurah = searchfield.getText().toString().toLowerCase();
                searchkelurahan(carilurah);
            }
        });


//        kelktb.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String filterlurahs = kelktb.getSelectedItem().toString().toLowerCase();
//                filterkelurahan(filterlurahs);
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });


        Spinner spkecamatankasus = (Spinner) findViewById(R.id.filterbykecamatan);
        ArrayAdapter<String> adapterkeckasus = new ArrayAdapter<String>(KasusTbActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.kecamatan));
        adapterkeckasus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spkecamatankasus.setAdapter((adapterkeckasus));

        Spinner spkelurahankasus = (Spinner) findViewById(R.id.filterbykelurahan);
        ArrayAdapter<String> adapterkelkasus = new ArrayAdapter<String>(KasusTbActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.pilihkec));
        adapterkelkasus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spkelurahankasus.setAdapter((adapterkelkasus));
}

    private EndlessRecyclerOnScrollListener scrollData(int pagesi){
        return new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
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
                                        response.getResult().getData().get(i).getKecamatan(),
                                        response.getResult().getData().get(i).getKelurahan(),
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
    private void searchkelurahan (String kelurahan) {
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
                        int lastpage =  response.getResult().getLastPage();
                        Log.i("halaman terakhir",Integer.toString(lastpage));
                        for (int i=1; i<lastpage+1;i++){
                            AndroidNetworking.get(CONSTANT.BASE_URL + "faskes")
                                    .addHeaders("Authorization", "bearer " + tokenpass)
                                    .addHeaders("page", Integer.toString(i))
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
                                                        response.getResult().getData().get(i).getKecamatan(),
                                                        response.getResult().getData().get(i).getKelurahan(),
                                                        response.getResult().getData().get(i).getNama_pasien(),
                                                        response.getResult().getData().get(i).getNik(),
                                                        response.getResult().getData().get(i).getJenis_kelamin(),
                                                        response.getResult().getData().get(i).getUmur(),
                                                        response.getResult().getData().get(i).getAlamat(),
                                                        response.getResult().getData().get(i).getPerujuk(),
                                                        response.getResult().getData().get(i).getTipe_diagnosis_tb());
                                                Log.i("Isi Kelurahan",(response.getResult().getData().get(i).getKelurahan()).toLowerCase());
                                                Log.i("Isi Kelurahan before",(kelurahan.toLowerCase()));
                                                if (response.getResult().getData().get(i).getKelurahan().toLowerCase().equals(kelurahan.toLowerCase())) {
                                                    Log.i("isi kelurahan",(kelurahan.toLowerCase()));
                                                    Log.i("isi kelurahan",(kelurahan));
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

                                    });}
                    }

                    @Override
                    public void onError(ANError anError) {

                    }

                });

        mProgress.dismiss();

//        } else Toast.makeText(getApplicationContext(), "Last Page", Toast.LENGTH_LONG).show();
    }

    private void filterkelurahan (String kelurahan) {
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
                                int lastpage =  response.getResult().getLastPage();
                                Log.i("halaman terakhir",Integer.toString(lastpage));
                                for (int i=1; i<lastpage+1;i++){
                                AndroidNetworking.get(CONSTANT.BASE_URL + "faskes")
                                        .addHeaders("Authorization", "bearer " + tokenpass)
                                        .addHeaders("page", Integer.toString(i))
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
                                                            response.getResult().getData().get(i).getKecamatan(),
                                                            response.getResult().getData().get(i).getKelurahan(),
                                                            response.getResult().getData().get(i).getNama_pasien(),
                                                            response.getResult().getData().get(i).getNik(),
                                                            response.getResult().getData().get(i).getJenis_kelamin(),
                                                            response.getResult().getData().get(i).getUmur(),
                                                            response.getResult().getData().get(i).getAlamat(),
                                                            response.getResult().getData().get(i).getPerujuk(),
                                                            response.getResult().getData().get(i).getTipe_diagnosis_tb());
                                                    Log.i("Isi Kelurahan",(response.getResult().getData().get(i).getKelurahan()).toLowerCase());
                                                    Log.i("Isi Kelurahan before",(kelurahan.toLowerCase()));
                                                    if (response.getResult().getData().get(i).getKelurahan().toLowerCase().equals(kelurahan.toLowerCase())) {
                                                        Log.i("isi kelurahan",(kelurahan.toLowerCase()));
                                                        Log.i("isi kelurahan",(kelurahan));
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

                                        });}
                            }

                            @Override
                            public void onError(ANError anError) {

                            }

                        });

                        mProgress.dismiss();

//        } else Toast.makeText(getApplicationContext(), "Last Page", Toast.LENGTH_LONG).show();
    }

}