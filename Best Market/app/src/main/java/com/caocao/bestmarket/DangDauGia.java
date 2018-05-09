package com.caocao.bestmarket;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.app.Dialog;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Window;

/**
 * Created by THONG on 4/12/2018.
 */

public class DangDauGia extends AppCompatActivity   {
    private Dialog dialog;
    private Button btnDangDauGia;
    private EditText minGia;
    private EditText giodaugia;
    private EditText phutdaugia;
    private ImageView imageview;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangdaugia);
        minGia=(EditText)findViewById(R.id.edtminprice);
        giodaugia=(EditText) findViewById(R.id.txthour);
        phutdaugia=(EditText) findViewById(R.id.txtmin);
        btnDangDauGia=findViewById(R.id.btnDauGia);
        //final String txtMinGia=minGia.getText().toString();
        btnDangDauGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    if (Double.parseDouble(minGia.getText().toString()) >= 100000
                            && Double.parseDouble(giodaugia.getText().toString())<=24
                            &&  Double.parseDouble(giodaugia.getText().toString())>=0
                            &&  Double.parseDouble(phutdaugia.getText().toString())>=0
                            &&  Double.parseDouble(phutdaugia.getText().toString())<=60

                            ) {
                        Toast.makeText(DangDauGia.this, "Đấu giá thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DangDauGia.this, ListProductDauGia.class);
                        startActivity(intent);
                    }
                    else
                        Toast.makeText(DangDauGia.this, "Đấu giá thất bại", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(DangDauGia.this, "Đấu giá thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageButton btnaddIMG=(ImageButton) findViewById(R.id.btnaddIMG);;
        //btnaddIMG.setClickable(true);
        btnaddIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();

            }
        });/*
        Button btngallery=(Button) findViewById(R.id.btngallery);

        btngallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePicture, 0);
            }
        });
        Button btncamera=(Button) findViewById(R.id.btncamera);
        btncamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto , 1);
            }
        });*/

/*
        BottomNavigationView bottomNavigationView= (BottomNavigationView) findViewById(R.id.bottom_navigationview);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.action_add:
                        Toast.makeText(DangDauGia.this,"Dau Gia Nao Moi Nguoi", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(DangDauGia.this, StartDauGia.class);
                        startActivity(intent);
                        break;
                    case R.id.action_edit:
                        Toast.makeText(DangDauGia.this,"Action Edit Clicked", Toast.LENGTH_SHORT).show();
                        break;

                }
                return true;
            }
        });*/
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

    public void showDialog() {
        dialog = new Dialog(DangDauGia.this);
        //dialog.setTitle(null);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_addcamera);
        dialog.show();
        Button btngallery=(Button) dialog.findViewById(R.id.btngallery);

        btngallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto , 1);
            }
        });
        Button btncamera=(Button) dialog.findViewById(R.id.btncamera);
        btncamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePicture, 0);

            }
        });

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        imageview=(ImageView) findViewById(R.id.btnaddIMG);
        imageview.setImageDrawable(null);
        imageview.setScaleType(ImageView.ScaleType.CENTER_CROP);
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            case 0:
                if(resultCode == RESULT_OK){

                    //Uri selectedImage = imageReturnedIntent.getData();
                    //imageview.setImageURI(selectedImage);

                    Bitmap thumbnail = (Bitmap) imageReturnedIntent.getExtras().get("data");
                    imageview.setImageBitmap(thumbnail);
                }

                break;
            case 1:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    String[] filePathColumn = { MediaStore.Images.Media.DATA };
                    Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    cursor.close();
                    imageview.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                }
                break;
        }
        dialog.dismiss();
    }


                    //Toast.makeText(DangDauGia.this, "Action Add Clicked", Toast.LENGTH_SHORT).show();


}

