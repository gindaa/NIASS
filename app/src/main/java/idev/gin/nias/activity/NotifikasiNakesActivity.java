package idev.gin.nias.activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.ArrayList;

import idev.gin.nias.KasusClass;
import idev.gin.nias.R;
import idev.gin.nias.RiwayatClass;
import idev.gin.nias.adapter.NotifikasiNakesAdapter;
import idev.gin.nias.dao.FaskesDao;
import idev.gin.nias.dao.RiwayatDao;
import idev.gin.nias.utils.CONSTANT;
import idev.gin.nias.utils.EndlessRecyclerOnScrollListener;

public class NotifikasiNakesActivity extends AppCompatActivity {

    private SharedPreferences sharedPref;
    private RecyclerView recyclerView;
    private ArrayList<RiwayatClass> tbList;
    private NotifikasiNakesAdapter adapter;
    private ProgressDialog mProgress;
    int lastpages;
    public int pages;
    String emailpass;
    String tokenpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifikasi_nakes);
        mProgress = new ProgressDialog(this);
        mProgress.setTitle("Loading..");
        mProgress.setMessage("Mohon Tunggu...");
        mProgress.setCancelable(false);
        mProgress.setIndeterminate(true);
        Bundle extras = getIntent().getExtras();
        emailpass = extras.getString("email");
        tokenpass = extras.getString("token");
        mProgress.show();
        AndroidNetworking.get(CONSTANT.BASE_URL + "penilaianriwayat")
                .addHeaders("Authorization", "bearer " + tokenpass)
                .addHeaders("page", "1")
                .setTag("Faskes")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(RiwayatDao.class, new ParsedRequestListener<RiwayatDao>() {
                    @Override
                    public void onResponse(RiwayatDao response) {
                        lastpages = response.getResult().getLastPage();
                        mProgress.dismiss();
                        Log.i("halakhirlastt",Integer.toString(lastpages));

                    }

                    @Override
                    public void onError(ANError anError) {
                        mProgress.dismiss();
                        Toast.makeText(getApplicationContext(), "Error: " + anError.getErrorBody(), Toast.LENGTH_LONG).show();
                    }
                });

        final Spinner kecktb = (Spinner) findViewById(R.id.filterbykecamatannotif);
        final Spinner kelktb = (Spinner) findViewById(R.id.filterbykelurahannotif);
        Button filterkel = (Button) findViewById(R.id.btnfilternotif);
        Spinner spkecamatankasus = (Spinner) findViewById(R.id.filterbykecamatannotif);
        ArrayAdapter<String> adapterkeckasus = new ArrayAdapter<String>(NotifikasiNakesActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.kecamatan));
        adapterkeckasus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spkecamatankasus.setAdapter((adapterkeckasus));

        Spinner spkelurahankasus = (Spinner) findViewById(R.id.filterbykelurahannotif);
        ArrayAdapter<String> adapterkelkasus = new ArrayAdapter<String>(NotifikasiNakesActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.pilihkec));
        adapterkelkasus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spkelurahankasus.setAdapter((adapterkelkasus));
        ArrayAdapter<String> adapterfana = new ArrayAdapter<String>(NotifikasiNakesActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.fanayama));
        adapterfana.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> adaptermin = new ArrayAdapter<String>(NotifikasiNakesActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.miniamolo));
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

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        tbList = new ArrayList<>();
        adapter = new NotifikasiNakesAdapter(this,tbList);
        RecyclerView rvsitem = (RecyclerView) findViewById(R.id.recnotifikasinakes);
        rvsitem.setAdapter(adapter);
        rvsitem.setLayoutManager(new  LinearLayoutManager(this));
        callkasustb("1");
        rvsitem.addOnScrollListener(scrollData(pages));



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
        AndroidNetworking.get(CONSTANT.BASE_URL + "penilaianriwayat")
                .addHeaders("Authorization", "bearer " + tokenpass)
                .addHeaders("page",pagestr)
                .setTag("Faskes")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(RiwayatDao.class, new ParsedRequestListener<RiwayatDao>() {
                    @Override
                    public void onResponse(RiwayatDao response) {
                        if (response.getResult().getPage().equals("0")) {
                            return;
                        }
                        for (int i = 0; i < response.getResult().getData().size(); i++) {
                            if (response.getResult().getData().get(i).getStatus() == null) {
                                RiwayatClass isikasus = new RiwayatClass(
                                        response.getResult().getData().get(i).getId(),
                                        response.getResult().getData().get(i).getNama_kader(),
                                        response.getResult().getData().get(i).getDesa(),
                                        response.getResult().getData().get(i).getTanggal(),
                                        response.getResult().getData().get(i).getNama_orantua(),
                                        response.getResult().getData().get(i).getNama_anak(),
                                        response.getResult().getData().get(i).getUsia_anak(),
                                        response.getResult().getData().get(i).getJumlah_anak(),
                                        response.getResult().getData().get(i).getAlamat_desa(),
                                        response.getResult().getData().get(i).getKeluarahan(),
                                        response.getResult().getData().get(i).getKecamatan(),
                                        response.getResult().getData().get(i).getKabupaten(),
                                        response.getResult().getData().get(i).getProvinsi(),
                                        response.getResult().getData().get(i).getFk_faskes());
                                tbList.add(isikasus);
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(getApplicationContext(),  "Error: " + anError.getErrorBody(), Toast.LENGTH_LONG).show();
                    }

                });
    }
    private void filterkelurahan (String kelurahan) {
        mProgress.show();
        AndroidNetworking.get(CONSTANT.BASE_URL + "penilaianriwayat")
                .addHeaders("Authorization", "bearer " + tokenpass)
                .addHeaders("page", "1")
                .setTag("Faskes")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(RiwayatDao.class, new ParsedRequestListener<RiwayatDao>() {
                    @Override
                    public void onResponse(RiwayatDao response) {
                        int lastpage =  response.getResult().getLastPage();
                        Log.i("halaman terakhir",Integer.toString(lastpage));
                        for (int i=1; i<lastpage+1;i++){
                            AndroidNetworking.get(CONSTANT.BASE_URL + "penilaianriwayat")
                                    .addHeaders("Authorization", "bearer " + tokenpass)
                                    .addHeaders("page", Integer.toString(i))
                                    .setTag("Faskes")
                                    .setPriority(Priority.MEDIUM)
                                    .build()
                                    .getAsObject(RiwayatDao.class, new ParsedRequestListener<RiwayatDao>() {
                                        @Override
                                        public void onResponse(RiwayatDao response) {
                                            for (int i = 0; i < response.getResult().getData().size(); i++) {
                                                RiwayatClass isikasus = new RiwayatClass(
                                                        response.getResult().getData().get(i).getId(),
                                                        response.getResult().getData().get(i).getNama_kader(),
                                                        response.getResult().getData().get(i).getDesa(),
                                                        response.getResult().getData().get(i).getTanggal(),
                                                        response.getResult().getData().get(i).getNama_orantua(),
                                                        response.getResult().getData().get(i).getNama_anak(),
                                                        response.getResult().getData().get(i).getUsia_anak(),
                                                        response.getResult().getData().get(i).getJumlah_anak(),
                                                        response.getResult().getData().get(i).getAlamat_desa(),
                                                        response.getResult().getData().get(i).getKeluarahan(),
                                                        response.getResult().getData().get(i).getKecamatan(),
                                                        response.getResult().getData().get(i).getKabupaten(),
                                                        response.getResult().getData().get(i).getProvinsi(),
                                                        response.getResult().getData().get(i).getFk_faskes());
                                                Log.i("Isi Kelurahan",(response.getResult().getData().get(i).getKeluarahan()).toLowerCase());
                                                Log.i("Isi Kelurahan before",(kelurahan.toLowerCase()));
                                                if (response.getResult().getData().get(i).getKeluarahan().toLowerCase().equals(kelurahan.toLowerCase())) {
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