package com.example.baseproject.listeners;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Shivang Goel on 10/7/16.
 */


public interface OnRecyclerClickListener {

    void onRecycleClicked(RecyclerView recyclerView, int position, View v);
}
