package com.caocao.bestmarket.forgotPassord;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.caocao.bestmarket.R;

public class ForgotPassword_1Activity extends AppCompatActivity {

    private TextView mText;
    private Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_1);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

       // getSupportActionBar().setDisplayHomeAsUpEnabled(true)

        mText = (TextView)findViewById(R.id.forgot_pass_text);
        mButton = (Button)findViewById(R.id.forgot_pass_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgotPassword_1Activity.this, ForgotPassword_2Activity.class);
                startActivity(intent);
            }
        });
    }
}
