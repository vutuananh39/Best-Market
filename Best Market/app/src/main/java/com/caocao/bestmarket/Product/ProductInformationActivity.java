package com.caocao.bestmarket.Product;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.caocao.bestmarket.Model.Product;
import com.caocao.bestmarket.R;


import static com.caocao.bestmarket.Product.ViewListProductActivity.lvProductActi;

public class ProductInformationActivity extends AppCompatActivity {

    TextView txtShowNameProduct,txtShowDescription,txtDateCreate,txtShowCategory,txtShowPrice;
    ImageView imageShowProduct;
    Product product;
    Button btnviewFeedback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_information);
        int position =0 ;
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null){
            position = bundle.getInt("sanpham");
        }
        Log.e("Xin chao",position+"");
//        Log.e("Xin chao",lvProductActi.getCount()+"");
//        Toast.makeText(ProductInformationActivity.this, position,  Toast.LENGTH_LONG).show();
        product = (Product) lvProductActi.getItemAtPosition(position);
        addControl();
        addEvent();

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnviewFeedback=(Button) findViewById(R.id.btnviewfeedback);
        final LinearLayout listcmt=(LinearLayout) findViewById(R.id.listcmt);
        btnviewFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(listcmt.getVisibility()==View.GONE) {
                    btnviewFeedback.setBackgroundColor(getResources().getColor(R.color.secondaryLightColor));
                    listcmt.setVisibility(View.VISIBLE);
                }
                else{
                    listcmt.setVisibility(View.GONE);
                    btnviewFeedback.setBackgroundColor(getResources().getColor(R.color.primaryColor));
                }
            }
        });
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


    void addControl(){
        txtShowNameProduct = findViewById(R.id.txtShowNameProduct);
        txtShowDescription = findViewById(R.id.txtShowDescription);
        txtDateCreate = findViewById(R.id.txtDateCreate);
        txtShowCategory = findViewById(R.id.txtShowCategory);
        imageShowProduct = findViewById(R.id.imageShowProduct);
        txtShowPrice = findViewById(R.id.txtPrice);

    }
    void addEvent(){
        txtShowNameProduct.setText(product.getName());
        txtShowDescription.setText(product.getDescription());
        txtDateCreate.setText("Hello");
        txtShowCategory.setText(product.getCategory());
//        Set ảnh
        imageShowProduct.setImageBitmap(product.getPhoto());
        txtShowPrice.setText(product.getPrice()+"VND");
    }
}
