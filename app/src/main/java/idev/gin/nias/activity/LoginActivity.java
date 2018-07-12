package idev.gin.nias.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import idev.gin.nias.R;
import idev.gin.nias.dao.AkunidDao;
import idev.gin.nias.dao.LoginDao;
import idev.gin.nias.data.model.LoginTokenCall;

import idev.gin.nias.data.model.Login;
import idev.gin.nias.data.remote.APIServiceLogin;
import idev.gin.nias.utils.CONSTANT;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static idev.gin.nias.data.remote.RetrofitClient.retrofit;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {
    // TODO: Usahain pake variabel global secukupnya aja. Lebih baik pake variabel lokal.
    private UserLoginTask mAuthTask = null;
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    public Button mSignUpButton;
    private String tokens;

    /**
     * Kalo bingung, dao dipisah aja setiap endpointnya
     * Dao masih kotlin, ubah aja ke java kl maum gua gada pojo converter
     *
     * Login dikasih overlay item aga transparan, ga keliatan soalnya teksnya
     *
     * Dokumentasi dan penggunaan FAN: https://github.com/amitshekhariitbhu/Fast-Android-Networking
     * Pake yang getAsObject jangan yang JSON, biar rapih dan ga bingung, tapi kalo mau juga gapapa.
     *
     * Refrensi clean code: https://github.com/ryanmcdermott/clean-code-javascript
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(R.drawable.bg);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mSignUpButton = (Button) findViewById(R.id.singup);
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmailView.getText().toString();
                String password = mPasswordView.getText().toString();
                login(email, password);
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    public void login(final String email, String password) {
        AndroidNetworking.post(CONSTANT.BASE_URL + "login")
                .addBodyParameter("email", email)
                .addBodyParameter("password", password)
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(LoginDao.class, new ParsedRequestListener<LoginDao>() {
                    @Override
                    public void onResponse(LoginDao response) {
                        Log.i("xxx", "" + response.getToken());
                        getAkunId(email, response.getToken());
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(getApplicationContext(), "Login Failed: " + anError.getErrorBody(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void getAkunId(String email, String token) {
        AndroidNetworking.get(CONSTANT.BASE_URL + "akunid")
                .addHeaders("Authorization", "bearer " + token)
                .addHeaders("email", email)
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(AkunidDao.class, new ParsedRequestListener<AkunidDao>() {
                    @Override
                    public void onResponse(AkunidDao response) {
                        String role = response.getResult().get(0).getRole();
                        Log.i("xxx", role);
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(getApplicationContext(),  "Error: " + anError.getErrorBody(), Toast.LENGTH_LONG).show();
                    }
                });
    }


    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);


        // Store values at the time of the login attempt.
        final String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        Login login = new Login(mEmailView.getText().toString(), mPasswordView.getText().toString());
        APIServiceLogin loginAPI = retrofit.create(APIServiceLogin.class);
        Call<LoginTokenCall> call = loginAPI.login(login);
        call.enqueue(new Callback<LoginTokenCall>() {
            @Override
            public void onResponse(Call<LoginTokenCall> call, Response<LoginTokenCall> response) {
                if (response.isSuccessful())
                    Toast.makeText(LoginActivity.this, "Login Berhasil " + response.body().getToken(), Toast.LENGTH_LONG).show();
                tokens = response.body().getToken();
                Intent intent = new Intent(LoginActivity.this, MenuKaderActivity.class);
                intent.putExtra("email", response.body().getToken());
                intent.putExtra("token", tokens);
                startActivity(intent);
//                    SharedPreferences preferences = getSharedPreferences("auth",MODE_PRIVATE);
//                    preferences.edit().putString("email","email").commit();
//                    preferences.edit().putString("token",tokens).commit();
//                    APIServiceSignUp loginrole = retrofit.create(APIServiceSignUp.class);
//                    Call<POST_AKUN> calls = loginrole.getAkun(tokens,email);
//                    calls.enqueue(new Callback<POST_AKUN>() {
//                        @Override
//                        public void onResponse(Call<POST_AKUN> calls, Response<POST_AKUN> response) {
//                            if(response.body().getRole() == "Kader"){
//                                Intent intent = new Intent(LoginActivity.this,MenuKaderActivity.class);
//                                startActivity(intent);
//                            }
//                            else if(response.body().getRole() == "Nakes") {
//                                Intent intent = new Intent(LoginActivity.this,MenuNakesActivity.class);
//                                startActivity(intent);
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<POST_AKUN> call, Throwable t) {
//                        }
//                    });
            }

            @Override
            public void onFailure(Call<LoginTokenCall> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Login Error", Toast.LENGTH_LONG).show();
            }
        });

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            showProgress(true);
            mAuthTask = new UserLoginTask(email, password);
            mAuthTask.execute((Void) null);
        }

    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 2;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            APIServiceLogin loginAPI = retrofit.create(APIServiceLogin.class);

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                finish();
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}

