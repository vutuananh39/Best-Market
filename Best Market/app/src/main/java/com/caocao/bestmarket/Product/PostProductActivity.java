package com.caocao.bestmarket.Product;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.caocao.bestmarket.R;

public class PostProductActivity extends AppCompatActivity {
    private Spinner spinnerCategory;
    EditText editNameProduct;
    EditText editDescription;
    ImageView imageProduct;
    Button btnPost;
    EditText editPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_product);
        addControl();
        addEvent();
    }

    private void addControl() {
        editNameProduct = findViewById(R.id.editNameProduct);
        editDescription = findViewById(R.id.editDescription);
        imageProduct = findViewById(R.id.imageProduct);
        btnPost = findViewById(R.id.btnPost);
        editPrice = findViewById(R.id.editprice);

        spinnerCategory = findViewById(R.id.spinnerCategory);
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.array_category_product));
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(categoryAdapter);



    }
    private void addEvent() {

    }

}
