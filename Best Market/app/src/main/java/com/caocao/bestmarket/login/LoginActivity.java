package com.caocao.bestmarket.login;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.caocao.bestmarket.BaseActivity;
import com.caocao.bestmarket.MainActivity;
import com.caocao.bestmarket.R;
import com.facebook.AccessToken;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener{

    // UI references.
    private EditText mEmailField;
    private EditText mPasswordField;
    LoginButton mLoginFBButton ;
    SignInButton mLoginGGButton;
    private GoogleSignInClient mGoogleSignInClient;
    private  final int RC_SIGN_IN =9001;
    private CallbackManager mCallbackManager;
    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mAccountRef;
    private static final String TAG = "LoginActivity";

    @Override
    protected void onStart() {
        super.onStart();

        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mFirebaseAuth.getCurrentUser();
        updateUI(currentUser);

    }


    @Override
    public void onClick(View view){
        int i = view.getId();
        if(isNetworkAvailable()) {

            if (i == R.id.register_button) {
                createAccount(mEmailField.getText().toString(), mPasswordField.getText().toString());
            } else if (i == R.id.forgot_pass_text) {
                Intent intent = new Intent(LoginActivity.this, ForgotPassword_1Activity.class);
                startActivity(intent);
            } else if (i == R.id.login_gg_button) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            } else if (i == R.id.email_sign_in_button) {
                signIn(mEmailField.getText().toString(), mPasswordField.getText().toString());
            }
        }
        else Toast.makeText(LoginActivity.this, "Không có kết nối internet!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Setup toolbar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // Setup onclick method for register button
        findViewById(R.id.register_button).setOnClickListener(this);
        // Setup onclick method for register button
        findViewById(R.id.forgot_pass_text).setOnClickListener(this);

        mEmailField = findViewById(R.id.email);
        mPasswordField = findViewById(R.id.password);

        // Setup value for firebaseAuth
        mFirebaseAuth = FirebaseAuth.getInstance();
        mAccountRef = FirebaseDatabase.getInstance().getReference().child("User");

        /*
        Email/password sign in
         */
        findViewById(R.id.email_sign_in_button).setOnClickListener(this);

        /*
        Facebook sign in
         */
        mCallbackManager = CallbackManager.Factory.create();
        mLoginFBButton = findViewById(R.id.login_fb_button);
        mLoginFBButton.setReadPermissions("email", "public_profile");
        mLoginFBButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess"+loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
                //updateUI(null);
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
                Toast.makeText(LoginActivity.this, "Không có kết nối internet!", Toast.LENGTH_SHORT).show();

                //updateUI(null);
            }
        });

        /*
        Gooogle sign in
         */
        mLoginGGButton = findViewById(R.id.login_gg_button);
        // setup name for google button
        TextView textView = (TextView)mLoginGGButton.getChildAt(0);
        textView.setText(R.string.google);

        mLoginGGButton.setOnClickListener(this);

        GoogleSignInOptions gso =new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // [START_EXCLUDE]
                //updateUI(null);
                // [END_EXCLUDE]
            }
        }
    }

    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }

        showProgressDialog();

        // [START sign_in_with_email]
        mFirebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mFirebaseAuth.getCurrentUser();
                            updateUI(user);
                            hideProgressDialog();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                           // updateUI(null);
                            hideProgressDialog();
                            Toast.makeText(getApplicationContext(), "Email hoặc mật khẩu không đúng!",
                                    Toast.LENGTH_SHORT).show();

                        }

                    }
                });
        // [END sign_in_with_email]
    }

    // [START auth_with_google]
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());
        // [START_EXCLUDE silent]
        showProgressDialog();
        // [END_EXCLUDE]

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mFirebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");

                            FirebaseUser user = mFirebaseAuth.getCurrentUser();
                            updateDatabase(user);
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            //Snackbar.make(findViewById(R.id.main_layout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                           // updateUI(null);
                        }

                        // [START_EXCLUDE]
                        hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
    }
    // [END auth_with_google]


    private void handleFacebookAccessToken(AccessToken token){
        Log.d("facebookLogin", "handleFacebookAccessToken"+token);
        showProgressDialog();

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mFirebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            // Sign in success, update UI
                            Log.d("facebookLogin", "signInWithCredential:success");
                           FirebaseUser user = mFirebaseAuth.getCurrentUser();
                            updateDatabase(user);
                            updateUI(user);
                        }
                        else {
                            Log.w("facebookLogin", "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                           // updateUI(null);
                        }
                        // [START_EXCLUDE]
                        hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
    }

    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }

        showProgressDialog();

        // [START create_user_with_email]
        mFirebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mFirebaseAuth.getCurrentUser();
                            updateDatabase(user);
                            updateUI(user);
                            hideProgressDialog();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            //updateUI(null);
                            hideProgressDialog();
                            Toast.makeText(getApplicationContext(), "Email đã được sử dụng!",
                                    Toast.LENGTH_SHORT).show();

                        }

                    }
                });
        // [END create_user_with_email]

    }

    private void updateDatabase(FirebaseUser user){
        DatabaseReference myUserdRef = mAccountRef.child(user.getUid());
        myUserdRef.addValueEventListener (new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(!dataSnapshot.exists()){
                    if(user!=null) {
                        //Toast.makeText(getApplicationContext(), "dataShapshot == null", Toast.LENGTH_SHORT).show();
                        Map<String, Object> hopperUpdates = new HashMap<>();

                        String name = user.getDisplayName();
                        if(name != null &&!name.isEmpty())
                            hopperUpdates.put("full_name", name);

                        String email = user.getEmail();
                        if(email!= null &&!email.isEmpty())
                            hopperUpdates.put("email_address", email);

                        String phone = user.getPhoneNumber();
                        if(phone != null && !phone.isEmpty())
                            hopperUpdates.put("phone_number", phone);

                        Uri photoUrl = user.getPhotoUrl();
                        if(photoUrl != null)
                            hopperUpdates.put("image_uri", photoUrl.toString());

                        mAccountRef.child(user.getUid()).updateChildren(hopperUpdates);
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    private void updateUI(FirebaseUser user) {
        hideProgressDialog();
        if (user != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = mEmailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmailField.setError(getString(R.string.error_field_required));
            valid = false;
        }else if(!email.contains("@") || email.contains(" ")){
            mEmailField.setError(getString(R.string.error_invalid_email));
            valid = false;
        }
        else {
            mEmailField.setError(null);
        }

        String password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(password) ) {
            mPasswordField.setError(getString(R.string.error_field_required));
            valid = false;
        }
        else if(mPasswordField.length() < 6){
            mPasswordField.setError(getString(R.string.error_invalid_password));
            valid = false;
        }
        else {
            mPasswordField.setError(null);
        }

        return valid;
    }

    private boolean isNetworkAvailable() {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager == null) return connected;

        NetworkInfo mobileNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifiNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        boolean bool = mobileNetworkInfo!= null && wifiNetworkInfo!=null &&
                (mobileNetworkInfo.getState() == NetworkInfo.State.CONNECTED ||
                        wifiNetworkInfo.getState() == NetworkInfo.State.CONNECTED);
        if(bool)
            //we are connected to a network
            connected = true;

        return connected;
    }
}



