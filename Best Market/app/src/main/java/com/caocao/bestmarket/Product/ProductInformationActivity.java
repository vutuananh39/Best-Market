package com.caocao.bestmarket.Product;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.caocao.bestmarket.Model.Product;
import com.caocao.bestmarket.R;

import static com.caocao.bestmarket.Product.ViewListProduct.lvProduct;
import static com.caocao.bestmarket.Product.ViewListProductActivity.lvProductActi;

public class ProductInformationActivity extends AppCompatActivity {

    TextView txtShowNameProduct,txtShowDescription,txtDateCreate,txtShowCategory,txtShowPrice;
    ImageView imageShowProduct;
    Product product;

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
        product = (Product) lvProduct.getItemAtPosition(position);
        addControl();
        addEvent();

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
