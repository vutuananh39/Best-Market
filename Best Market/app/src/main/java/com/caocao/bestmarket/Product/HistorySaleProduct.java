package com.caocao.bestmarket.Product;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.caocao.bestmarket.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HistorySaleProduct.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HistorySaleProduct#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistorySaleProduct extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView txthistoryTotalsale,txtAmountDientu,txtAmountQuanao,txtAmountMyPham,txtAmountXe,txtAmountHanggiadungvadoisong,txtAmountSach;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HistorySaleProduct() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HistorySaleProduct.
     */
    // TODO: Rename and change types and number of parameters
    public static HistorySaleProduct newInstance(String param1, String param2) {
        HistorySaleProduct fragment = new HistorySaleProduct();
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
        View v = inflater.inflate(R.layout.fragment_history_sale_product, container, false);
        addControl(v);
        addEvent();
        return v;
    }

    public void addControl(View v){
        txtAmountDientu =  v.findViewById(R.id.txtAmountDientu);
        txtAmountHanggiadungvadoisong = v.findViewById(R.id.txtAmountHanggiadungvadoisong);
        txthistoryTotalsale = v.findViewById(R.id.txthistoryTotalsale);
        txtAmountQuanao = v.findViewById(R.id.txtAmountQuanao);
        txtAmountMyPham = v.findViewById(R.id.txtAmountMyPham);
        txtAmountXe = v.findViewById(R.id.txtAmountXe);
        txtAmountSach =v.findViewById(R.id.txtAmountSach);

    }
    public void addEvent(){
        txthistoryTotalsale.setText("20");
        txtAmountDientu.setText("10");
        txtAmountQuanao.setText("1");
        txtAmountMyPham.setText("4");
        txtAmountXe.setText("1");
        txtAmountHanggiadungvadoisong.setText("2");
        txtAmountSach.setText("2");

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
