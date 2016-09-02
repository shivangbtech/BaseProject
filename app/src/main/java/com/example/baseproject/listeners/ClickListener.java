package com.example.baseproject.listeners;

import android.view.View;

/**
 * Created by Shivang Goel on 3/5/16.
 */

public interface ClickListener {
    void onClick(View view, int position);

    void onLongClick(View view, int position);
}