package com.caocao.bestmarket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;

public class DauGiaFragment extends Fragment  {
    Activity context;

    int[] images = {R.drawable.xa2, R.drawable.iphone, R.drawable.note8, R.drawable.xa2, R.drawable.xa2, R.drawable.xa2, R.drawable.xa2, R.drawable.xa2, R.drawable.xa2, R.drawable.xa2, R.drawable.xa2, R.drawable.xa2, R.drawable.xa2, R.drawable.xa2};
    String[] info = {"Android Alpha", "Android Beta", "Android Cupcake", "Android Donut", "Android Eclair", "Android Froyo", "Android Gingerbread", "Android Honeycomb", "Android Ice Cream Sandwich", "Android JellyBean", "Android Kitkat", "Android Lollipop", "Android Marshmallow", "Android Nougat"};
    String[] minPrice = {"1.0", "1.1", "1.5", "1.6", "2.0", "2.2", "2.3", "3.0", "4.0", "4.1", "4.4", "5.0", "6.0", "7.0"};
    String[] luotmua = {"1.0", "1.1", "1.5", "1.6", "2.0", "2.2", "2.3", "3.0", "4.0", "4.1", "4.4", "5.0", "6.0", "7.0"};

    ListView lView;

    ListAdapter lAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_daugia, container, false);
        lView = (ListView) view.findViewById(R.id.listviewProductDaugia);
        lAdapter = new ListAdapter(this.getActivity(), info,minPrice,luotmua, images);
        lView.setAdapter(lAdapter);
        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //create an Intent object
                Intent intent=new Intent(getActivity(), ProductDauGia.class);
                //add data to the Intent object
                intent.putExtra("some", "somedata");
                //start the second activity
                startActivity(intent);

            }
        });

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.icon_daugia, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.bar_daugia){
            //create an Intent object
            Intent intent=new Intent(getActivity(),DangDauGia.class);
            //add data to the Intent object
            intent.putExtra("some", "somedata");
            //start the second activity
            startActivity(intent);
        }
        return false;
    }

/*
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_daugia, container, false);
        Button btnDauGia=(Button) view.findViewById(R.id.btnDauGiafrg);
        Button btnVaoDauGia=(Button) view.findViewById(R.id.btnVaoDauGiafrg);
        btnVaoDauGia.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                //create an Intent object
                Intent intent=new Intent(getActivity(), ListProductDauGia.class);
                //add data to the Intent object
                intent.putExtra("some", "somedata");
                //start the second activity
                startActivity(intent);
            }

        });
        btnDauGia.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                //create an Intent object
                Intent intent=new Intent(getActivity(), DangDauGia.class);
                //add data to the Intent object
                intent.putExtra("some", "somedata");
                //start the second activity
                startActivity(intent);
            }

        });

        return view;
    }
*/

}