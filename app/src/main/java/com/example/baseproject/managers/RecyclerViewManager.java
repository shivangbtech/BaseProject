package com.example.baseproject.managers;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.baseproject.listeners.ItemClickSupport;
import com.example.baseproject.listeners.OnRecyclerClickListener;
import com.example.baseproject.views.DividerItemDecoration;

/**
 * Created by Shivang Goel on 10/7/16.
 */

public class RecyclerViewManager {

    private final String TAG = getClass().getSimpleName();
    private OnRecyclerClickListener onRecyclerClickListener;
    private static RecyclerViewManager mClassInstance;

    private RecyclerViewManager(){}

    public static RecyclerViewManager getInstance(){
        if(mClassInstance == null){
            mClassInstance = new RecyclerViewManager();
        }
        return mClassInstance;
    }

    public void setupRecyclerView(RecyclerView recyclerView, Context context) {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public void setClickItemSupport(RecyclerView recyclerView, OnRecyclerClickListener onRecyclerClickListener) {
        this.onRecyclerClickListener = onRecyclerClickListener;
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(mItemClickSupport);
    }

    public void setItemDecoration(RecyclerView recyclerView, Context context) {
        recyclerView.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
    }

    private ItemClickSupport.OnItemClickListener mItemClickSupport = new ItemClickSupport.OnItemClickListener() {
        @Override
        public void onItemClicked(RecyclerView recyclerView, int position, View v) {
            if(onRecyclerClickListener != null){
                onRecyclerClickListener.onRecycleClicked(recyclerView, position, v);
            }else {
                Log.w(TAG, "onRecyclerClickListener is null! Make sure you have called setClickItemSupport()");
            }
        }
    };
}
