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

import java.util.Calendar;

import idev.gin.nias.R;
import idev.gin.nias.data.model.POST_KONTAK;
import idev.gin.nias.data.remote.APIServiceKontak;
import idev.gin.nias.data.remote.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Form16Activity extends AppCompatActivity {
    private static final String TAG = RiwayatTbActivity.class.getName();
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private APIServiceKontak mAPIServiceKontak;
    private TextView mResponseTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_16);
        final TextInputEditText unitPelayananEt = (TextInputEditText) findViewById(R.id.unit_pelayanan);
        final TextInputEditText kabupatenEt = (TextInputEditText) findViewById(R.id.kabkota);
        final TextView tanggalEt = (TextView) findViewById(R.id.tanggalregis);
        final TextInputEditText nikEt = (TextInputEditText) findViewById(R.id.nikKontak);
        final TextInputEditText namaEt = (TextInputEditText) findViewById(R.id.nama);
        final TextInputEditText resistanEt= (TextInputEditText) findViewById(R.id.sensitifResistan);
        final TextInputEditText alamatEt = (TextInputEditText) findViewById(R.id.alamat);
        final TextInputEditText namaKontakEt = (TextInputEditText) findViewById(R.id.namaKontak);
        final TextInputEditText umurKontakEt = (TextInputEditText) findViewById(R.id.usiaAnak);
        final Spinner spgender = (Spinner) findViewById(R.id.spgender);
        final TextInputEditText alamatKontakEt = (TextInputEditText) findViewById(R.id.alamatKontak);
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
                mAPIServiceKontak.savePost(unitPelayanan,kabupaten,tanggalRegis,nik,nama,resisten,alamat,namaKontak,umurKontak,spGender,alamatKontak,hasilAkhir,tindakLanjut,tanggalPpInh,hasilPpInh,lokasi).enqueue(new Callback<POST_KONTAK>() {
                    @Override
                    public void onResponse(Call<POST_KONTAK> call, Response<POST_KONTAK> response) {

                        if(response.isSuccessful()) {
                            showResponse(response.body().toString());
                            Log.i(TAG, "post submitted to API." + response.body().toString());
                        }
                    }

                    public void showResponse(String response) {
                        if(mResponseTv.getVisibility() == View.GONE) {
                            mResponseTv.setVisibility(View.VISIBLE);
                        }
                        mResponseTv.setText(response);
                    }

                    @Override
                    public void onFailure(Call<POST_KONTAK> call, Throwable t) {
                        Log.e(TAG, "Unable to submit post to API.");
                    }
                });
            }
        });



        Spinner spGender = (Spinner) findViewById(R.id.spgender);
        ArrayAdapter<String> adapterid = new ArrayAdapter<String>(Form16Activity.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.gender));
        adapterid.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGender.setAdapter((adapterid));


        mDisplayDate = (TextView) findViewById(R.id.datepick);
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
                mDisplayDate.setText(date);
            }
        };
    }
}
