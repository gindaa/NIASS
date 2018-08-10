package idev.gin.nias.activity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
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

import idev.gin.nias.R;
import idev.gin.nias.dao.AddPoinDao;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_16);
        Bundle extras = getIntent().getExtras();
        emailpassnakes = extras.getString("email");
        tokenpassnakes = extras.getString("token");
        final TextInputEditText unitPelayananEt = (TextInputEditText) findViewById(R.id.unit_pelayanan);
        final TextInputEditText kabupatenEt = (TextInputEditText) findViewById(R.id.kabkotar);
        final TextView tanggalEt = (TextView) findViewById(R.id.tanggalregisf);
        final TextInputEditText nikEt = (TextInputEditText) findViewById(R.id.nikKontakr);
        final TextInputEditText namaEt = (TextInputEditText) findViewById(R.id.namakk);
        final TextInputEditText resistanEt= (TextInputEditText) findViewById(R.id.sensitifResistan);
        final TextInputEditText alamatEt = (TextInputEditText) findViewById(R.id.alamatxx);
        final TextInputEditText namaKontakEt = (TextInputEditText) findViewById(R.id.namaKontakx);
        final TextInputEditText umurKontakEt = (TextInputEditText) findViewById(R.id.umurx);
        final Spinner spgender = (Spinner) findViewById(R.id.spgender2);
        final TextInputEditText alamatKontakEt = (TextInputEditText) findViewById(R.id.alamatKontakx);
        final Spinner hasilAkhirsp = (Spinner) findViewById(R.id.sphasilakhir);
        final Spinner tindaklanjutsp = (Spinner) findViewById(R.id.sptindaklanjut);
        final TextView tanggalPpInh = (TextView) findViewById(R.id.tanggalppinh);
        final Spinner hasilppinhsp = (Spinner) findViewById(R.id.spppinh);

        Button submitfaskes = (Button) findViewById(R.id.submitfaskes);
        mAPIServiceKontak = ApiUtils.getAPIKontak();

        submitfaskes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String unitPelayanan = unitPelayananEt.getText().toString().trim();
                String kabupaten = kabupatenEt.getText().toString().trim();
                String tanggalRegis = tanggalEt.getText().toString().trim();
                String nik = nikEt.getText().toString().trim();
                String nama = namaEt.getText().toString().trim();
                String resistan = resistanEt.getText().toString().trim();
                String alamat = alamatEt.getText().toString().trim();
                String namaKontak = namaKontakEt.getText().toString().trim();
                String umurKontak = umurKontakEt.getText().toString().trim();
                String spGender = spgender.getSelectedItem().toString().trim();
                String alamatKontak = alamatKontakEt.getText().toString().trim();
                String hasilAkhir = hasilAkhirsp.getSelectedItem().toString().trim();
                String tindakLanjut = tindaklanjutsp.getSelectedItem().toString().trim();
                String tanggalppinh = tanggalPpInh.getText().toString().trim();
                String hasilPPinh = hasilppinhsp.getSelectedItem().toString().trim();
                String lokasi = "maps".toString().trim();

                if(!TextUtils.isEmpty(nama) &&
                        !TextUtils.isEmpty(unitPelayanan)&&
                        !TextUtils.isEmpty(kabupaten) &&
                        !TextUtils.isEmpty(tanggalRegis) &&
                        !TextUtils.isEmpty(nik) &&
                        !TextUtils.isEmpty(nama) &&
                        !TextUtils.isEmpty(resistan) &&
                        !TextUtils.isEmpty(alamat) &&
                        !TextUtils.isEmpty(namaKontak) &&
                        !TextUtils.isEmpty(umurKontak) &&
                        !TextUtils.isEmpty(spGender) &&
                        !TextUtils.isEmpty(alamatKontak) &&
                        !TextUtils.isEmpty(hasilAkhir) &&
                        !TextUtils.isEmpty(tindakLanjut) &&
                        !TextUtils.isEmpty(tanggalppinh) &&
                        !TextUtils.isEmpty(hasilPPinh))
                {
                        sendPost(unitPelayanan,kabupaten,tanggalRegis,nik,nama,resistan,alamat,namaKontak,umurKontak,spGender,alamat,hasilAkhir,tindakLanjut,tanggalppinh,hasilAkhir,lokasi);
                }
            }
            private void sendPost(String unitPelayanan,String kabupaten,String tanggalRegis,String nik,String nama,String resisten,String alamat,String namaKontak,String umurKontak,String spGender,String alamatKontak,String hasilAkhir,String tindakLanjut,String tanggalPpInh,String hasilPpInh,String lokasi) {
                AndroidNetworking.post(CONSTANT.BASE_URL + "pelacakan")
                        .addHeaders("Authorization", "bearer " + tokenpassnakes)
                        .addBodyParameter("unit_pelayanan",unitPelayanan)
                        .addBodyParameter("kabupaten",kabupaten)
                        .addBodyParameter("tanggal_wawancara",tanggalRegis)
                        .addBodyParameter("nik",nik)
                        .addBodyParameter("nama",nama)
                        .addBodyParameter("resisten",resisten)
                        .addBodyParameter("alamat",alamat)
                        .addBodyParameter("nama_kontak",namaKontak)
                        .addBodyParameter("umurkontak_l",umurKontak)
                        .addBodyParameter("umurKontak_p",umurKontak)
                        .addBodyParameter("alamat_kontak",alamatKontak)
                        .addBodyParameter("hasil_akhir",hasilAkhir)
                        .addBodyParameter("tindak_lanjut",tindakLanjut)
                        .addBodyParameter("tanggalmulai",tanggalPpInh)
                        .addBodyParameter("hasil_pp_inh",hasilPpInh)
                        .addBodyParameter("lokasi","lokasi sekarang")
                        .addBodyParameter("fk_faskes","32")
                        .setTag("pelacakan")
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsObject(PostPelacakanDao.class, new ParsedRequestListener<PostPelacakanDao>() {
                            @Override
                            public void onResponse(PostPelacakanDao response) {
                                Log.i("xxx" , response.toString());
                                Log.i("xxx" , response.getStatus());
                                Toast.makeText(getApplicationContext(), "Pelacakan Berhasil diinput "+response.toString(), Toast.LENGTH_LONG).show();
                                AndroidNetworking.post(CONSTANT.BASE_URL + "adduserspoint")
                                        .addHeaders("Authorization", "bearer " + tokenpassnakes)
                                        .addHeaders("email", emailpassnakes)
                                        .setTag("addpoint")
                                        .setPriority(Priority.MEDIUM)
                                        .build()
                                        .getAsObject(AddPoinDao.class, new ParsedRequestListener() {
                                            @Override
                                            public void onResponse(Object response) {
                                                Toast.makeText(getApplicationContext(), "Poin Berhasil Ditambah "+response.toString(), Toast.LENGTH_LONG).show();

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
            }
        });



        Spinner spGendera = (Spinner) findViewById(R.id.spgender2);
        ArrayAdapter<String> adapterid = new ArrayAdapter<String>(Form16Activity.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.gender));
        adapterid.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGendera.setAdapter(adapterid);

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
