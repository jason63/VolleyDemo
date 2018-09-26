package com.jason63.volleydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {

    String[] nameList =new String[25];
//            = {"一个叫欧维的男人决定去死" , "重庆森林" , "无耻之徒（美版）", "Game of thrones Season 7 " , "公民凯恩" , "Mission Impossible 6" , "夜行者" , "the fault in our stars" , "Ferris Bueller's Day Off" , "无间道" , "辛普森一家", "La La Land" , "Balance", "飞哥和小佛" , "夏日大作战", "Fullmetal Alchemist", "Swiss Army Man", "Black Panther", "Avengers:Infinity War", "头号玩家"
//    } ;
    String[] imgList =new String[25];
//            = {"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2406624993.webp" , "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p792381411.webp" , "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2377449669.webp" , "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2462245619.webp" , "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2111252759.webp" , "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2529365085.webp" , "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2203693875.webp" , "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2197680919.webp" , "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p1382118133.webp" , "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2233971046.webp" , "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p1947152148.webp" , "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2425658570.webp" , "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2454473678.webp" , "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2187194609.webp" , "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p1031772057.webp" , "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2225808366.webp" , "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2328680655.webp" , "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2512123434.webp" , "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2517753454.webp" , "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2516578307.webp"
//    } ;
    String[] str = new String[25] ;
    PicAdapter adapter ;
    ArrayList<Item> itemList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getData();


        ListView listView = findViewById(R.id.list1) ;
         adapter = new PicAdapter(this, itemList) ;
         listView.setAdapter(adapter);
    }
    private void getData(){
        RequestQueue rq = Volley.newRequestQueue(this) ;
        StringRequest sr = new StringRequest("https://movie.douban.com/top250",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Pattern p=Pattern.compile("<img width(.*?)>");
                        Matcher m=p.matcher(s);
                        int j = 0 ;
                        while(m.find()&& j< 25)  {
                            str[j++] = m.group() ;
                        }
                        for(int i = 0; i< 25; i++){
                            if(!TextUtils.isEmpty( str[i] )) {
                                String[] splits = str[i].split("\"");
                                imgList[i] = splits[5];
                                nameList[i] = splits[3];
                                Log.i("html----", imgList[i]+" "+nameList[i]) ;
                            }
                        }

                        for(int i = 0; i< nameList.length; i++){
                            Item item = new Item(nameList[i], imgList[i]) ;
                            itemList.add(item) ;
                        }
                        adapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.i("main----","errorListening----") ;
                    }
                }) ;
        rq.add(sr) ;

    }
}
