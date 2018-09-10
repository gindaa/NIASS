package idev.gin.nias.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.ArrayList;

import idev.gin.nias.KasusDetailClass;
import idev.gin.nias.R;
import idev.gin.nias.adapter.NotifikasiNakesAdapter;
import idev.gin.nias.dao.FaskesDao;
import idev.gin.nias.utils.CONSTANT;
import idev.gin.nias.utils.EndlessRecyclerOnScrollListener;

public class NotifikasiKaderActivity extends AppCompatActivity {

    private SharedPreferences sharedPref;
    private RecyclerView recyclerView;
    private ArrayList<KasusDetailClass> tbList;
    private NotifikasiNakesAdapter adapter;
    int lastpages;
    public int pages;
    String emailpass;
    String tokenpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifikasi);
        Bundle extras = getIntent().getExtras();
        emailpass = extras.getString("email");
        tokenpass = extras.getString("token");
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

                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(getApplicationContext(), "Error: " + anError.getErrorBody(), Toast.LENGTH_LONG).show();
                    }
                });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        tbList = new ArrayList<>();
        adapter = new NotifikasiNakesAdapter(this,tbList);
        RecyclerView rvsitem = (RecyclerView) findViewById(R.id.recnotifikasi);
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
        AndroidNetworking.get(CONSTANT.BASE_URL + "faskes")
                .addHeaders("Authorization", "bearer " + tokenpass)
                .addHeaders("page",pagestr)
                .setTag("Faskes")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(FaskesDao.class, new ParsedRequestListener<FaskesDao>() {
                    @Override
                    public void onResponse(FaskesDao response) {
                        if (response.getResult().getPage().equals("0")) {
                            return;
                        }
                        for (int i = 0; i < response.getResult().getData().size(); i++) {
                            KasusDetailClass isikasus = new KasusDetailClass(
                                    response.getResult().getData().get(i).getId(),
                                    response.getResult().getData().get(i).getNo_registrasi_faskes(),
                                    response.getResult().getData().get(i).getAlamat(),
                                    response.getResult().getData().get(i).getRtrw(),
                                    response.getResult().getData().get(i).getKelurahan(),
                                    response.getResult().getData().get(i).getKecamatan(),
                                    response.getResult().getData().get(i).getKabupaten(),
                                    response.getResult().getData().get(i).getProvinsi(),
                                    response.getResult().getData().get(i).getNo_registrasi_tbkota(),
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