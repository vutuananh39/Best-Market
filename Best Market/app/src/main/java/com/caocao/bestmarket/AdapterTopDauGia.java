package com.caocao.bestmarket;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class AdapterTopDauGia extends BaseAdapter {

    Context context;
    private final String [] name;
    private final String [] price;


    public AdapterTopDauGia(Context context, String [] name, String [] price){
        //super(context, R.layout.single_list_app_item, utilsArrayList);
        this.context = context;
        this.name = name;
        this.price = price;

    }

    @Override
    public int getCount() { return name.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @NonNull
    @Override

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        ViewHolder viewHolder;

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.infonguoidaugia, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.txtnamenguoidaugia);
            viewHolder.txtPrice = (TextView) convertView.findViewById(R.id.txtgianguoidaugia);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.txtName.setText(name[position]);
        viewHolder.txtPrice.setText(price[position]);

        return convertView;
    }
    private static class ViewHolder {

        TextView txtName;
        TextView txtPrice;

    }

}