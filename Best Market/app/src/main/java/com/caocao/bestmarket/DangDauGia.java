package com.caocao.bestmarket;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.DateFormat;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.view.Window;

import java.util.Calendar;

/**
 * Created by THONG on 4/12/2018.
 */

public class DangDauGia extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener  {
    private Dialog dialog;
    private Button btnDangDauGia;
    private ImageButton btnedittime;
    private EditText minGia;
    private TextView txtTime, txtInfo;
    private TextView txtDate;
    private ImageView imageview;

    int day, month, year, hour, min;
    int dayFinal, monthFinal, yearFinal, hourFinal, minFinal;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangdaugia);
        minGia=(EditText)findViewById(R.id.edtminprice);

        btnDangDauGia=(Button)findViewById(R.id.btnDauGia);
        txtInfo=(EditText) findViewById(R.id.edittxtMotasp);
        btnDangDauGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    if (Double.parseDouble(minGia.getText().toString()) >= 100000
                            && txtInfo.getText().toString()!=""
                            //&&checkDatetime(yearFinal, monthFinal,dayFinal,hourFinal, minFinal)
                            ) {

                        MainActivity.check=true;
                        int templateimage=imageview.getId();
                        String templateinfo=txtInfo.toString();
                        String templateminprice=minGia.toString();

                        Intent intent = new Intent(DangDauGia.this,MainActivity.class);

                        intent.putExtra("image", templateimage);
                        intent.putExtra("info", templateinfo);
                        intent.putExtra("minprice", templateminprice);
                        Toast.makeText(DangDauGia.this, "Đấu giá thành công", Toast.LENGTH_SHORT).show();
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
        btnaddIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();

            }
        });
        Bundle bundle=getIntent().getExtras();
        btnedittime=(ImageButton) findViewById(R.id.btnedittime);
        btnedittime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c=Calendar.getInstance();
                year=c.get(Calendar.YEAR);
                month=c.get(Calendar.MONTH);
                day=c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(DangDauGia.this, DangDauGia.this, year, month, day);
                datePickerDialog.show();
            }
        });
    }
    public  boolean checkDatetime(int year, int month, int day, int hour, int min){
        int year0, month0, day0, hour0, min0;
        Calendar c=Calendar.getInstance();
        year0=c.get(Calendar.YEAR);
        month0=c.get(Calendar.MONTH);
        day0=c.get(Calendar.DAY_OF_MONTH);
        hour0=c.get(Calendar.HOUR_OF_DAY);
        min0=c.get(Calendar.MINUTE);
        if (year<=year0) return false;
        else{
            if (month<month0) return false;
            else{
                if (day<day0) return false;
                else{
                    if (hour<hour0) return false;
                    else{
                        if (min<(min0+30)) return false;
                        Toast.makeText(DangDauGia.this, "Thời gian đấu giá phải hơn 30 phút", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
        return true;
    }
    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        yearFinal=i;
        monthFinal=i1+1;
        dayFinal=i2;
        Calendar c=Calendar.getInstance();
        hour=c.get(Calendar.HOUR_OF_DAY);
        min=c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog=new TimePickerDialog(DangDauGia.this, DangDauGia.this, hour, min, android.text.format.DateFormat.is24HourFormat(this));
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        hourFinal=i;
        minFinal=i1;
        txtTime=(TextView) findViewById(R.id.txttime);
        txtDate=(TextView) findViewById(R.id.txtdate);
        txtDate.setText(dayFinal+"/"+monthFinal+"/"+yearFinal);
        txtTime.setText(hourFinal+":"+minFinal);
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
        imageview.setBackgroundResource(0);
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

