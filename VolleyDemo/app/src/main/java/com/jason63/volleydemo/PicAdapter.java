package com.jason63.volleydemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.LruCache;
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
    List<Item> mList ;
    public PicAdapter(Context context, List<Item> list){
        super(context, R.layout.item, list ) ;
        mList = list ;
        RequestQueue mQueue = Volley.newRequestQueue(context) ;
        imageLoader = new ImageLoader(mQueue, new BitmapCache());

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String str = mList.get(position).getTitle() ;
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item,parent,false) ;
        TextView tv = view.findViewById(R.id.txt) ;
        NetworkImageView nv = view.findViewById(R.id.img) ;

        nv.setDefaultImageResId(R.drawable.ic_launcher_background);
        nv.setErrorImageResId(R.drawable.ic_launcher_background);
        nv.setImageUrl(mList.get(position).getImg(),
                imageLoader);
        tv.setText(str);
        return view;
    }
    public class BitmapCache implements ImageLoader.ImageCache{
        private LruCache<String, Bitmap> mCache ;
        public BitmapCache(){
            int maxSize = 10*1024*1024 ;
            mCache = new LruCache<String, Bitmap>(maxSize){
                @Override
                protected int sizeOf(String key, Bitmap value) {
                    return value.getRowBytes()*value.getHeight() ;
                }
            };
        }

        @Override
        public Bitmap getBitmap(String s) {
            return mCache.get(s) ;
        }

        @Override
        public void putBitmap(String s, Bitmap bitmap) {
            mCache.put(s,bitmap) ;
        }
    }
}
