package com.caocao.bestmarket;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by THONG on 4/12/2018.
 */

public class ListProductDauGia extends AppCompatActivity {
    //private DrawerLayout mDrawLayout;
    //private ActionBarDrawerToggle mTonggle;
    private TextView txtvInfo;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listproductdaugia);
/*
        mDrawLayout=(DrawerLayout) findViewById(R.id.listProductDauGia);
        mTonggle=new ActionBarDrawerToggle(this, mDrawLayout, R.string.open, R.string.close);
        mDrawLayout.addDrawerListener(mTonggle);
        mTonggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
*/
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
/*
        txtvInfo = (TextView) findViewById(R.id.txtInfo);
        txtvInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ListProductDauGia.this, DauGiaSanPham.class);
                startActivity(intent);
            }
        });
        Bundle bundle=getIntent().getExtras();
*/
    }



    public boolean onOptionsItemSelected(MenuItem item) {
        int id =item.getItemId();
        if(id==android.R.id.home) this.finish();
        return super.onOptionsItemSelected(item);
    }
}
