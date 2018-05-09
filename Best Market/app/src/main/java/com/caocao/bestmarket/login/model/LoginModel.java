package com.caocao.bestmarket.login.model;

import com.caocao.bestmarket.login.presenter.ILoginPresenter;

/**
 * Created by vutuananh on 4/7/2018.
 */

public class LoginModel implements ILoginModel {
    private ILoginPresenter mPresenter;
    public LoginModel(ILoginPresenter presenter){
        mPresenter = presenter;
    }
}
