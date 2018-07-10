package idev.gin.nias;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class Form15Activity extends AppCompatActivity {

    private static final String TAG = "form_tb15";
    private TextView mDisplayDate;
    private TextView tanggallahir;
    private TextView mTanggalBaca;
    private TextView tanggalSuntik;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private DatePickerDialog.OnDateSetListener mTanggalLahir;
    private DatePickerDialog.OnDateSetListener mBacaListener;
    private DatePickerDialog.OnDateSetListener mTanggalSuntik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_15);

        Spinner spyn1 = (Spinner) findViewById(R.id.spks);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Form15Activity.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.yn));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spyn1.setAdapter((adapter));

        Spinner spyn2 = (Spinner) findViewById(R.id.spke);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(Form15Activity.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.yn));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spyn2.setAdapter((adapter1));

        Spinner sphiv = (Spinner) findViewById(R.id.sphiv);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(Form15Activity.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.HIV));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sphiv.setAdapter((adapter2));

        Spinner sprl = (Spinner) findViewById(R.id.sprl);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(Form15Activity.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.resikolain));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sprl.setAdapter((adapter3));

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
                        Form15Activity.this,
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

        tanggallahir = (TextView) findViewById(R.id.tgllhr);
        tanggallahir.setPaintFlags(tanggallahir.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tanggallahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Form15Activity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mTanggalLahir,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mTanggalLahir = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year , int month,int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: date "+ day + "/" + month + "/" + year);
                String date = day +"/"+ month+ "/" + year;
                tanggallahir.setText(date);
            }
        };
        mTanggalBaca = (TextView) findViewById(R.id.tglbc);
        mTanggalBaca.setPaintFlags(tanggallahir.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        mTanggalBaca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Form15Activity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mBacaListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mBacaListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year , int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: date "+ day + "/" + month + "/" + year);
                String date = "Tanggal Baca :" +day +"/"+ month+ "/" + year;
                mTanggalBaca.setText(date);
            }
        };

        tanggalSuntik = (TextView) findViewById(R.id.tglstk);
        tanggalSuntik.setPaintFlags(tanggallahir.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tanggalSuntik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Form15Activity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mTanggalSuntik,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mTanggalSuntik = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year , int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: date "+ day + "/" + month + "/" + year);
                String date = "Tanggal Suntik : "+ day +"/"+ month+ "/" + year;
                tanggalSuntik.setText(date);
            }
        };

        Spinner spGender = (Spinner) findViewById(R.id.spgender);
        ArrayAdapter<String> adapterid = new ArrayAdapter<String>(Form15Activity.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.gender));
        adapterid.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGender.setAdapter((adapterid));
    }
}
