package com.example.countrieslist.view;

import android.content.Context;
import android.widget.ImageView;

import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.countrieslist.R;

public class Util {
    public static void loadImage(ImageView image, String url, CircularProgressDrawable progressDrawable){
        RequestOptions options = new RequestOptions()
                .placeholder(progressDrawable)
                .error(R.mipmap.ic_launcher_round);
        Glide.with(image.getContext()).setDefaultRequestOptions(options)
                .load(url).into(image);
    }

    public static CircularProgressDrawable getProgressDrawable(Context context){
        CircularProgressDrawable drawable = new CircularProgressDrawable(context);
        drawable.setStrokeWidth(10f);
        drawable.setCenterRadius(50f);
        drawable.start();
        return drawable;
    }
}
