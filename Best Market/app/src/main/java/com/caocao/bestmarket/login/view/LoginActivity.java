package com.caocao.bestmarket.login.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.caocao.bestmarket.forgotPassord.ForgotPassword_1Activity;
import com.caocao.bestmarket.R;
import com.caocao.bestmarket.Register.RegisterActivity;
import com.caocao.bestmarket.login.presenter.LoginPresenter;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.util.Arrays;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements ILoginView{

    // UI references.
    private EditText mUserView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private Button mLoginServerButton ;
    private LoginButton mLoginFBButton ;
    private SignInButton mLoginGGButton;
    private  Button mRegisterButton;
    private  View mForgotPassword;
    private LoginPresenter mPresenter;
    private GoogleSignInClient mGoogleSignInClient;
    private  final int RC_SIGN_IN =0;
    private CallbackManager mCallbackManager;
    private TextView mForgotPassView;
    private static final String EMAIL = "email";


//    @Override
//    protected void onStart() {
//        super.onStart();
//        // Check for existing Google Sign In account, if the user is already signed in
//        // the GoogleSignInAccount will be non-null.
////        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
////        updateUI(account);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        mRegisterButton = (Button)findViewById(R.id.register_button);
        mRegisterButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        mForgotPassView = (TextView)findViewById(R.id.forgot_pass_text);
        mForgotPassView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(LoginActivity.this, ForgotPassword_1Activity.class);
                startActivity(intent);
            }
        });

        //
        // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        //

        mPresenter = new LoginPresenter(this);
        // Set up the login form.
        mUserView = (EditText) findViewById(R.id.email);
        //populateAutoComplete();

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    mPresenter.attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);


//        mLoginServerButton = (Button)findViewById(R.id.email_sign_in_button);
//        mLoginServerButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onLoginServerClick(view);
//            }
//        });

        mCallbackManager = CallbackManager.Factory.create();

        mLoginFBButton = (LoginButton)findViewById(R.id.login_fb_button);
        mLoginFBButton.setReadPermissions(Arrays.asList(EMAIL));
        // Callback registration

        mLoginFBButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Toast.makeText(getApplicationContext(),"Đăng nhập thành công", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                // App code
                Toast.makeText(getApplicationContext(),"Hủy đăng nhập", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Toast.makeText(getApplicationContext(),"Chưa kết nối internet", Toast.LENGTH_SHORT).show();
            }
        });


        ///////////////////////////////////////////////////////
        mLoginGGButton = (SignInButton)findViewById(R.id.login_gg_button);
        TextView textView = (TextView) mLoginGGButton.getChildAt(0);
        textView.setText(R.string.google);
        mLoginGGButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onLoginGGClick(view);
            }
        });
//
//        mForgotPassword = (View)findViewById(R.id.forgotPassword);
//        mForgotPassword.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onForgotPasswordClick(view);
//            }
//        });

    }


    public void onLoginGGClick(View view){
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            //updateUI(account);

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            //Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            //updateUI(null);
        }
    }



    /**
     * Shows the progress UI and hides the login form.
     */
    //@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    @Override
    public void showProgress(final boolean show) {
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

    @Override
    public String getEmail(){
        return mUserView.getText().toString();
    }

    @Override
    public String getPassword(){
        return mPasswordView.getText().toString();
    }

    @Override
    public void finishTask(){
        finish();
    }

    @Override
    public void setErrorEmail(CharSequence error){
        mUserView.setError(error);
    }

    @Override
    public void setErrorPassword(CharSequence error){
        mPasswordView.setError(error);
    }

    @Override
    public void requestFocusEmail(){
        mUserView.requestFocus();
    }

    @Override
    public  void requestFocusPassword(){
        mPasswordView.requestFocus();
    }

    @Override
    public String getStringValue(int value){
        return getString(value);
    }


}



