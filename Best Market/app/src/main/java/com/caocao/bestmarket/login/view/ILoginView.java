package com.caocao.bestmarket.login.view;

/**
 * Created by vutuananh on 4/7/2018.
 */

public interface ILoginView {
    String getEmail();
    String getPassword();
    void setErrorEmail(CharSequence error);
    void setErrorPassword(CharSequence error);
    void requestFocusEmail();
    void requestFocusPassword();
    void showProgress(final boolean show);
    String getStringValue(int value);
    void finishTask();

}
