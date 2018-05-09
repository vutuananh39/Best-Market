package com.caocao.bestmarket;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by THONG on 4/12/2018.
 */

public class DauGiaSanPham extends AppCompatActivity{
    private Button btnNhapGiaSP;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daugiasanpham);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnNhapGiaSP=(Button) findViewById(R.id.btnnhapgia);
        btnNhapGiaSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DauGiaSanPham.this, ListProductDauGia.class);
                startActivity(intent);
                Toast.makeText(DauGiaSanPham.this, "Bạn đã đấu giá sản phẩm này", Toast.LENGTH_SHORT).show();
            }
        });
    }
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
