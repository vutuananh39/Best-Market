package com.caocao.bestmarket.Product;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.caocao.bestmarket.R;

public class HistorySaleProductActivity extends AppCompatActivity {

    TextView txthistoryTotalsale,txtAmountDientu,txtAmountQuanao,txtAmountMyPham,txtAmountXe,txtAmountHanggiadungvadoisong,txtAmountSach;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_sale_product);
        addControl();
        addEvent();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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


    public void addControl(){
        txtAmountDientu = findViewById(R.id.txtAmountDientu);
        txtAmountHanggiadungvadoisong = findViewById(R.id.txtAmountHanggiadungvadoisong);
        txthistoryTotalsale = findViewById(R.id.txthistoryTotalsale);
        txtAmountQuanao = findViewById(R.id.txtAmountQuanao);
        txtAmountMyPham = findViewById(R.id.txtAmountMyPham);
        txtAmountXe = findViewById(R.id.txtAmountXe);
        txtAmountSach = findViewById(R.id.txtAmountSach);


    }
    public void addEvent(){
        txthistoryTotalsale.setText("20");
        txtAmountDientu.setText("10");
        txtAmountQuanao.setText("1");
        txtAmountMyPham.setText("4");
        txtAmountXe.setText("1");
        txtAmountHanggiadungvadoisong.setText("2");
        txtAmountSach.setText("2");

    }
}
