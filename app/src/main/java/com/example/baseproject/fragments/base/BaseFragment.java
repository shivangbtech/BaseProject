package com.example.baseproject.fragments.base;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.example.baseproject.R;
import com.example.baseproject.utilities.KeyboardUtils;

/**
 * Created by Shivang Goel on 20/6/16.
 */
public abstract class BaseFragment extends Fragment {

    private boolean shouldOptionMenu = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutView(), null);
        initUi(view);
        if (shouldOptionMenu)
            setHasOptionsMenu(true);
        return view;
    }

    protected abstract int getLayoutView();

    protected abstract void initUi(View view);

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        if (getMenuLayout() != 0) {
            inflater.inflate(getMenuLayout(), menu);
        }
    }

    protected int getMenuLayout() {
        return 0;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        KeyboardUtils.getInstance().hideKeyboard(getActivity());
    }

    protected void setShouldOptionMenu(boolean shouldOptionMenu) {
        this.shouldOptionMenu = shouldOptionMenu;
    }
}
