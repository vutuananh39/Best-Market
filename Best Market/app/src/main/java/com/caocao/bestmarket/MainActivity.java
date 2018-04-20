package com.caocao.bestmarket;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.caocao.bestmarket.Product.HistorySaleProduct;
import com.caocao.bestmarket.Product.HistorySaleProductActivity;
import com.caocao.bestmarket.Product.PostProduct;
import com.caocao.bestmarket.Product.ProductInformationActivity;
import com.caocao.bestmarket.Product.ViewListProduct;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControl();
        addEvent();
    }

    private void addControl() {
        navigationView = findViewById(R.id.nView);
        mDrawerLayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private  void addEvent(){
        setupDrawerContent(navigationView);
    }
    public  void selectItemDrawer(MenuItem menuItem){
        android.support.v4.app.Fragment mFragement = null;
        Class fragmentClass = null;
        switch (menuItem.getItemId()){
            case R.id.menu_trangchu:

            case R.id.menu_dangbansanpham:
                fragmentClass = PostProduct.class;
                break;
            case R.id.menu_danhsachsanpham:
                fragmentClass = ViewListProduct.class;
                break;
            case R.id.menu_lichsuban:
                fragmentClass = HistorySaleProduct.class;
                break;
        }
        try{
            mFragement = (android.support.v4.app.Fragment) fragmentClass.newInstance();

        }
        catch (Exception e){
            e.printStackTrace();
        }

        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragmentMain,mFragement).commit();
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        mDrawerLayout.closeDrawers();
        
    }
    public  void selectItemDrawerActivity(MenuItem menuItem){

        switch (menuItem.getItemId()){
            case R.id.menu_trangchu:
                break;
            case R.id.menu_dangbansanpham:
                 Intent intent1 = new Intent(getApplicationContext(),PostProduct.class);
                //based on item add info to intent
                startActivity(intent1);
                break;
            case R.id.menu_danhsachsanpham:
                Intent intent2 = new Intent(getApplicationContext(), ViewListProduct.class);
                //based on item add info to intent
                startActivity(intent2);
                break;
            case R.id.menu_lichsuban:
                Intent intent3 = new Intent(getApplicationContext(),HistorySaleProductActivity.class);
                //based on item add info to intent
                startActivity(intent3);
                break;

        }


    }

    private void setupDrawerContent(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectItemDrawer(item);
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
}
