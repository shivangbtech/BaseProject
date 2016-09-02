package com.example.baseproject.activities;

import android.os.Bundle;

import com.example.baseproject.R;
import com.example.baseproject.activities.base.AppBaseActivity;

public class MainActivity extends AppBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolbar("Test");
        setNavigationBarColor("#570710");
        setStatusBarColor("#570710");
        setToolbarTitleColor("#570710");
        setToolbarColor("#000000");
    }
}
