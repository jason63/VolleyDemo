package com.jason63.volleydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String[] nameList = {"一个叫欧维的男人决定去死" , "重庆森林" , "无耻之徒（美版）", "Game of thrones Season 7 " , "公民凯恩" , "Mission Impossible 6" , "夜行者" , "the fault in our stars" , "Ferris Bueller's Day Off" , "无间道" , "辛普森一家", "La La Land" , "Balance", "飞哥和小佛" , "夏日大作战", "Fullmetal Alchemist", "Swiss Army Man", "Black Panther", "Avengers:Infinity War", "头号玩家"
    } ;
    String[] imgList ={"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2406624993.webp" , "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p792381411.webp" , "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2377449669.webp" , "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2462245619.webp" , "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2111252759.webp" , "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2529365085.webp" , "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2203693875.webp" , "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2197680919.webp" , "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p1382118133.webp" , "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2233971046.webp" , "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p1947152148.webp" , "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2425658570.webp" , "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2454473678.webp" , "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2187194609.webp" , "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p1031772057.webp" , "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2225808366.webp" , "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2328680655.webp" , "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2512123434.webp" , "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2517753454.webp" , "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2516578307.webp"
    } ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Item> itemList = new ArrayList<>();
        for(int i = 0; i< nameList.length; i++){
            Item item = new Item(nameList[i], imgList[i]) ;
            itemList.add(item) ;
        }

        ListView listView = findViewById(R.id.list1) ;
        PicAdapter adapter = new PicAdapter(this, itemList) ;
        listView.setAdapter(adapter);
    }
}
