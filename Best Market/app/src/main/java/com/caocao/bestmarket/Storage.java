package com.caocao.bestmarket;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

public class Storage {
    // Create a storage reference from our app
     StorageReference storageRef = FirebaseStorage.getInstance().getReferenceFromUrl("gs://final-243ad.appspot.com");
     int REQUEST_CODE_IMG=1;
    public Storage() {
    }

    public Storage(StorageReference storageRef, int REQUEST_CODE_IMG) {
        this.storageRef = storageRef;
        this.REQUEST_CODE_IMG = REQUEST_CODE_IMG;
    }

    public void upPicture(Button btnSave, final ImageView imghinh, final Database mdata, final String idProduct){
        //up ảnh
        //lấy tg hệ thống đặt tên để k trùng ảnh
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar= Calendar.getInstance();
                // Create a reference to "mountains.jpg"
                StorageReference mountainsRef = storageRef.child("image"+ calendar.getTimeInMillis()+".png");
                // Get the data from an ImageView as bytes
                imghinh.setDrawingCacheEnabled(true);
                imghinh.buildDrawingCache();
                Bitmap bitmap = imghinh.getDrawingCache();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] data = baos.toByteArray();

                UploadTask uploadTask = mountainsRef.putBytes(data);
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        //taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                        Uri downloadUrl = taskSnapshot.getDownloadUrl();//link chứa hình
                        //tạo node mới chứa link hình trên database
                        Hinhanh hinhanh=new Hinhanh(idProduct, String.valueOf(downloadUrl));
                        mdata.pushHinh(hinhanh);
                    }
                });
            }
        });
    }
}
