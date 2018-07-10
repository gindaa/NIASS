package idev.gin.nias;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import idev.gin.nias.data.model.POST_RIWAYAT;
import idev.gin.nias.data.remote.APIServiceRiwayat;
import idev.gin.nias.data.remote.ApiUtils;
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
                    sendPost(namaKader,desa,tanggal,nama_orangtua,nama_anak,usia_anak,jumlah_anak,alamat_desa,kontak_tb,Berat59,Berat14,Demam,Batuk,Kelenjar,Pembengkakan);
                }
            }
            private void sendPost(String unitPelayanan,String kabupaten,String tanggalRegis,String nik,String nama,String resisten,String alamat,String namaKontak,String umurKontak,String spGender,String alamatKontak,String hasilAkhir,String tindakLanjut,String tanggalPpInh,String hasilPpInh) {
                mApiServiceRiwayat.savePost(unitPelayanan,kabupaten,tanggalRegis,nik,nama,resisten,alamat,namaKontak,umurKontak,spGender,alamatKontak,hasilAkhir,tindakLanjut,tanggalPpInh,hasilPpInh).enqueue(new Callback<POST_RIWAYAT>() {
                    @Override
                    public void onResponse(Call<POST_RIWAYAT> call, Response<POST_RIWAYAT> response) {

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
                    public void onFailure(Call<POST_RIWAYAT> call, Throwable t) {
                        Log.e(TAG, "Unable to submit post to API.");
                    }
                });
            }
        });

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String tanggalriwayat = df.format(c);
        tanggalr.setText(tanggalriwayat);
    }
}
