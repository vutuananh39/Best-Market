package com.caocao.bestmarket.myAccount;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.caocao.bestmarket.forgotPassord.ForgotPassword_1Activity;
import com.caocao.bestmarket.R;

public class EditInfoActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);

        findViewById(R.id.user_name).setOnClickListener(this);
        findViewById(R.id.password).setOnClickListener(this);
        findViewById(R.id.full_name).setOnClickListener(this);
        findViewById(R.id.email).setOnClickListener(this);
        findViewById(R.id.phone_number).setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        Intent intent=new Intent(EditInfoActivity.this, EditUserNameActivity.class);
        switch (view.getId()){
            case R.id.user_name:
                //intent =new Intent(EditInfoActivity.this, EditUserNameActivity.class);
                break;
            case R.id.password:
                intent =new Intent(EditInfoActivity.this, ForgotPassword_1Activity.class);
                break;

            case R.id.full_name:
                intent = new Intent(EditInfoActivity.this, EditNameActivity.class);
                break;

            case R.id.email:
                intent = new Intent(EditInfoActivity.this,EditEmailActivity.class);
                break;

            case R.id.phone_number:
                intent =new Intent(EditInfoActivity.this, EditPhoneNumberActivity.class);
                break;
        }
        startActivity(intent);
    }
}
