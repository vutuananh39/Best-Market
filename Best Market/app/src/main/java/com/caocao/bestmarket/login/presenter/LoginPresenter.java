package com.caocao.bestmarket.login.presenter;

import android.os.AsyncTask;
import android.text.TextUtils;

import com.caocao.bestmarket.R;
import com.caocao.bestmarket.login.model.ILoginModel;
import com.caocao.bestmarket.login.model.LoginModel;
import com.caocao.bestmarket.login.view.ILoginView;

/**
 * Created by vutuananh on 4/7/2018.
 */

public class LoginPresenter implements ILoginPresenter {
    private ILoginView mView;
    private ILoginModel mModel;
    private UserLoginTask mAuthTask;

    public LoginPresenter(ILoginView view){
        mView = view;
        mModel = new LoginModel(this);
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    @Override
    public void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mView.setErrorEmail(null);
        mView.setErrorPassword(null);

        // Store values at the time of the login attempt.
        String email = mView.getEmail();
        String password = mView.getPassword();

        boolean cancel = false;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mView.setErrorPassword(mView.getStringValue(R.string.error_invalid_password));
            mView.requestFocusPassword();
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mView.setErrorEmail(mView.getStringValue(R.string.error_field_required));
            mView.requestFocusEmail();
            cancel = true;
        } else if (!isEmailValid(email)) {
            mView.setErrorEmail(mView.getStringValue(R.string.error_invalid_email));
            mView.requestFocusEmail();
            cancel = true;
        }

        if(!cancel){
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            mView.showProgress(true);

            mAuthTask = new UserLoginTask(email, password);
            mAuthTask.execute((Void) null);
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
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
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }


            /**
             * A dummy authentication store containing known user names and passwords.
             * TODO: remove after connecting to a real authentication system.
             */
            String[] DUMMY_CREDENTIALS = new String[]{
                    "foo@example.com:hello", "bar@example.com:world"
            };

            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mEmail)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }

            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            mView.showProgress(false);

            if (success) {
                mView.finishTask();
            } else {
                mView.setErrorPassword(mView.getStringValue(R.string.error_incorrect_password));
                mView.requestFocusPassword();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            mView.showProgress(false);
        }
    }
}
