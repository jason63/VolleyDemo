package com.jason63.volleydemo;

/**
 * Created by Jasonli on 2018/9/26;
 */
public class Item {
    private String title ;
    private String img ;

    public Item(String title, String img){
        this.img = img ;
        this.title= title ;
    }
    public void setImg(String img) {
        this.img = img;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public String getTitle() {
        return title;
    }
}
