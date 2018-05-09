package com.caocao.bestmarket;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.caocao.bestmarket.Product.HistorySaleProductActivity;
import com.caocao.bestmarket.Product.PostProductActivity;
import com.caocao.bestmarket.Product.ViewListProduct;
import com.caocao.bestmarket.Product.ViewListProductActivity;
import com.caocao.bestmarket.myAccount.EditInfoActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private boolean check=false;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    NavigationView navigationView;
    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControl();
        addEvent();

        bottomNav = findViewById(R.id.bottom_navigationview);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new ViewListProduct()).commit();
        }

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    android.support.v4.app.Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.action_add:
                            selectedFragment = new ViewListProduct();
                            break;
                        case R.id.action_edit:
                            selectedFragment = new DauGiaFragment();
                            break;

                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };


    private void addControl() {
        navigationView = findViewById(R.id.nView);
        mDrawerLayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //
       setNavHeader(navigationView);
        //
    }
    private  void addEvent(){
        setupDrawerContent(navigationView);
    }
//    public  void selectItemDrawer(MenuItem menuItem){
//        android.support.v4.app.Fragment mFragement = null;
//        Class fragmentClass = null;
//        switch (menuItem.getItemId()){
//            case R.id.menu_trangchu:
//
//            case R.id.menu_dangbansanpham:
//                fragmentClass = PostProduct.class;
//                break;
//            case R.id.menu_danhsachsanpham:
//                fragmentClass = ViewListProduct.class;
//                break;
//            case R.id.menu_lichsuban:
//                fragmentClass = HistorySaleProduct.class;
//                break;
//        }
//        try{
//            mFragement = (android.support.v4.app.Fragment) fragmentClass.newInstance();
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.fragmentMain,mFragement).commit();
//        menuItem.setChecked(true);
//        setTitle(menuItem.getTitle());
//        mDrawerLayout.closeDrawers();
//
//    }
    public  void selectItemDrawerActivity(MenuItem menuItem){

        switch (menuItem.getItemId()){
            case R.id.menu_trangchu:
                break;
            case R.id.menu_dangbansanpham:
                Intent intent1 = new Intent(MainActivity.this,PostProductActivity.class);
                //based on item add info to intent
                startActivity(intent1);
                break;
            case R.id.menu_danhsachsanpham:
                Intent intent2 = new Intent(MainActivity.this, ViewListProductActivity.class);
                //based on item add info to intent
                startActivity(intent2);
                break;
            case R.id.menu_lichsuban:
                Intent intent3 = new Intent(MainActivity.this,HistorySaleProductActivity.class);
                //based on item add info to intent
                startActivity(intent3);
                break;

            case R.id.nav_change_info:
                Intent intent4 = new Intent(MainActivity.this, EditInfoActivity.class);
                startActivity(intent4);
                break;

        }


    }

    private void setupDrawerContent(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectItemDrawerActivity(item);
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setNavHeader(NavigationView navigationView){
        View hView =  navigationView.inflateHeaderView(R.layout.nav_header_main);
        final ImageView backgroundImage = hView.findViewById(R.id.background_image_view);
        final ImageView profileImage = hView.findViewById(R.id.profile_image_view);
        final TextView nameField = hView.findViewById(R.id.name_text_view);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user=null;
        if(auth!=null)
            user = auth.getCurrentUser();
        if(user== null)
            return;

        DatabaseReference myUserdRef = FirebaseDatabase.getInstance().getReference().child("Accounts").child(user.getUid());
        myUserdRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Object backgroundSnapshot = dataSnapshot.child("background_uri").getValue();
                if(backgroundSnapshot!=null){
                    String backgroundUri = backgroundSnapshot.toString();
                    if(backgroundUri!=null && !backgroundUri.equals("")){
                        Glide.with(backgroundImage.getContext())
                                .load(backgroundUri)
                                .into(backgroundImage);
                    }
                }

                Object profileSnapshot = dataSnapshot.child("image_uri").getValue();
                if(profileSnapshot!=null){
                    String profileUri = profileSnapshot.toString();
                    if(profileUri!=null && !profileUri.equals("")){
                        Glide.with(profileImage.getContext())
                                .load(profileUri)
                                .into(profileImage);
                    }
                }

                Object nameSnapshot = dataSnapshot.child("full_name").getValue();
                if(nameSnapshot!=null){
                    String name = nameSnapshot.toString();
                    if(name!=null && !name.equals("")){
                        nameField.setText(name);
                    }
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
