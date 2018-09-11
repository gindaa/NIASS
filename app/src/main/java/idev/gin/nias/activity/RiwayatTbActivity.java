package idev.gin.nias.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import idev.gin.nias.KasusClass;
import idev.gin.nias.R;
import idev.gin.nias.dao.AddPoinDao;
import idev.gin.nias.dao.AkunidDao;
import idev.gin.nias.dao.FaskesIdDao;
import idev.gin.nias.dao.POST_RIWAYAT;
import idev.gin.nias.dao.PoinDao;
import idev.gin.nias.dao.PostRiwayatDao;
import idev.gin.nias.data.remote.APIServiceRiwayat;
import idev.gin.nias.data.remote.ApiUtils;
import idev.gin.nias.utils.CONSTANT;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RiwayatTbActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private APIServiceRiwayat mApiServiceRiwayat;
    private int PLACE_PICKER_REQUEST = 1;
    private static final String TAG = RiwayatTbActivity.class.getName();
    private TextView mResponseTv;
    String tokenpass;
    String emailpass;
    String idkasus;
    String latitude;
    String longitude;
    private GoogleApiClient mGoogleApiClient;
    private Button btmap;
    private TextView tvMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_tb);
        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();

        Button mBtMap = (Button)findViewById(R.id.selectmap);
        tvMap = findViewById(R.id.lokasitv);
        mBtMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(RiwayatTbActivity.this), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });

        Bundle extras = getIntent().getExtras();
        emailpass = extras.getString("email");
        tokenpass = extras.getString("token");
        idkasus = extras.getString("idKasus");

        final TextInputEditText UnitPelayanan = (TextInputEditText)findViewById(R.id.unit_pelayanan);
        final TextInputEditText desak = (TextInputEditText) findViewById(R.id.kabkota);
        final TextView tanggalr = (TextView) findViewById(R.id.tanggalriwayattb);
        final TextInputEditText namaOrangTua = (TextInputEditText)findViewById(R.id.namaortuasuh);
        final TextInputEditText namaAnak = (TextInputEditText) findViewById(R.id.namaAnak);
        final Spinner usiaAnak= (Spinner) findViewById(R.id.spusia);
        final Spinner jumlahAnak = (Spinner) findViewById(R.id.spjumlahanak);
        final TextInputEditText alamatDesa =(TextInputEditText) findViewById(R.id.alamatdesa);
        final Spinner kontakTb = (Spinner) findViewById(R.id.spkontak);
        final Spinner berat59 = (Spinner) findViewById(R.id.spberat59);
        final Spinner berat14 = (Spinner) findViewById(R.id.spberat14);
        final Spinner demam = (Spinner) findViewById(R.id.spdemam);
        final Spinner batuk = (Spinner) findViewById(R.id.spbatuk);
        final Spinner kelenjarlimfie = (Spinner) findViewById(R.id.sppembesarankelenjar);
        final Spinner pembengkakan = (Spinner) findViewById(R.id.sppembekakan);
        final Spinner provinsi = (Spinner) findViewById(R.id.spprovinsiriwayat);
        final Spinner kabupaten = (Spinner) findViewById(R.id.spkabupatenriwayat);
        final Spinner kelurahan = (Spinner) findViewById(R.id.spkelurahanriwayat);
        final Spinner kecamatan = (Spinner) findViewById(R.id.spkecamatanriwayat);

        ArrayAdapter<String> adapterfana = new ArrayAdapter<String>(RiwayatTbActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.fanayama));
        adapterfana.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> adaptermin = new ArrayAdapter<String>(RiwayatTbActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.miniamolo));
        adaptermin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        kecamatan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String kecamatans = kecamatan.getSelectedItem().toString().toLowerCase();
                if (kecamatans.equals("fanayama")){
                    kelurahan.setAdapter(adapterfana);
                }
                else if(kecamatans.equals("maniamolo")){
                    kelurahan.setAdapter(adaptermin);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button submitriwayat = (Button) findViewById(R.id.submitrwyt);
        mApiServiceRiwayat = ApiUtils.getAPIRiwayat();
        AndroidNetworking.get(CONSTANT.BASE_URL + "faskesid")
                .addHeaders("Authorization", "bearer " + tokenpass)
                .addHeaders("id",idkasus)
                .setTag("Faskesid")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(FaskesIdDao.class, new ParsedRequestListener<FaskesIdDao>() {
                    @Override
                    public void onResponse(FaskesIdDao response) {
                        desak.setText(response.getResult().get(0 ).getKabupaten());
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(getApplicationContext(),  "Error: " + anError.getErrorBody(), Toast.LENGTH_LONG).show();
                    }

                });

        AndroidNetworking.get(CONSTANT.BASE_URL + "akunid")
                .addHeaders("Authorization", "bearer " + tokenpass)
                .addHeaders("email", emailpass)
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(AkunidDao.class, new ParsedRequestListener<AkunidDao>() {
                    @Override
                    public void onResponse(AkunidDao response) {
                        UnitPelayanan.setText(response.getResult().get(0).getNama());
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(getApplicationContext(),  "Error: " + anError.getErrorBody(), Toast.LENGTH_LONG).show();
                    }
                });

        submitriwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namaKader = UnitPelayanan.getText().toString().trim();
                String desa = desak.getText().toString().trim();
                String tanggal = tanggalr.getText().toString().trim();
                String nama_orangtua = namaOrangTua.getText().toString().trim();
                String nama_anak = namaAnak.getText().toString().trim();
                String usia_anak = usiaAnak.getSelectedItem().toString().trim();
                String jumlah_anak = jumlahAnak.getSelectedItem().toString().trim();
                String alamat_desa = alamatDesa.getText().toString().trim();
                String provinsisp = provinsi.getSelectedItem().toString().trim();
                String kabupatensp = kabupaten.getSelectedItem().toString().trim();
                String kelurahansp = kelurahan.getSelectedItem().toString().trim();
                String kecamatansp = kecamatan.getSelectedItem().toString().trim();
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
                    sendriwayat(namaKader,desa,tanggal,nama_orangtua,nama_anak,usia_anak,jumlah_anak,alamat_desa,provinsisp,kabupatensp,kelurahansp,kecamatansp,kontak_tb,Berat59,Berat14,Demam,Batuk,Kelenjar,Pembengkakan);
                }
            }
            private void sendriwayat(String namaKader,String desa,String tanggal,String namaOrangtua,String namaAnak,String usiaAnak,String jumlahAnak,String alamatDesa,String provinsi,String kabupaten,String kelurahan, String kecamatan,String kontakTb,String berat59,String berat4,String demam,String batuk ,String kelenjar,String bengkak) {

                AndroidNetworking.post(CONSTANT.BASE_URL + "penilaianriwayat")
                        .addHeaders("Authorization", "bearer " + tokenpass)
                        .addBodyParameter("nama_kader",namaKader)
                        .addBodyParameter("desa",desa)
                        .addBodyParameter("tanggal",tanggal)
                        .addBodyParameter("nama_orantua",namaOrangtua)
                        .addBodyParameter("nama_anak",namaAnak)
                        .addBodyParameter("usia_anak",usiaAnak)
                        .addBodyParameter("jumlah_anak",jumlahAnak)
                        .addBodyParameter("alamat_desa",alamatDesa)
                        .addBodyParameter("kecamatan",kecamatan)
                        .addBodyParameter("keluarahan",kelurahan)
                        .addBodyParameter("kabupaten",kabupaten)
                        .addBodyParameter("provinsi",provinsi)
                        .addBodyParameter("kontak_tb",kontakTb)
                        .addBodyParameter("berat_badan_59bulan",berat59)
                        .addBodyParameter("berat_badan_14tahun",berat4)
                        .addBodyParameter("demam",demam)
                        .addBodyParameter("batuk",batuk)
                        .addBodyParameter("pembesaran_kelenjar_limfe",kelenjar)
                        .addBodyParameter("pembesaran_tulang",bengkak)
                        .addBodyParameter("lat",latitude)
                        .addBodyParameter("long",longitude)
                        .addBodyParameter("fk_faskes",idkasus)
                        .setTag("riwayat")
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsObject(PostRiwayatDao.class, new ParsedRequestListener<PostRiwayatDao>() {
                            @Override
                            public void onResponse(PostRiwayatDao response) {
                                Toast.makeText(getApplicationContext(), "Riwayat TB Berhasil diinput"+response.toString(), Toast.LENGTH_LONG).show();
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
                                                    Intent intent = new Intent(RiwayatTbActivity.this,MenuKaderActivity.class);
                                                    intent.putExtra("token" , tokenpass);
                                                    intent.putExtra("email", emailpass);
                                                    finish();
                                                    startActivity(intent);


                                                }

                                                @Override
                                                public void onError(ANError anError) {
                                                    Toast.makeText(getApplicationContext(), "Poin gagal Ditambah" + anError.getErrorBody(), Toast.LENGTH_LONG).show();

                                                }
                                            });
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

        Spinner spusia = (Spinner) findViewById(R.id.spusia);
        ArrayAdapter<String> adapterusia = new ArrayAdapter<String>(RiwayatTbActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.usia));
        adapterusia.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spusia.setAdapter((adapterusia));

        Spinner spanaklain = (Spinner) findViewById(R.id.spjumlahanak);
        ArrayAdapter<String> adapteranaklain = new ArrayAdapter<String>(RiwayatTbActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.anaklains));
        adapteranaklain.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spanaklain.setAdapter((adapteranaklain));

        Spinner spprov = (Spinner) findViewById(R.id.spprovinsiriwayat);
        ArrayAdapter<String> adaptereprov = new ArrayAdapter<String>(RiwayatTbActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.provinsi));
        adaptereprov.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spprov.setAdapter((adaptereprov));

        Spinner spkab = (Spinner) findViewById(R.id.spkabupatenriwayat);
        ArrayAdapter<String> adapterkab = new ArrayAdapter<String>(RiwayatTbActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.kab));
        adapterkab.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spkab.setAdapter((adapterkab));

        Spinner spkecamatan = (Spinner) findViewById(R.id.spkecamatanriwayat);
        ArrayAdapter<String> adapterkec = new ArrayAdapter<String>(RiwayatTbActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.kecamatan));
        adapterkec.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spkecamatan.setAdapter((adapterkec));
    }
    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Snackbar.make(btmap, connectionResult.getErrorMessage() + "", Snackbar.LENGTH_LONG).show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                StringBuilder stBuilder = new StringBuilder();
                String placename = String.format("%s", place.getName());
                latitude = String.valueOf(place.getLatLng().latitude);
                longitude = String.valueOf(place.getLatLng().longitude);
                String address = String.format("%s", place.getAddress());
                stBuilder.append("Latitude: ");
                stBuilder.append(latitude);
                stBuilder.append("\n");
                stBuilder.append("Logitude: ");
                stBuilder.append(longitude);
                stBuilder.append("\n");
                stBuilder.append("Address: ");
                stBuilder.append(address);
                tvMap.setText(stBuilder.toString());
}
        }
    }

    public void setKeterangan(){

    }

}
