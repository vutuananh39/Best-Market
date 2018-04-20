package com.caocao.bestmarket.Product;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.caocao.bestmarket.Model.Product;
import com.caocao.bestmarket.R;

import static com.caocao.bestmarket.Product.ViewListProduct.lvProduct;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProductInformation#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductInformation extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    TextView txtShowNameProduct,txtShowDescription,txtDateCreate,txtShowCategory,txtShowPrice;
    ImageView imageShowProduct;
    Product product;

    public ProductInformation() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductInformation.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductInformation newInstance(String param1, String param2) {
        ProductInformation fragment = new ProductInformation();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_product_information, container, false);
        addControl(v);
        addEvent();
        return v;
    }


    void addControl(View v){
        txtShowNameProduct = v.findViewById(R.id.txtShowNameProduct);
        txtShowDescription = v.findViewById(R.id.txtShowDescription);
        txtDateCreate = v.findViewById(R.id.txtDateCreate);
        txtShowCategory = v.findViewById(R.id.txtShowCategory);
        imageShowProduct = v.findViewById(R.id.imageShowProduct);
        txtShowPrice = v.findViewById(R.id.txtPrice);

        int position =0 ;
        String getArgument = getArguments().getString("sanpham");
        position = Integer.parseInt(getArgument);
        product = (Product) lvProduct.getItemAtPosition(position);

    }
    void addEvent(){
        txtShowNameProduct.setText(product.getName());
        txtShowDescription.setText(product.getDescription());
        txtDateCreate.setText("Hello");
        txtShowCategory.setText(product.getCategory());
//        Set ảnh
        imageShowProduct.setImageBitmap(product.getPhoto());
        txtShowPrice.setText(product.getPrice()+"VND");
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
