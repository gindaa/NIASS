package idev.gin.nias.activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import idev.gin.nias.KasusClass;
import idev.gin.nias.R;
import idev.gin.nias.dao.AddPoinDao;
import idev.gin.nias.dao.AkunidDao;
import idev.gin.nias.dao.FaskesDao;
import idev.gin.nias.dao.FaskesIdDao;
import idev.gin.nias.dao.POST_KONTAK;
import idev.gin.nias.dao.PostPelacakanDao;
import idev.gin.nias.dao.PostRiwayatDao;
import idev.gin.nias.data.remote.APIServiceKontak;
import idev.gin.nias.data.remote.ApiUtils;
import idev.gin.nias.utils.CONSTANT;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Form16Activity extends AppCompatActivity {
    private static final String TAG = RiwayatTbActivity.class.getName();
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private APIServiceKontak mAPIServiceKontak;
    private TextView mResponseTv;
    private String emailpassnakes;
    private String tokenpassnakes;
    private String idKasus;
    private String fkFaskes;
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_16);
        mProgress = new ProgressDialog(this);
        mProgress.setTitle("Loading..");
        mProgress.setMessage("Mohon Tunggu...");
        mProgress.setCancelable(false);
        mProgress.setIndeterminate(true);
        Bundle extras = getIntent().getExtras();
        emailpassnakes = extras.getString("email");
        tokenpassnakes = extras.getString("token");
        idKasus = extras.getString("idKasus");
        final TextInputEditText unitPelayananEt = (TextInputEditText) findViewById(R.id.unit_pelayanan);
        final TextInputEditText kabupatenEt = (TextInputEditText) findViewById(R.id.kabkotar);
        final TextView tanggalEt = (TextView) findViewById(R.id.tanggalregisf);
        final TextInputEditText nikEt = (TextInputEditText) findViewById(R.id.nikKontakr);
        final TextInputEditText namaEt = (TextInputEditText) findViewById(R.id.namakk);
        final TextInputEditText resistanEt= (TextInputEditText) findViewById(R.id.sensitifResistan);
        final Spinner provk = (Spinner) findViewById(R.id.spprovinsikontak);
        final Spinner kabk = (Spinner) findViewById(R.id.spkabupatenkontak);
        final Spinner keck = (Spinner) findViewById(R.id.spkecamatankontak);
        final Spinner kelk = (Spinner) findViewById(R.id.spkelurahankontak);
        final TextInputEditText rtrwet = (TextInputEditText) findViewById(R.id.rtrwkontak);
        final TextInputEditText alamatEt = (TextInputEditText) findViewById(R.id.alamatxx);
        final TextInputEditText namaKontakEt = (TextInputEditText) findViewById(R.id.namaKontakx);
        final Spinner spgender = (Spinner) findViewById(R.id.spgender2);
        final Spinner spumurl = (Spinner) findViewById(R.id.spumurlform16);
        final Spinner spumurp =  (Spinner) findViewById(R.id.spumurpform16);
        final TextInputEditText alamatKontakEt = (TextInputEditText) findViewById(R.id.alamatKontakx);
        final Spinner hasilAkhirsp = (Spinner) findViewById(R.id.sphasilakhir);
        final Spinner tindaklanjutsp = (Spinner) findViewById(R.id.sptindaklanjut);
        final TextView tanggalPpInh = (TextView) findViewById(R.id.tanggalppinh);
        final Spinner hasilppinhsp = (Spinner) findViewById(R.id.spppinh);

        ArrayAdapter<String> adapterfana = new ArrayAdapter<String>(Form16Activity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.fanayama));
        adapterfana.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> adaptermin = new ArrayAdapter<String>(Form16Activity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.miniamolo));
        adaptermin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        keck.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String kecamatans = keck.getSelectedItem().toString().toLowerCase();
                if (kecamatans.equals("fanayama")){
                    kelk.setAdapter(adapterfana);
                }
                else if(kecamatans.equals("maniamolo")){
                    kelk.setAdapter(adaptermin);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button submitfaskes = (Button) findViewById(R.id.submitfaskes);
        mAPIServiceKontak = ApiUtils.getAPIKontak();
        mProgress.show();
//        AndroidNetworking.get(CONSTANT.BASE_URL + "faskesid")
//                .addHeaders("Authorization", "bearer " + tokenpassnakes)
//                .addHeaders("id",idKasus)
//                .setTag("Faskesid")
//                .setPriority(Priority.MEDIUM)
//                .build()
//                .getAsObject(FaskesIdDao.class, new ParsedRequestListener<FaskesIdDao>() {
//                    @Override
//                    public void onResponse(FaskesIdDao response) {
//                        kabupatenEt.setText(response.getResult().get(0).getKabupaten());
//                    }
//
//                    @Override
//                    public void onError(ANError anError) {
//                        Toast.makeText(getApplicationContext(),  "Error: " + anError.getErrorBody(), Toast.LENGTH_LONG).show();
//                    }
//
//                });
        AndroidNetworking.get(CONSTANT.BASE_URL + "akunid")
                .addHeaders("Authorization", "bearer " + tokenpassnakes)
                .addHeaders("email", emailpassnakes)
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(AkunidDao.class, new ParsedRequestListener<AkunidDao>() {
                    @Override
                    public void onResponse(AkunidDao response) {
                        unitPelayananEt.setText(response.getResult().get(0).getNama());
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(getApplicationContext(),  "Error: " + anError.getErrorBody(), Toast.LENGTH_LONG).show();
                    }
                });
        mProgress.dismiss();


        submitfaskes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String unitPelayanan = unitPelayananEt.getText().toString().trim();
                String kabupaten = kabupatenEt.getText().toString().trim();
                String tanggalRegis = tanggalEt.getText().toString().trim();
                String nik = nikEt.getText().toString().trim();
                String nama = namaEt.getText().toString().trim();
                String resistan = resistanEt.getText().toString().trim();
                String provinsi = provk.getSelectedItem().toString().trim();
                String kabupatena = kabk.getSelectedItem().toString().trim();
                String kecamatan = keck.getSelectedItem().toString().trim();
                String kelurahan = kelk.getSelectedItem().toString().trim();
                String rtrwk = rtrwet.getText().toString().trim();
                String alamat = alamatEt.getText().toString().trim();
                String namaKontak = namaKontakEt.getText().toString().trim();
                String umurKontakl = spumurl.getSelectedItem().toString().trim();
                String umurKontakp = spumurp.getSelectedItem().toString().trim();
                String spGender = spgender.getSelectedItem().toString().trim();
                String alamatKontak = alamatKontakEt.getText().toString().trim();
                String hasilAkhir = hasilAkhirsp.getSelectedItem().toString().trim();
                String tindakLanjut = tindaklanjutsp.getSelectedItem().toString().trim();
                String tanggalppinh = tanggalPpInh.getText().toString().trim();
                String hasilPPinh = hasilppinhsp.getSelectedItem().toString().trim();

                if(!TextUtils.isEmpty(nama) &&
                        !TextUtils.isEmpty(unitPelayanan)&&
                        !TextUtils.isEmpty(kabupaten) &&
                        !TextUtils.isEmpty(tanggalRegis) &&
                        !TextUtils.isEmpty(nik) &&
                        !TextUtils.isEmpty(nama) &&
                        !TextUtils.isEmpty(resistan) &&
                        !TextUtils.isEmpty(provinsi) &&
                        !TextUtils.isEmpty(kabupatena) &&
                        !TextUtils.isEmpty(kelurahan) &&
                        !TextUtils.isEmpty(kecamatan) &&
                        !TextUtils.isEmpty(alamat) &&
                        !TextUtils.isEmpty(rtrwk) &&
                        !TextUtils.isEmpty(namaKontak) &&
                        !TextUtils.isEmpty(umurKontakp) &&
                        !TextUtils.isEmpty(umurKontakl) &&
                        !TextUtils.isEmpty(spGender) &&
                        !TextUtils.isEmpty(alamatKontak) &&
                        !TextUtils.isEmpty(hasilAkhir) &&
                        !TextUtils.isEmpty(tindakLanjut) &&
                        !TextUtils.isEmpty(tanggalppinh) &&
                        !TextUtils.isEmpty(hasilPPinh))
                {
                        sendPost(unitPelayanan,kabupaten,tanggalRegis,nik,nama,resistan,provinsi,kabupatena,kelurahan,kecamatan,rtrwk,alamat,namaKontak,umurKontakp,umurKontakl,spGender,alamat,hasilAkhir,tindakLanjut,tanggalppinh,hasilAkhir);
                }
            }
            private void sendPost(String unitPelayanan,String kabupaten,String tanggalRegis,String nik,String nama,String resisten,String provinsi,String kabupatenkontak, String kelurahan,String kecamatan,String rtrw,String alamat,String namaKontak,String umurKontakp,String umurKontakl,String alamatKontak,String hasilAkhir,String tindakLanjut,String tanggalPpInh,String hasilPpInh,String lokasi) {
                AndroidNetworking.post(CONSTANT.BASE_URL + "kontak")
                        .addHeaders("Authorization", "bearer " + tokenpassnakes)
                        .addBodyParameter("tanggal",tanggalRegis)
                        .addBodyParameter("unitpelayanan",unitPelayanan)
                        .addBodyParameter("kabupaten",kabupaten)
                        .addBodyParameter("nik",nik)
                        .addBodyParameter("nama",nama)
                        .addBodyParameter("resisten",resisten)
                        .addBodyParameter("rtrw",rtrw)
                        .addBodyParameter("kelurahan",kelurahan)
                        .addBodyParameter("kecamatan",kecamatan)
                        .addBodyParameter("kabupatenkontak",kabupatenkontak)
                        .addBodyParameter("provinsi_alamat",provinsi)
                        .addBodyParameter("alamat",alamat)
                        .addBodyParameter("nama_kontak",namaKontak)
                        .addBodyParameter("umurkontak_l",umurKontakl)
                        .addBodyParameter("umurKontak_p",umurKontakp)
                        .addBodyParameter("alamat_kontak",alamatKontak)
                        .addBodyParameter("hasil_akhir",hasilAkhir)
                        .addBodyParameter("tindak_lanjut",tindakLanjut)
                        .addBodyParameter("tanggalmulai",tanggalPpInh)
                        .addBodyParameter("hasil_pp_inh",hasilPpInh)
                        .addBodyParameter("fk_faskes",idKasus)
                        .setTag("pelacakan")
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsObject(PostPelacakanDao.class, new ParsedRequestListener<PostPelacakanDao>() {
                            @Override
                            public void onResponse(PostPelacakanDao response) {
                                Toast.makeText(getApplicationContext(), "Menuju Skoring "+response.toString(), Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Form16Activity.this,ScoringActivity.class);
                                intent.putExtra("email",emailpassnakes);
                                intent.putExtra("token",tokenpassnakes);
                                intent.putExtra("idKasus",idKasus);
                                startActivity(intent);
                            }

                            @Override
                            public void onError(ANError anError) {
                                Toast.makeText(getApplicationContext(), "Input Failed: " + anError.getErrorBody(), Toast.LENGTH_LONG).show();

                            }
                        });
            }
        });



        Spinner spGendera = (Spinner) findViewById(R.id.spgender2);
        ArrayAdapter<String> adapterid = new ArrayAdapter<String>(Form16Activity.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.gender));
        adapterid.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGendera.setAdapter(adapterid);

        Spinner spumurpe = (Spinner) findViewById(R.id.spumurpform16);
        ArrayAdapter<String> adapterumurp = new ArrayAdapter<String>(Form16Activity.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.usia));
        adapterumurp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spumurp.setAdapter(adapterumurp);

        Spinner spumurla = (Spinner) findViewById(R.id.spumurlform16);
        ArrayAdapter<String> adapterumurl = new ArrayAdapter<String>(Form16Activity.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.usia));
        adapterumurl.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spumurl.setAdapter(adapterumurl);

        Spinner spAkhir = (Spinner) findViewById(R.id.sphasilakhir);
        ArrayAdapter<String> adapterha = new ArrayAdapter<String>(Form16Activity.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.hasilakhirkontak));
        adapterha.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spAkhir.setAdapter(adapterha);

        Spinner sprtl = (Spinner) findViewById(R.id.sptindaklanjut);
        ArrayAdapter<String> adaptertl = new ArrayAdapter<String>(Form16Activity.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.tindaklanjutkontak));
        adaptertl.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sprtl.setAdapter(adaptertl);

        Spinner spPpinh = (Spinner) findViewById(R.id.spppinh);
        ArrayAdapter<String> adapterpp = new ArrayAdapter<String>(Form16Activity.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.hasilppinh));
        adapterpp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPpinh.setAdapter(adapterpp);

        Spinner spprovk = (Spinner) findViewById(R.id.spprovinsikontak);
        ArrayAdapter<String> adaptereprovk = new ArrayAdapter<String>(Form16Activity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.provinsi));
        adaptereprovk.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spprovk.setAdapter((adaptereprovk));

        Spinner spkabk = (Spinner) findViewById(R.id.spkabupatenkontak);
        ArrayAdapter<String> adapterkabk = new ArrayAdapter<String>(Form16Activity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.kab));
        adapterkabk.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spkabk.setAdapter((adapterkabk));

        Spinner spkecamatank = (Spinner) findViewById(R.id.spkecamatankontak);
        ArrayAdapter<String> adapterkeck = new ArrayAdapter<String>(Form16Activity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.kecamatan));
        adapterkeck.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spkecamatank.setAdapter((adapterkeck));

        Spinner spkelurahank = (Spinner) findViewById(R.id.spkelurahankontak);
        ArrayAdapter<String> adapterkelk = new ArrayAdapter<String>(Form16Activity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.pilihkec));
        adapterkelk.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spkelurahank.setAdapter((adapterkelk));


        mDisplayDate = (TextView) findViewById(R.id.tanggalppinh);
        mDisplayDate.setPaintFlags(mDisplayDate.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Form16Activity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year , int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: date "+ day + "/" + month + "/" + year);
                String date = day +"/"+ month+ "/" + year;
                tanggalPpInh.setText(date);
            }
        };
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String tanggalriwayat = df.format(c);
        tanggalEt.setText(tanggalriwayat);


    }


}
