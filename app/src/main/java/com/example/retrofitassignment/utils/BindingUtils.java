package com.example.retrofitassignment.utils;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.retrofitassignment.R;

public class BindingUtils {
    @BindingAdapter("image")
    public static void setImage(ImageView imageView, String imgSrc) {
        Glide.with(imageView.getContext())
                .load(imgSrc)
                .placeholder(R.drawable.loading_img)
                .error(R.drawable.ic_broken_image)
                .transform(new CenterCrop(), new RoundedCorners(25))
                .into(imageView);
    }
}
