package idev.gin.nias.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
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
import idev.gin.nias.utils.CONSTANT;

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
    SharedPreferences sharedPref;
    String emaildefault;

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
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
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
                        String token = response.getToken();
                        getAkunId(email, response.getToken());
                        Toast.makeText(getApplicationContext(), "Login Berhasil", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(getApplicationContext(), "Login Failed: " + anError.getErrorBody(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void getAkunId(final String email, final  String token) {
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
                        Log.i("role",role);
                        if(role.toLowerCase().contains("kader")){
                            Toast.makeText(getApplicationContext(), "KADER", Toast.LENGTH_LONG).show();
                            sharedPref = getApplicationContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);
                            SharedPreferences.Editor edit = sharedPref.edit();
                            edit.putString("role", role);
                            edit.apply();
                                startkader();
                            }
                            else if(role.toLowerCase().contains("nakes")) {
                            Toast.makeText(getApplicationContext(), "NAKES", Toast.LENGTH_LONG).show();
                            sharedPref = getApplicationContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);
                            SharedPreferences.Editor edit = sharedPref.edit();
                            edit.putString("role", role);
                            edit.apply();
                                startnakes();

                            }
                            else {
                            Toast.makeText(getApplicationContext(), "Role not found", Toast.LENGTH_LONG).show();
                        }
                    }

                    private void startkader() {
                        Intent intent = new Intent(LoginActivity.this,MenuKaderActivity.class);
                        intent.putExtra("token" , token);
                        intent.putExtra("email", email);
                        startActivity(intent);
                    }

                    private void startnakes() {
                        Intent intent = new Intent(LoginActivity.this,MenuNakesActivity.class);
                        intent.putExtra("token" , token);
                        intent.putExtra("email", email);
                        startActivity(intent);
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
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();


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

