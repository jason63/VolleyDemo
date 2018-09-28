package com.jason63.volleydemo;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button top250, book, music ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        top250 = findViewById(R.id.top250) ;
        book = findViewById(R.id.book250) ;
        music = findViewById(R.id.music250) ;
        top250.setOnClickListener(this);
        book.setOnClickListener(this);
        music.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.top250:
                startIntent("movie");
                break ;
            case R.id.book250:
                startIntent("book");
                break ;
            case R.id.music250:
                startIntent("music");
                break ;
        }
    }
    private void startIntent(String s){
        Intent intent = new Intent(MainActivity.this, Douban250.class) ;
        intent.putExtra("type",s) ;
        startActivity(intent);
    }
}
