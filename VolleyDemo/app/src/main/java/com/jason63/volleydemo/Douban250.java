package com.jason63.volleydemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Jasonli on 2018/9/27;
 */
public class Douban250 extends Activity {
    String[] nameList =new String[25];
    //            = {"一个叫欧维的男人决定去死" , "重庆森林" , "无耻之徒（美版）", "Game of thrones Season 7 " , "公民凯恩" , "Mission Impossible 6" , "夜行者" , "the fault in our stars" , "Ferris Bueller's Day Off" , "无间道" , "辛普森一家", "La La Land" , "Balance", "飞哥和小佛" , "夏日大作战", "Fullmetal Alchemist", "Swiss Army Man", "Black Panther", "Avengers:Infinity War", "头号玩家"
//    } ;
    String[] imgList =new String[25];
    //            = {"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2406624993.webp" , "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p792381411.webp" , "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2377449669.webp" , "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2462245619.webp" , "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2111252759.webp" , "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2529365085.webp" , "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2203693875.webp" , "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2197680919.webp" , "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p1382118133.webp" , "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2233971046.webp" , "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p1947152148.webp" , "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2425658570.webp" , "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2454473678.webp" , "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2187194609.webp" , "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p1031772057.webp" , "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2225808366.webp" , "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2328680655.webp" , "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2512123434.webp" , "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2517753454.webp" , "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2516578307.webp"
//    } ;
    String[] str = new String[25] ;
    String[] quote = new String[25] ;
    String[] title = new String[25] ;
    String[] rating = new String[25] ;
    String[] rateNum = new String[25] ;
    String[] detail = new String[25] ;
    final String QUOTE = " <span class=\"inq\">(.*?)</span>" ;
    final String BOOK_IMG="<img src=(.*?)width=\"90\" />" ;
    final String BOOK_TITLE="&#34; title=\"(.*?)\"" ;
    final String MOVIE_STRING ="<img width(.*?)>" ;
    final String RATE_NUM=" <span>(.*?)人评价" ;
    final String RATING = " <span class=\"rating_num\" property=\"v:average\">(.*?)</span>" ;
    final String DETAIL = "导演((.|\n|\n\r)*?)</p>" ;
    PicAdapter adapter ;
    ArrayList<Item> itemList ;
    int start ;
    int count = 1 ;
    String type ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.top250);
        Intent intent = getIntent() ;
        type = intent.getStringExtra("type") ;

        itemList = new ArrayList<>() ;
        RequestQueue rq = Volley.newRequestQueue(this) ;
        for(start = 0; start< 249; start+=25) {
            StringRequest sr = getData();
            rq.add(sr) ;
        }
        rq.start();

        ListView listView = findViewById(R.id.list1) ;
        adapter = new PicAdapter(this, itemList) ;
        listView.setAdapter(adapter);
    }
    private StringRequest getData(){
        String url ;
        StringRequest sr ;
        if(0== start ) url = "https://"+type+".douban.com/top250" ;
        else url = "https://"+type+".douban.com/top250?start="+start+"&filter=" ;
        sr = new StringRequest(url ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        doWithResponse(s);
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

        return sr;
    }
   private void doWithResponse(String s) {
       if (type.equals("movie")) {
           str = matchPattern(MOVIE_STRING, s) ;
           quote = matchPattern(QUOTE, s);
           rating = matchPattern(RATING,s) ;
           rateNum = matchPattern(RATE_NUM, s) ;
           detail = matchPattern(DETAIL, s) ;
           for (int i = 0; i < 25; i++) {
               String[] splits = str[i].split("\"");
               imgList[i] = splits[5];
               nameList[i] = count++ + "." + splits[3]
                       + "\n\n" + detail[i].split("<(.*?)>")[0].replaceAll("&nbsp;", " ")
                       + "\n" + detail[i].split("<(.*?)>")[1].replaceAll("&nbsp;", " ").replaceAll("                            ","")
                       + "\n" + rating[i].split("<(.*?)>")[1]+"分"
                       + " " + rateNum[i].split(">")[1]
                       + "\n\n“" + quote[i].split("<(.*?)>")[1]+"”";

           }
       } else {
           str = matchPattern(BOOK_IMG, s);
           quote = matchPattern(QUOTE, s);
           title = matchPattern(BOOK_TITLE, s);

           for (int i = 0; i < 25; i++) {
               imgList[i] = str[i].split("\"")[1];
               String pinjia = TextUtils.isEmpty(quote[i])? "豆瓣没有精选评论哦~~": quote[i].split("<(.*?)>")[1] ;
               nameList[i] = count++ + "." + title[i].split("\"")[1] + "\n" + pinjia;
           }
       }
   }
   private String[] matchPattern(String p, String s){
        Pattern t = Pattern.compile(p) ;
        Matcher tt = t.matcher(s) ;
        int j = 0 ;
        String[] temp = new String[25] ;
        while(tt.find() && j<25){
            temp[j++] = tt.group() ;
            Log.i("match",p+", "+temp[j-1]) ;
        }
        return temp ;
   }
}
