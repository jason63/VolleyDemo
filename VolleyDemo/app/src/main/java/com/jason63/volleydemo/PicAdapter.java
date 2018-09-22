package com.jason63.volleydemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import java.util.List;

/**
 * Created by Jasonli on 2018/9/22;
 */
public class PicAdapter extends ArrayAdapter {
    ImageLoader imageLoader ;
    String[] imgList ={
            "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2406624993.webp" ,
            "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p792381411.webp" ,
            "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2377449669.webp" ,
            "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2462245619.webp" ,
            "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2111252759.webp" ,
            "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2529365085.webp" ,
            "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2203693875.webp" ,
            "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2197680919.webp" ,
            "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p1382118133.webp" ,
            "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2233971046.webp" ,
            "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p1947152148.webp" ,
            "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2425658570.webp" ,
            "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2454473678.webp" ,
            "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2187194609.webp" ,
            "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p1031772057.webp" ,
            "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2225808366.webp" ,
            "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2328680655.webp" ,
            "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2512123434.webp" ,
            "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2517753454.webp" ,
            "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2516578307.webp"
    } ;
    public PicAdapter(Context context, List<String> list){
        super(context, R.layout.item, list ) ;
        RequestQueue mQueue = Volley.newRequestQueue(context) ;
        imageLoader = new ImageLoader(mQueue, new ImageLoader.ImageCache() {
            @Override
            public void putBitmap(String url, Bitmap bitmap) {
            }

            @Override
            public Bitmap getBitmap(String url) {
                return null;
            }
        });

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String str = (String)getItem(position) ;
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item,parent,false) ;
        TextView tv = view.findViewById(R.id.txt) ;
        NetworkImageView nv = view.findViewById(R.id.img) ;

        nv.setDefaultImageResId(R.drawable.ic_launcher_background);
        nv.setErrorImageResId(R.drawable.ic_launcher_background);
        nv.setImageUrl(imgList[position],
                imageLoader);
        tv.setText(str);
        return view;
    }
}
