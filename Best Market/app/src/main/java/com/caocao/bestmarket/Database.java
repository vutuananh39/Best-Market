package com.caocao.bestmarket;

import com.caocao.bestmarket.Model.Product;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Database {
    //khai báo mdata lưu dữ liệu
    DatabaseReference mdata= FirebaseDatabase.getInstance().getReference();//khởi tạo mdta trỏ đến node gốc;
    public Database() {
    }
    public Database(DatabaseReference mdata) {
        this.mdata = mdata;
    }
    //hàm liên quan user ##############################################
    public void addUser(User user){
        String id=mdata.push().getKey();
        user.Id=id;
        mdata.child("User").child(id).setValue(user);
        mdata.child("CartBuy").child(id).setValue(" ");
        mdata.child("CartSell").child(id).setValue(" ");
    }
    public void removeUser(String userId){
        mdata.child("User").child(userId).setValue(null);
    }
    public void updateTentkUser(User user, String tenTK )
    {
        mdata.child("User").child(user.Id).child("TenTk").setValue(tenTK);
    }
    public void updatePasswordUser(User user,String password)
    {
        mdata.child("User").child(user.Id).child("PassWord").setValue(password);
    }
    public void updateNameUser(User user,String hoten)
    {
        mdata.child("User").child(user.Id).child("HoTen").setValue(hoten);
    }
    public void updatePhoneUser(User user,String sdt)
    {
        mdata.child("User").child(user.Id).child("Sdt").setValue(sdt);

    }
    public void updateEmailUser(User user,String email)
    {
        mdata.child("User").child(user.Id).child("Email").setValue(email);
    }
    public void updateAddressUser(User user,String dc)
    {
        mdata.child("User").child(user.Id).child("Dc").setValue(dc);

    }
    public void getAllDataUser( final ArrayList<User> mang_User){
        mdata.child("User").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                User user=dataSnapshot.getValue(User.class);
                mang_User.add(user);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    //hàm liên quan product ############################################
    public void addProduct(ProductNormal product){
        String id=mdata.push().getKey();
        product.Id=id;
        mdata.child("Product").child(id).setValue(product);
    }
    public void removeProduct(String productId){
        mdata.child("Product").child(productId).setValue(null);
    }
    public void updateCountProduct(ProductNormal product,int count)
    {
        mdata.child("Product").child(product.Id).child("Count").setValue(count);

    }
    public void updateInfoProduct(ProductNormal product, String Info_Product)
    {
        mdata.child("Product").child(product.Id).child("Info_Product").setValue(Info_Product);

    }
    public void updatePriceProduct(ProductNormal product,int Price)
    {
        mdata.child("Product").child(product.Id).child("Price").setValue(Price);

    }
    //hàm liên quan đến Cart
    public void addCartBuy(String userId, ProductNormal product){
        mdata.child("CartBuy").child(userId).push().setValue(product);
    }
    public void removeCartMua(String userId, ProductNormal product){
        mdata.child("CartSell").child(userId).setValue(null);
    }
    public void addCartSell(String userId, ProductNormal product){
        mdata.child("CartSell").child(userId).push().setValue(product);
    }
    //hàm liên quan tới đấu giá
    public void addAuction(Product_DG productDg,int value){

    }
    //hàm liên quan hình ảnh
    public void pushHinh(Hinhanh hinhanh) {
        mdata.child("HinhAnh").push().setValue(hinhanh, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if(databaseError==null){
                    //Toast.makeText(MainActivity.this, "save data success", Toast.LENGTH_SHORT).show();
                }else {
                   // Toast.makeText(MainActivity.this, "save data fail", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    /*public void getAllPicture(final ArrayList<Hinhanh> mang_hinh, final adapterHinh adapterHinh){
        mdata.child("HinhAnh").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Hinhanh hinh=dataSnapshot.getValue(Hinhanh.class);
                mang_hinh.add(hinh);
                adapterHinh.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }*/
}
