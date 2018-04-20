package com.caocao.bestmarket.Product;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.caocao.bestmarket.Adapter.ArrayAdapterProduct;
import com.caocao.bestmarket.Model.Product;
import com.caocao.bestmarket.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ViewListProduct.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ViewListProduct#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewListProduct extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public static ListView lvProduct;
    ArrayList<Product> arrayProduct;
    ArrayAdapterProduct adapterProduct;


    public ViewListProduct() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewListProduct.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewListProduct newInstance(String param1, String param2,String param3) {
        ViewListProduct fragment = new ViewListProduct();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString("sanpham", param3);
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
        View v = inflater.inflate(R.layout.fragment_view_list_product, container, false);
        addControl(v);
        addEvent();
        return v;
    }



    private void addControl(View v) {
        lvProduct = v.findViewById(R.id.lvProduct);
        CreateDatabaseForTest();
    }

    private void addEvent() {
        lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getContext(),ProductInformationActivity.class);
                //based on item add info to intent
                intent.putExtra("sanpham",i);
                getActivity().startActivity(intent);

            }
        });

    }
    void CreateDatabaseForTest(){

        arrayProduct = new ArrayList<Product>();
        Date currentTime = Calendar.getInstance().getTime();
        //San pham thu nhat
        Drawable myDrawable = ResourcesCompat.getDrawable(getContext().getResources(), R.drawable.xemay, null);
        Bitmap myLogo = ((BitmapDrawable) myDrawable).getBitmap();
        arrayProduct.add(new Product("Xe máy","Xe đã dùng 2 năm",currentTime,1000,"Xe", myLogo));
        adapterProduct = new ArrayAdapterProduct(getContext(),R.layout.item_product,arrayProduct);

        //San pham thu 2
        myDrawable = ResourcesCompat.getDrawable(getContext().getResources(), R.drawable.maytinh, null);
        myLogo = ((BitmapDrawable) myDrawable).getBitmap();
        arrayProduct.add(new Product("Máy tính để bàn","Máy tính đễ bàn hãng acer, đã dùng được 2 năm, hiện đang còn rất tốt",currentTime,1000,"Điện tử", myLogo));
        adapterProduct = new ArrayAdapterProduct(getContext(),R.layout.item_product,arrayProduct);

        myDrawable = ResourcesCompat.getDrawable(getContext().getResources(), R.drawable.sonmoi, null);
        myLogo = ((BitmapDrawable) myDrawable).getBitmap();
        arrayProduct.add(new Product("Son môi","Son môi nhập ngoại, hàng chính hãng, giúp môi giữ ẫm, màu hồng tươi sáng",currentTime,1000,"Quần áo", myLogo));
        adapterProduct = new ArrayAdapterProduct(getContext(),R.layout.item_product,arrayProduct);

        myDrawable = ResourcesCompat.getDrawable(getContext().getResources(), R.drawable.vaydep, null);
        myLogo = ((BitmapDrawable) myDrawable).getBitmap();
        arrayProduct.add(new Product("Váy dạ hội","Hàng mẫu mã mới, phù hợp cho các chị em",currentTime,1000,"Quần áo", myLogo));
        adapterProduct = new ArrayAdapterProduct(getContext(),R.layout.item_product,arrayProduct);

        myDrawable = ResourcesCompat.getDrawable(getContext().getResources(), R.drawable.iphone6, null);
        myLogo = ((BitmapDrawable) myDrawable).getBitmap();
        arrayProduct.add(new Product("Điện thoại iphone","Điện thoại mới mua, do cần tiền nên mình bán lại giá giảm 20% so với giá gốc",currentTime,1000,"Điện thoại", myLogo));
        adapterProduct = new ArrayAdapterProduct(getContext(),R.layout.item_product,arrayProduct);

        lvProduct.setAdapter(adapterProduct);
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
