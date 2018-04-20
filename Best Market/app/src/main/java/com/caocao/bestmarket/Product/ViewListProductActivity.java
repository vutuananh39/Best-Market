package com.caocao.bestmarket.Product;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.caocao.bestmarket.Adapter.ArrayAdapterProduct;
import com.caocao.bestmarket.Model.Product;
import com.caocao.bestmarket.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ViewListProductActivity extends AppCompatActivity {

    public static ListView lvProductActi;
    ArrayList<Product> arrayProduct;
    ArrayAdapterProduct adapterProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list_product);
        addControl();
        addEvent();
    }

    private void addControl() {
        lvProductActi = findViewById(R.id.lvProduct);
        CreateDatabaseForTest();
    }

    private void addEvent() {
        lvProductActi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getApplicationContext(),ProductInformationActivity.class);
                //based on item add info to intent
                intent.putExtra("sanpham",i);
                startActivity(intent);
            }
        });

    }
    void CreateDatabaseForTest(){

        arrayProduct = new ArrayList<Product>();
        Date currentTime = Calendar.getInstance().getTime();
        //San pham thu nhat
        Drawable myDrawable = ResourcesCompat.getDrawable(this.getApplicationContext().getResources(), R.drawable.xemay, null);
        Bitmap myLogo = ((BitmapDrawable) myDrawable).getBitmap();
        arrayProduct.add(new Product("Xe máy","Xe đã dùng 2 năm",currentTime,1000,"Xe", myLogo));
        adapterProduct = new ArrayAdapterProduct(this,R.layout.item_product,arrayProduct);

        //San pham thu 2
        myDrawable = ResourcesCompat.getDrawable(this.getApplicationContext().getResources(), R.drawable.maytinh, null);
        myLogo = ((BitmapDrawable) myDrawable).getBitmap();
        arrayProduct.add(new Product("Máy tính để bàn","Máy tính đễ bàn hãng acer, đã dùng được 2 năm, hiện đang còn rất tốt",currentTime,1000,"Điện tử", myLogo));
        adapterProduct = new ArrayAdapterProduct(this,R.layout.item_product,arrayProduct);

        myDrawable = ResourcesCompat.getDrawable(this.getApplicationContext().getResources(), R.drawable.sonmoi, null);
        myLogo = ((BitmapDrawable) myDrawable).getBitmap();
        arrayProduct.add(new Product("Son môi","Son môi nhập ngoại, hàng chính hãng, giúp môi giữ ẫm, màu hồng tươi sáng",currentTime,1000,"Quần áo", myLogo));
        adapterProduct = new ArrayAdapterProduct(this,R.layout.item_product,arrayProduct);

        myDrawable = ResourcesCompat.getDrawable(this.getApplicationContext().getResources(), R.drawable.vaydep, null);
        myLogo = ((BitmapDrawable) myDrawable).getBitmap();
        arrayProduct.add(new Product("Váy dạ hội","Hàng mẫu mã mới, phù hợp cho các chị em",currentTime,1000,"Quần áo", myLogo));
        adapterProduct = new ArrayAdapterProduct(this,R.layout.item_product,arrayProduct);

        myDrawable = ResourcesCompat.getDrawable(this.getApplicationContext().getResources(), R.drawable.iphone6, null);
        myLogo = ((BitmapDrawable) myDrawable).getBitmap();
        arrayProduct.add(new Product("Điện thoại iphone","Điện thoại mới mua, do cần tiền nên mình bán lại giá giảm 20% so với giá gốc",currentTime,1000,"Điện thoại", myLogo));
        adapterProduct = new ArrayAdapterProduct(this,R.layout.item_product,arrayProduct);

        lvProductActi.setAdapter(adapterProduct);
    }
}
