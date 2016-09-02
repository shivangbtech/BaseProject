package com.example.baseproject.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baseproject.R;

import java.util.ArrayList;

/**
 * Created by Shivang Goel on 9/6/16.
 */
public abstract class BaseRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

//    private Context mContext;
    private final int EMPTY_VIEW = 1;
    private int mListSize = 0;

    public BaseRecyclerViewAdapter(int listSize){
        mListSize = listSize;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        mContext = parent.getContext();
        View itemView;
        if (viewType == EMPTY_VIEW) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_empty_adapter, parent, false);
            EmptyViewHolder evh = new EmptyViewHolder(itemView);
            return evh;
        } else {
            return onCreateVH(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (!(holder instanceof EmptyViewHolder))
            onBindVH(holder, position);
    }

    protected class EmptyViewHolder extends RecyclerView.ViewHolder {
        public EmptyViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mListSize == 0) {
            return EMPTY_VIEW;
        }
        return super.getItemViewType(position);
    }

    abstract void onBindVH(RecyclerView.ViewHolder holder, int position);

//    abstract void onBindBaseViewHolder(RecyclerView.ViewHolder holder, int position);

    abstract RecyclerView.ViewHolder onCreateVH(ViewGroup parent, int viewType);
}
