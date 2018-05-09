package com.caocao.bestmarket;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by THONG on 4/12/2018.
 */

public class ProductDauGia extends AppCompatActivity{

    String[] name = {"Nam", "Danh", "Hai", "Huy", "Sang"};
    String[] price = {"5,000,000", "5,100,000", "4,200,000", "3,000,000", "2,000,000"};
    ListView top5;
    AdapterTopDauGia listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button btnOk;
        final TextView txtGiaNhapVao;
        final TextView txtGiaCu;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productdaugia);


        top5 = (ListView) findViewById(R.id.top5daugia);

        listAdapter = new AdapterTopDauGia(ProductDauGia.this, name, price);

        top5.setAdapter(listAdapter);

        txtGiaCu=(TextView) findViewById(R.id.txtMyPrice);
        txtGiaNhapVao=(TextView) findViewById(R.id.txtPriceInput);
        btnOk=(Button) findViewById(R.id.btnnhapgia);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Double.parseDouble(txtGiaNhapVao.getText().toString())>Double.parseDouble(txtGiaCu.getText().toString()))
                    txtGiaCu.setText(txtGiaNhapVao.getText());
            }
        });



        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle bundle=getIntent().getExtras();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id =item.getItemId();
        if(id==android.R.id.home) this.finish();
/*     if(mTonggle.onOptionsItemSelected(item)){
         return true;
     }
  */
        return super.onOptionsItemSelected(item);
    }

}
