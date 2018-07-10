package idev.gin.nias;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
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

import idev.gin.nias.data.remote.APIServiceSignUp;
import idev.gin.nias.data.remote.ApiUtils;
import idev.gin.nias.data.model.POST_AKUN;
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
        final Spinner roleSp = (Spinner) findViewById(R.id.pilihlogin);
        final TextInputEditText passwordEt = (TextInputEditText) findViewById(R.id.password);
        final TextInputEditText kPasswordEt = (TextInputEditText) findViewById(R.id.konf_password);
        String response;

        Button submitBtn = (Button) findViewById(R.id.btnSignup);
        mAPIServiceRegis = ApiUtils.getAPIRegis();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEt.getText().toString().trim();
                String nama = namaEt.getText().toString().trim();
                String gender = genderSp.getSelectedItem().toString().trim();
                String tanggalLahir = tanggalLahirEt.getText().toString().trim();
                String tempatLahir = tempatLahirEt.getText().toString().trim();
                String noHp = noHpEt.getText().toString().trim();
                String role = roleSp.getSelectedItem().toString().trim();
                String password = passwordEt.getText().toString().trim();

                if (!TextUtils.isEmpty(nama) &&
                        !TextUtils.isEmpty(email) &&
                        !TextUtils.isEmpty(gender) &&
                        !TextUtils.isEmpty(tanggalLahir) &&
                        !TextUtils.isEmpty(tempatLahir) &&
                        !TextUtils.isEmpty(noHp) &&
                        !TextUtils.isEmpty(role) &&
                        !TextUtils.isEmpty(password) &&
                        (kPasswordEt) != (passwordEt)
                        ) {
                    sendPost(email, nama, gender, tempatLahir, tanggalLahir, noHp, password, role);
                }
            }

            private void sendPost(String email, String nama, String gender, String tempatLahir, String tanggalLahir, String noHp, String role, String password) {
                mAPIServiceRegis.savePost(email, nama, gender, tempatLahir, tanggalLahir, noHp, role, password).enqueue(new Callback<POST_AKUN>() {
                    @Override
                    public void onResponse(Call<POST_AKUN> call, Response<POST_AKUN> response) {
                        if (response.isSuccessful()) {
                            Intent intant = new Intent(SignUpActivity.this, LoginActivity.class);
                            startActivity(intant);
                            Toast.makeText(SignUpActivity.this, "Sign up Berhasil", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<POST_AKUN> call, Throwable t) {
                        Toast.makeText(SignUpActivity.this, "Sign up Errorr", Toast.LENGTH_LONG).show();
                    }
                });
            }
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
    }
}