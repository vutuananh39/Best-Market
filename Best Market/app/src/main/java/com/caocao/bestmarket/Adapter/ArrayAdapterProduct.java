package com.caocao.bestmarket.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.caocao.bestmarket.Model.Product;
import com.caocao.bestmarket.R;

import java.util.List;

/**
 * Created by rongc on 3/12/2018.
 */

public class ArrayAdapterProduct  extends BaseAdapter{

    private Context context;
    private int layout;
    private List<Product> lvProduct;

    public ArrayAdapterProduct(Context context, int layout, List<Product> lvProduct) {
        this.context = context;
        this.layout = layout;
        this.lvProduct = lvProduct;
    }



    @Override
    public int getCount() {
        return lvProduct.size();
    }

    @Override
    public Object getItem(int i) {
        return lvProduct.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout,null);

        ImageView imageProduct = view.findViewById(R.id.imageItemProduct);
        TextView txtName = view.findViewById(R.id.txtItemName);
        TextView txtDescription = view.findViewById(R.id.txtItemDescription);

        TextView txtPrice = view.findViewById(R.id.txtPriceProduct);

        Product product = lvProduct.get(i);
        txtName.setText(product.getName());
        txtDescription.setText(product.getDescription());
        imageProduct.setImageBitmap(product.getPhoto());

        return view;
    }
}
