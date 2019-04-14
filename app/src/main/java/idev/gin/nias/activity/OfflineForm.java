package idev.gin.nias.activity;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import idev.gin.nias.R;

public class OfflineForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_form);

        final TextInputEditText namaOrangTua = (TextInputEditText)findViewById(R.id.namaortuasuhof);
        final TextInputEditText namaAnak = (TextInputEditText) findViewById(R.id.namaAnakof);
        final Spinner usiaAnak= (Spinner) findViewById(R.id.spusiaof);
        final Spinner jumlahAnak = (Spinner) findViewById(R.id.spjumlahanakof);
        final TextInputEditText alamatDesa =(TextInputEditText) findViewById(R.id.alamatdesaof);
        final Spinner kontakTb = (Spinner) findViewById(R.id.spkontakof);
        final Spinner berat59 = (Spinner) findViewById(R.id.spberat59of);
        final Spinner berat14 = (Spinner) findViewById(R.id.spberat14of);
        final Spinner demam = (Spinner) findViewById(R.id.spdemamof);
        final Spinner batuk = (Spinner) findViewById(R.id.spbatukof);
        final Spinner kelenjarlimfie = (Spinner) findViewById(R.id.sppembesarankelenjarof);
        final Spinner pembengkakan = (Spinner) findViewById(R.id.sppembekakanof);
        final Spinner provinsi = (Spinner) findViewById(R.id.spprovinsiriwayatof);
        final Spinner kabupaten = (Spinner) findViewById(R.id.spkabupatenriwayatof);
        final Spinner kelurahan = (Spinner) findViewById(R.id.spkelurahanriwayatof);
        final Spinner kecamatan = (Spinner) findViewById(R.id.spkecamatanriwayatof);

        ArrayAdapter<String> adapterfana = new ArrayAdapter<String>(OfflineForm.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.fanayama));
        adapterfana.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> adaptermin = new ArrayAdapter<String>(OfflineForm.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.miniamolo));
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

        Spinner spkontak = (Spinner) findViewById(R.id.spkontakof);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(OfflineForm.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.kontak));
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spkontak.setAdapter((adapter1));

        Spinner spberat059 = (Spinner) findViewById(R.id.spberat59of);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(OfflineForm.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.berat3));
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spberat059.setAdapter((adapter2));

        Spinner spberat614 = (Spinner) findViewById(R.id.spberat14of);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(OfflineForm.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.berat6));
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spberat614.setAdapter((adapter3));

        Spinner spdemam = (Spinner) findViewById(R.id.spdemamof);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(OfflineForm.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.demam));
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spdemam.setAdapter((adapter4));

        Spinner spbatuk = (Spinner) findViewById(R.id.spbatukof);
        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(OfflineForm.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.batuk));
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spbatuk.setAdapter((adapter5));

        Spinner spkelenjar = (Spinner) findViewById(R.id.sppembesarankelenjarof);
        ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(OfflineForm.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.pembesaran));
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spkelenjar.setAdapter((adapter6));

        Spinner sptulang = (Spinner) findViewById(R.id.sppembekakanof);
        ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(OfflineForm.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.tulang));
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sptulang.setAdapter((adapter7));

        Spinner spusia = (Spinner) findViewById(R.id.spusiaof);
        ArrayAdapter<String> adapterusia = new ArrayAdapter<String>(OfflineForm.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.usia));
        adapterusia.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spusia.setAdapter((adapterusia));

        Spinner spanaklain = (Spinner) findViewById(R.id.spjumlahanakof);
        ArrayAdapter<String> adapteranaklain = new ArrayAdapter<String>(OfflineForm.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.anaklains));
        adapteranaklain.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spanaklain.setAdapter((adapteranaklain));

        Spinner spprov = (Spinner) findViewById(R.id.spprovinsiriwayatof);
        ArrayAdapter<String> adaptereprov = new ArrayAdapter<String>(OfflineForm.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.provinsi));
        adaptereprov.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spprov.setAdapter((adaptereprov));

        Spinner spkab = (Spinner) findViewById(R.id.spkabupatenriwayatof);
        ArrayAdapter<String> adapterkab = new ArrayAdapter<String>(OfflineForm.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.kab));
        adapterkab.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spkab.setAdapter((adapterkab));

        Spinner spkecamatan = (Spinner) findViewById(R.id.spkecamatanriwayatof);
        ArrayAdapter<String> adapterkec = new ArrayAdapter<String>(OfflineForm.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.kecamatan));
        adapterkec.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spkecamatan.setAdapter((adapterkec));

        Spinner spkelurahan = (Spinner) findViewById(R.id.spkelurahanriwayatof);
        ArrayAdapter<String> adapterkel = new ArrayAdapter<String>(OfflineForm.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.pilihkec));
        adapterkel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spkelurahan.setAdapter((adapterkel));

    }
}
