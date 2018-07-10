package idev.gin.nias;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import idev.gin.nias.data.model.LoginTokenCall;

import idev.gin.nias.data.model.Login;
import idev.gin.nias.data.remote.APIServiceLogin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static idev.gin.nias.data.remote.RetrofitClient.retrofit;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity{
    private UserLoginTask mAuthTask = null;
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    public Button mSignUpButton;
    private String tokens;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(R.drawable.bg);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mSignUpButton = (Button) findViewById(R.id.singup);
        mSignUpButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
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
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
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
        Login login = new Login (mEmailView.getText().toString(), mPasswordView.getText().toString());
        Call<LoginTokenCall> call = loginAPI.login(login);
        call.enqueue(new Callback<LoginTokenCall>() {
            @Override
            public void onResponse(Call<LoginTokenCall> call, Response<LoginTokenCall> response) {
                if (response.isSuccessful())
                    Toast.makeText(LoginActivity.this,"Login Berhasil "+response.body().getToken(),Toast.LENGTH_LONG).show();
                    tokens = response.body().getToken();
                    Intent intent = new Intent(LoginActivity.this,MenuKaderActivity.class);
                    intent.putExtra("email",response.body().getToken());
                    intent.putExtra("token",tokens);
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
                Toast.makeText(LoginActivity.this,"Login Error",Toast.LENGTH_LONG).show();
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

