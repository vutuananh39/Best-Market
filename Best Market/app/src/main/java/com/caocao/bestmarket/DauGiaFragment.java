package com.caocao.bestmarket;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;

public class DauGiaFragment extends Fragment  {
    Activity context;

    int[] images = {R.drawable.xa2, R.drawable.iphone, R.drawable.note8, R.drawable.xa2};
    String[] info = {"SONY XPERIA XA2 MÀU XANH\nMàu sắc:\tXanh\nNhà sản xuất:\tSony\nXuất xứ:\tThái Lan\nHệ điều hành:\tAndroid 8.0\nChipset:\tQualcomm Snapdragon 630\nCPU:\tOcta-core 2.2 GHz\nRAM:\t",
                    "IPHONE X\n" +
                        "Thiết kế: Nguyên khối\n" +
                        "\n" +
                        "Màn hình: Super AMOLED capacitive touchscreen, 5.8 inch HD\n" +
                        "\n" +
                        "Camera Trước/Sau: 7MP/ 2 camera 12MP\n" +
                        "\n" +
                        "CPU: Apple A11 Bionic 6 nhân\n" +
                        "\n" +
                        "Bộ Nhớ: 256GB\n" +
                        "\n" +
                        "RAM: 3GB\n" +
                        "\n" +
                        "SIM: 1 Nano SIM\n" +
                        "\n" +
                        "Tính năng: Chống nước, chống bụi, Face ID",
                    "Samsung Galaxy Note 8\n" +
                            "Kích thước màn hình:\t6.3 inches\n" +
                            "Độ phân giải màn hình:\t1440 x 2960 pixels\n" +
                            "Hệ điều hành:\tAndroid\n" +
                            "Phiên bản hệ điều hành:\t7.1.1 (Nougat), có thể nâng cấp lên Android 8.0 (Oreo)\n" +
                            "Chipset:\tSamsung Exynos 9 Octa 8895\n" +
                            "CPU:\t4x 2.3 GHz Exynos M2 Mongoose & 4x 1.7 GHz ARM Cortex-A53\n" +
                            "GPU:\tMali-G71 MP20\n" +
                            "Khe cắm thẻ nhớ:\tmicroSD, lên đến 256 GB\n" +
                            "Bộ nhớ:\t64 GB, 6 GB RAM\n" +
                            "Camera sau:\t12 MP (f/1.7, 26mm, 1/2.5\", 1.4 µm, Dual Pixel PDAF) + 12MP (f/2.4, 52mm, 1/3.6\", 1 µm, AF), OIS, tự động lấy nét nhận diện theo giai đoạn, zoom quang học 2x, LED flash\n" +
                            "Camera trước:\t8 MP (f/1.7, 25mm, 1/3.6\", 1.22 µm), tự động lấy nét, 1440p@30fps, video call kép, HDR tự động",
            "Android Donut"};
    String[] minPrice = {"6.000.000đ", "20.000.000 ₫", "10.000.000", "1.6"};
    String[] luotmua = {"568", "13", "85", "16"};

    ListView lView;

    ListAdapter lAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_daugia, container, false);

        String txtminpriceNhap = getActivity().getIntent().getStringExtra("minprice");
        String txtinfospNhap = getActivity().getIntent().getStringExtra("info");
        int imageview=getActivity().getIntent().getIntExtra("image", 0);
        /*
        images[5]=imageview;
        minPrice[5]=txtminpriceNhap;
        info[5]=txtinfospNhap;
        luotmua[5]="0";
*/
        lView = (ListView) view.findViewById(R.id.listviewProductDaugia);
        lAdapter = new ListAdapter(this.getActivity(), info,minPrice,luotmua, images);
        lView.setAdapter(lAdapter);


        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int templateimage=images[i];
                String templateinfo=info[i].toString();
                String templateminprice=minPrice[i].toString();
                String templateluotmua=luotmua[i].toString();
                Intent intent=new Intent(getActivity(), ProductDauGia.class);

                intent.putExtra("image", templateimage);
                intent.putExtra("info", templateinfo);
                intent.putExtra("minprice", templateminprice);
                intent.putExtra("luotmua", templateluotmua);
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

}