package com.example.retrofitassignment.network;

import android.text.TextUtils;
import android.view.View;

import androidx.appcompat.content.res.AppCompatResources;

import com.google.gson.annotations.SerializedName;

public class MarsProperty {
    public String id;
    public String type;
    @SerializedName("img_src")
    public String imgSrcUrl;
    public Double price;

    public String getId() {
        return "ID: " + id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return "Type: " + type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImgSrcUrl() {
        return imgSrcUrl;
    }

    public int visibility(){
        if(TextUtils.isEmpty(getImgSrcUrl())){
            return View.GONE;
        } else {
            return View.VISIBLE;
        }
    }

    public void setImgSrcUrl(String imgSrcUrl) {
        this.imgSrcUrl = imgSrcUrl;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
