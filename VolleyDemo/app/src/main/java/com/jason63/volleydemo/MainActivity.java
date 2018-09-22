package com.jason63.volleydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String[] nameList = {
            "一个叫欧维的男人决定去死" ,
            "重庆森林" ,
            "无耻之徒（美版）",
            "Game of thrones Season 7 " ,
            "公民凯恩" ,
            "Mission Impossible 6" ,
            "夜行者" ,
            "the fault in our stars" ,
            "Ferris Bueller's Day Off" ,
            "无间道" ,
            "辛普森一家",
            "La La Land" ,
            "Balance",
            "飞哥和小佛" ,            "夏日大作战",
            "夏日大作战",
            "Fullmetal Alchemist",
            "Swiss Army Man",
            "Black Panther",
            "Avengers:Infinity War",
            "头号玩家"
    } ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> arrList = new ArrayList<>();
        Collections.addAll(arrList, nameList) ;

        ListView listView = findViewById(R.id.list1) ;
        PicAdapter adapter = new PicAdapter(this, arrList) ;
        listView.setAdapter(adapter);
    }
}
