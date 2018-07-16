package idev.gin.nias.activity;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.toolbox.JsonObjectRequest;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import idev.gin.nias.KasusClass;
import idev.gin.nias.R;
import idev.gin.nias.dao.POST_RIWAYAT;
import idev.gin.nias.dao.PoinDao;
import idev.gin.nias.dao.PostRiwayatDao;
import idev.gin.nias.data.remote.APIServiceRiwayat;
import idev.gin.nias.data.remote.ApiUtils;
import idev.gin.nias.utils.CONSTANT;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RiwayatTbActivity extends AppCompatActivity {

    private APIServiceRiwayat mApiServiceRiwayat;
    private static final String TAG = RiwayatTbActivity.class.getName();
    private TextView mResponseTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_tb);
        Bundle extras = getIntent().getExtras();
        String emailpass = extras.getString("email");
        String tokenpass = extras.getString("token");
        final TextInputEditText UnitPelayanan = (TextInputEditText)findViewById(R.id.unit_pelayanan);
        final TextInputEditText desak = (TextInputEditText) findViewById(R.id.kabkota);
        final TextView tanggalr = (TextView) findViewById(R.id.tanggalriwayattb);
        final TextInputEditText namaOrangTua = (TextInputEditText)findViewById(R.id.namaortuasuh);
        final TextInputEditText namaAnak = (TextInputEditText) findViewById(R.id.namaAnak);
        final TextInputEditText usiaAnak= (TextInputEditText) findViewById(R.id.usiaAnak);
        final TextInputEditText jumlahAnak = (TextInputEditText) findViewById(R.id.jumlahAnak);
        final TextInputEditText alamatDesa =(TextInputEditText) findViewById(R.id.alamatdesa);
        final Spinner kontakTb = (Spinner) findViewById(R.id.spkontak);
        final Spinner berat59 = (Spinner) findViewById(R.id.spberat59);
        final Spinner berat14 = (Spinner) findViewById(R.id.spberat14);
        final Spinner demam = (Spinner) findViewById(R.id.spdemam);
        final Spinner batuk = (Spinner) findViewById(R.id.spbatuk);
        final Spinner kelenjarlimfie = (Spinner) findViewById(R.id.sppembesarankelenjar);
        final Spinner pembengkakan = (Spinner) findViewById(R.id.sppembekakan);

        Button submitriwayat = (Button) findViewById(R.id.submitrwyt);
        mApiServiceRiwayat = ApiUtils.getAPIRiwayat();

        submitriwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namaKader = UnitPelayanan.getText().toString().trim();
                String desa = desak.getText().toString().trim();
                String tanggal = tanggalr.getText().toString().trim();
                String nama_orangtua = namaOrangTua.getText().toString().trim();
                String nama_anak = namaAnak.getText().toString().trim();
                String usia_anak = usiaAnak.getText().toString().trim();
                String jumlah_anak = jumlahAnak.getText().toString().trim();
                String alamat_desa = alamatDesa.getText().toString().trim();
                String kontak_tb = kontakTb.getSelectedItem().toString().trim();
                String Berat59 = berat59.getSelectedItem().toString().trim();
                String Berat14 = berat14.getSelectedItem().toString().trim();
                String Demam = demam.getSelectedItem().toString().trim();
                String Batuk = batuk.getSelectedItem().toString().trim();
                String Kelenjar = kelenjarlimfie.getSelectedItem().toString().trim();
                String Pembengkakan = pembengkakan.getSelectedItem().toString().trim();

                if (!TextUtils.isEmpty(namaKader) &&
                        !TextUtils.isEmpty(desa)&&
                        !TextUtils.isEmpty(tanggal) &&
                        !TextUtils.isEmpty(nama_orangtua) &&
                        !TextUtils.isEmpty(nama_anak) &&
                        !TextUtils.isEmpty(usia_anak) &&
                        !TextUtils.isEmpty(jumlah_anak) &&
                        !TextUtils.isEmpty(alamat_desa) &&
                        !TextUtils.isEmpty(kontak_tb) &&
                        !TextUtils.isEmpty(Berat59) &&
                        !TextUtils.isEmpty(Berat14) &&
                        !TextUtils.isEmpty(Demam) &&
                        !TextUtils.isEmpty(Batuk) &&
                        !TextUtils.isEmpty(Kelenjar) &&
                        !TextUtils.isEmpty(Pembengkakan))
                {
                    sendriwayat(namaKader,desa,tanggal,nama_orangtua,nama_anak,usia_anak,jumlah_anak,alamat_desa,kontak_tb,Berat59,Berat14,Demam,Batuk,Kelenjar,Pembengkakan);
                }
            }
            private void sendriwayat(String namaKader,String desa,String tanggal,String namaOrangtua,String namaAnak,String usiaAnak,String jumlahAnak,String alamatDesa,String kontakTb,String berat59,String berat4,String demam,String batuk ,String kelenjar,String bengkak) {
                AndroidNetworking.post(CONSTANT.BASE_URL + "penilaianriwayat")
                        .addHeaders("Authorization", "bearer " + tokenpass)
                        .addBodyParameter("nama_kader",namaKader)
                        .addBodyParameter("desa",desa)
                        .addBodyParameter("tanggal",tanggal)
                        .addBodyParameter("nama_orantua",namaOrangtua)
                        .addBodyParameter("nama_anak",namaAnak)
                        .addBodyParameter("usia_anak",usiaAnak)
                        .addBodyParameter("jumlah_anak",jumlahAnak)
                        .addBodyParameter("alamat_anak",alamatDesa)
                        .addBodyParameter("kontak_tb",kontakTb)
                        .addBodyParameter("berat_badan_59bulan",berat59)
                        .addBodyParameter("berat_badan_14tahun",berat4)
                        .addBodyParameter("demam",demam)
                        .addBodyParameter("batuk",batuk)
                        .addBodyParameter("pembesaran_kelenjar_limfie",kelenjar)
                        .addBodyParameter("pembesaran_tulang",bengkak)
                        .addBodyParameter("fk_register","32")
                        .addBodyParameter("lat","1 ")
                        .addBodyParameter("long"," 139 ")
                        .setTag("riwayat")
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsObject(PostRiwayatDao.class, new ParsedRequestListener() {
                            @Override
                            public void onResponse(Object response) {
                                Log.i("xxx" , response.toString());
                                Toast.makeText(getApplicationContext(), "Riwayat TB Berhasil diinput "+response.toString(), Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onError(ANError anError) {
                                Toast.makeText(getApplicationContext(), "Input Failed: " + anError.getErrorBody(), Toast.LENGTH_LONG).show();

                            }
                        });
//                mApiServiceRiwayat
//                        .savePost(unitPelayanan,kabupaten,tanggalRegis,nik,nama,resisten,alamat,namaKontak,umurKontak,spGender,alamatKontak,hasilAkhir,tindakLanjut,tanggalPpInh,hasilPpInh)
//                        .request().header("Authorization","bearer" + tokenpass)
//                        .enqueue(new Callback<POST_RIWAYAT>() {
//                    @Override
//                    public void onResponse(Call<POST_RIWAYAT> call, Response<POST_RIWAYAT> response) {
//
//                        if(response.isSuccessful()) {
//                            showResponse(response.body().toString());
//                            Log.i(TAG, "post submitted to API." + response.body().toString());
//                        }
//                    }
//
//                    public void showResponse(String response) {
//                        if(mResponseTv.getVisibility() == View.GONE) {
//                            mResponseTv.setVisibility(View.VISIBLE);
//                        }
//                        mResponseTv.setText(response);
//                    }
//
//                    @Override
//                    public void onFailure(Call<POST_RIWAYAT> call, Throwable t) {
//                        Log.e(TAG, "Unable to submit post to API.");
//                    }
//                });
            }
        });

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String tanggalriwayat = df.format(c);
        tanggalr.setText(tanggalriwayat);

        Spinner spkontak = (Spinner) findViewById(R.id.spkontak);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(RiwayatTbActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.kontak));
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spkontak.setAdapter((adapter1));

        Spinner spberat059 = (Spinner) findViewById(R.id.spberat59);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(RiwayatTbActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.berat3));
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spberat059.setAdapter((adapter2));

        Spinner spberat614 = (Spinner) findViewById(R.id.spberat14);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(RiwayatTbActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.berat6));
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spberat614.setAdapter((adapter3));

        Spinner spdemam = (Spinner) findViewById(R.id.spdemam);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(RiwayatTbActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.demam));
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spdemam.setAdapter((adapter4));

        Spinner spbatuk = (Spinner) findViewById(R.id.spbatuk);
        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(RiwayatTbActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.batuk));
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spbatuk.setAdapter((adapter5));

        Spinner spkelenjar = (Spinner) findViewById(R.id.sppembesarankelenjar);
        ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(RiwayatTbActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.pembesaran));
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spkelenjar.setAdapter((adapter6));

        Spinner sptulang = (Spinner) findViewById(R.id.sppembekakan);
        ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(RiwayatTbActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.tulang));
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sptulang.setAdapter((adapter7));
    }
}
