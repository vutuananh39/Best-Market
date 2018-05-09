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


public class ListAdapter extends BaseAdapter {

    Context context;
    private final String [] info;
    private final String [] minPrice;
    private final String [] luotmua;
    private final int [] images;

    public ListAdapter(Context context, String [] info, String [] minPrice,  String [] luotmua,  int [] images){
        //super(context, R.layout.single_list_app_item, utilsArrayList);
        this.context = context;
        this.minPrice = minPrice;
        this.info = info;
        this.luotmua = luotmua;
        this.images = images;
    }

    @Override
    public int getCount() { return info.length;
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
            convertView = inflater.inflate(R.layout.spdaugia, parent, false);
            viewHolder.txtInfo = (TextView) convertView.findViewById(R.id.txtspdgInfo);
            viewHolder.txtLuotmua = (TextView) convertView.findViewById(R.id.txtspdgLuot);
            viewHolder.txtMinPrice = (TextView) convertView.findViewById(R.id.txtspdgMinPrice);
            viewHolder.img = (ImageView) convertView.findViewById(R.id.imgspdg);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.txtInfo.setText(info[position]);
        viewHolder.txtMinPrice.setText(minPrice[position]);
        viewHolder.txtLuotmua.setText(luotmua[position]);
        viewHolder.img.setImageResource(images[position]);

        return convertView;
    }

    private static class ViewHolder {

        TextView txtInfo;
        TextView txtMinPrice;
        TextView txtLuotmua;
        ImageView img;

    }

}