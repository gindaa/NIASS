package idev.gin.nias.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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

import idev.gin.nias.R;
import idev.gin.nias.dao.AddPoinDao;
import idev.gin.nias.dao.SkoringDao;
import idev.gin.nias.data.remote.APIServiceSignUp;
import idev.gin.nias.data.remote.ApiUtils;
import idev.gin.nias.dao.SignupDao;
import idev.gin.nias.utils.CONSTANT;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.Calendar;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "SignUpActivity";
    private Button signupb;
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TextView mResponseTv;
    private APIServiceSignUp mAPIServiceRegis;
    SharedPreferences sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        final TextInputEditText emailEt = (TextInputEditText) findViewById(R.id.emails);
        final TextInputEditText namaEt = (TextInputEditText) findViewById(R.id.namalengkap);
        final Spinner genderSp = (Spinner) findViewById(R.id.spgender);
        final TextView tanggalLahirEt = (TextView) findViewById(R.id.datepick);
        final TextInputEditText tempatLahirEt = (TextInputEditText) findViewById(R.id.sgtptlhr);
        final TextInputEditText noHpEt = (TextInputEditText) findViewById(R.id.sgnhp);
        final TextInputEditText alamatEt = (TextInputEditText) findViewById(R.id.alamatsu);
        final Spinner roleSp = (Spinner) findViewById(R.id.pilihlogin);
        final TextInputEditText passwordEt = (TextInputEditText) findViewById(R.id.password);
        final TextInputEditText kPasswordEt = (TextInputEditText) findViewById(R.id.konf_password);
        final Spinner spKecamatan = (Spinner) findViewById(R.id.spkecamatan);
        final Spinner spKelurahan = (Spinner) findViewById(R.id.spkelurahan);
        String response;
        ArrayAdapter<String> adapterfana = new ArrayAdapter<String>(SignUpActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.fanayama));
        adapterfana.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> adaptermin = new ArrayAdapter<String>(SignUpActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.miniamolo));
        adaptermin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Button submitBtn = (Button) findViewById(R.id.btnSignup);
        mAPIServiceRegis = ApiUtils.getAPIRegis();

        spKecamatan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String kecamatan = spKecamatan.getSelectedItem().toString().toLowerCase();
                if (kecamatan.equals("fanayama")){
                    spKelurahan.setAdapter(adapterfana);
                }
                else if(kecamatan.equals("maniamolo")){
                    spKelurahan.setAdapter(adaptermin);
                }
        }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AndroidNetworking.post(CONSTANT.BASE_URL + "register")
                        .addBodyParameter("email",emailEt.getText().toString().trim())
                        .addBodyParameter("nama",namaEt.getText().toString().trim())
                        .addBodyParameter("jenis_kelamin",genderSp.getSelectedItem().toString())
                        .addBodyParameter("tempat_lahir",tempatLahirEt.getText().toString().trim())
                        .addBodyParameter("tanggal_lahir",tanggalLahirEt.getText().toString().trim())
                        .addBodyParameter("no_hp",noHpEt.getText().toString().trim())
                        .addBodyParameter("password",passwordEt.getText().toString().trim())
                        .addBodyParameter("role",roleSp.getSelectedItem().toString())
                        .addBodyParameter("alamat",alamatEt.getText().toString().trim())
                        .addBodyParameter("kabupaten","Nias Selatan")
                        .addBodyParameter("kecamatan",spKecamatan.getSelectedItem().toString())
                        .addBodyParameter("kelurahan",spKelurahan.getSelectedItem().toString())
                        .setTag("Register")
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsObject(SignupDao.class, new ParsedRequestListener<SignupDao>() {
                            @Override
                            public void onResponse(SignupDao response) {
                                Intent intant = new Intent(SignUpActivity.this, LoginActivity.class);
                                startActivity(intant);
                                Toast.makeText(SignUpActivity.this, "Sign up Berhasil", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onError(ANError anError) {
                                Toast.makeText(getApplicationContext(), "Input Failed: " + anError.getErrorBody(), Toast.LENGTH_LONG).show();

                            }
                        });
            }
//                String email = emailEt.getText().toString().trim();
//                String nama = namaEt.getText().toString().trim();
//                String gender = genderSp.getSelectedItem().toString().trim();
//                String tanggalLahir = tanggalLahirEt.getText().toString().trim();
//                String tempatLahir = tempatLahirEt.getText().toString().trim();
//                String noHp = noHpEt.getText().toString().trim();
//                String role = roleSp.getSelectedItem().toString().trim();
//                String password = passwordEt.getText().toString().trim();
//
//                if (!TextUtils.isEmpty(nama) &&
//                        !TextUtils.isEmpty(email) &&
//                        !TextUtils.isEmpty(gender) &&
//                        !TextUtils.isEmpty(tanggalLahir) &&
//                        !TextUtils.isEmpty(tempatLahir) &&
//                        !TextUtils.isEmpty(noHp) &&
//                        !TextUtils.isEmpty(role) &&
//                        !TextUtils.isEmpty(password) &&
//                        (kPasswordEt) != (passwordEt)
//                        ) {
//                    sendPost(email, nama, gender, tempatLahir, tanggalLahir, noHp, password, role);
//                }
//            }
//
//            private void sendPost(String email, String nama, String gender, String tempatLahir, String tanggalLahir, String noHp, String role, String password) {
//                mAPIServiceRegis.savePost(email, nama, gender, tempatLahir, tanggalLahir, noHp, role, password)
//                        .enqueue(new Callback<SignupDao>() {
//                    @Override
//                    public void onResponse(Call<SignupDao> call, Response<SignupDao> response) {
//                        if (response.isSuccessful()) {
//                            Intent intant = new Intent(SignUpActivity.this, LoginActivity.class);
//                            startActivity(intant);
//                            Toast.makeText(SignUpActivity.this, "Sign up Berhasil", Toast.LENGTH_LONG).show();
//                            sharedPref = getApplicationContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);
//                            SharedPreferences.Editor edit = sharedPref.edit();
//                            edit.putString("email", email);
//                            edit.apply();
//
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<SignupDao> call, Throwable t) {
//                        Toast.makeText(SignUpActivity.this, "Sign up Errorr", Toast.LENGTH_LONG).show();
//                    }
//                });
//            }
        });

        Spinner spGender = (Spinner) findViewById(R.id.spgender);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SignUpActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.gender));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGender.setAdapter((adapter));

        Spinner splogin = (Spinner) findViewById(R.id.pilihlogin);
        ArrayAdapter<String> adapterlogin = new ArrayAdapter<String>(SignUpActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.typelogin));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        splogin.setAdapter((adapterlogin));

        mDisplayDate = (TextView) findViewById(R.id.datepick);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        SignUpActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: date " + day + "/" + month + "/" + year);
                String date = day + "/" + month + "/" + year;
                mDisplayDate.setText(date);
            }
        };

        Spinner spkecamatan = (Spinner) findViewById(R.id.spkecamatan);
        ArrayAdapter<String> adapterkec = new ArrayAdapter<String>(SignUpActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.kecamatan));
        adapterkec.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spkecamatan.setAdapter((adapterkec));

//        Spinner spkelurahan = (Spinner) findViewById(R.id.spkelurahan);
//        ArrayAdapter<String> adapterkel = new ArrayAdapter<String>(SignUpActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.miniamolo));
//        adapterkel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spkelurahan.setAdapter((adapterkel));
    }
}