package com.example.baseproject.managers;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.baseproject.R;

/**
 * Created by Shivang Goel on 10/7/16.
 */


public class ImageLoaderManager {

    public void loadImage(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .fitCenter()
//                .placeholder(R.drawable.loading_spinner)
                .crossFade()
                .into(imageView);
    }
}
