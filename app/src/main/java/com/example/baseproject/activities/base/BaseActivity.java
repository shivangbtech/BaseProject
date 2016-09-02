package com.example.baseproject.activities.base;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.baseproject.R;
import com.example.baseproject.utilities.KeyboardUtils;

/**
 * Created by Shivang Goel on 09/08/16.
 */
public class BaseActivity extends AppCompatActivity {

    private static final String TAG = BaseActivity.class.getSimpleName();
    private Toolbar mToolbar;
    private int backEnterAnim = R.anim.left_in;
    private int backExitAnim = R.anim.right_out;
    private int activityEnterAnim = R.anim.right_in;
    private int activityExitAnim = R.anim.left_out;


    protected String getScreenName() {
        return getClass().getSimpleName();
    }

    protected Toolbar getToolbar() {
        return mToolbar;
    }

    protected void setToolbar(String title) {
        setToolbar(title, false);
    }

    protected void setToolbar(String title, boolean isBackArrow) {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setToolbarTitle(title);
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            if (isBackArrow) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
                setBackArrow();
                mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                });
            }
        } else {
            Log.w(TAG, "Toolbar is null, please make sure its defined in your XML layout for this activity");
        }
    }

    protected void setToolbar(String title, Drawable drawableBackArrow) {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setToolbarTitle(title);
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            setBackArrow(drawableBackArrow);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        } else {
            Log.w(TAG, "Toolbar is null, please make sure its defined in your XML layout for this activity");
        }
    }

    protected void setToolbar(String title, int resIdBackArrow) {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setToolbarTitle(title);
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            setBackArrow(resIdBackArrow);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        } else {
            Log.w(TAG, "Toolbar is null, please make sure its defined in your XML layout for this activity");
        }
    }

    protected void setToolbar(String title, int resIdDrawableLogo, boolean isBackArrow) {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setToolbarTitle(title);
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            setLogo(resIdDrawableLogo);
            if (isBackArrow) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
                setBackArrow();
                mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                });
            }
        } else {
            Log.w(TAG, "Toolbar is null, please make sure its defined in your XML layout for this activity");
        }
    }

    protected void setToolbar(String title, Drawable drawableLogo, boolean isBackArrow) {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setToolbarTitle(title);
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            setLogo(drawableLogo);
            if (isBackArrow) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
                setBackArrow();
                mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                });
            }
        } else {
            Log.w(TAG, "Toolbar is null, please make sure its defined in your XML layout for this activity");
        }
    }

    protected void setBackArrowColor(int color) {
        final Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.ic_back);
        upArrow.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
    }

    protected void setBackArrow() {
        final Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.ic_back);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
    }

    protected void setBackArrow(int resId) {
        getSupportActionBar().setHomeAsUpIndicator(resId);
    }

    protected void setBackArrow(Drawable drawable) {
        getSupportActionBar().setHomeAsUpIndicator(drawable);
    }

    protected void setLogo(int resId) {
        if (mToolbar != null) {
            ImageView ivLogo = (ImageView) mToolbar.findViewById(R.id.iv_toolbar_logo);
            if (ivLogo != null) {
                ivLogo.setVisibility(View.VISIBLE);
                ivLogo.setImageResource(resId);
            }
        }
    }

    protected void setLogo(Drawable drawable) {
        if (mToolbar != null) {
            ImageView ivLogo = (ImageView) mToolbar.findViewById(R.id.iv_toolbar_logo);
            if (ivLogo != null) {
                ivLogo.setVisibility(View.VISIBLE);
                ivLogo.setImageDrawable(drawable);
            }
        }
    }

    protected void setToolbarTitle(String title) {
        if (mToolbar != null) {
            TextView titleView = (TextView) mToolbar.findViewById(R.id.tv_toolbar_title);
            if (titleView != null) {
                if (title == null || title.isEmpty()) { //if title is empty, hide title
                    titleView.setVisibility(View.GONE);
                } else { //else if title is valid, display title
                    titleView.setVisibility(View.VISIBLE);
                    titleView.setText(title);
                }
            }
        } else {
            Log.w(TAG, "Toolbar is null, please make sure its defined in your XML layout for this activity");
        }
    }

    protected void setToolbarColor(int color) {
        if (mToolbar != null)
            mToolbar.setBackgroundColor(color);
    }

    // Height would be 56
    protected int getToolbarHeight() {
        int height = 56;
        if (mToolbar != null && mToolbar.getHeight() > 0)
            height = mToolbar.getHeight();
        return height;
    }

    protected void setToolbarTitleColor(int color) {
        if (mToolbar != null) {
            TextView titleView = (TextView) mToolbar.findViewById(R.id.tv_toolbar_title);
            if (titleView != null) {
                titleView.setTextColor(color);
            }
        } else {
            Log.w(TAG, "Toolbar is null, please make sure its defined in your XML layout for this activity");
        }
    }

    //Height would be 25
    protected int getStatusBarHeight() {
        Rect rectangle = new Rect();
        Window window = getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(rectangle);
        int statusBarHeight = rectangle.top;
        int contentViewTop = window.findViewById(Window.ID_ANDROID_CONTENT).getTop();
        int titleBarHeight = contentViewTop - statusBarHeight;
        Log.i(TAG, "StatusBar Height= " + statusBarHeight + " , TitleBar Height = " + titleBarHeight);
        if (statusBarHeight <= 0)
            statusBarHeight = 25;
        return statusBarHeight;
    }

    protected void setStatusBarColor(int color) {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(color);
        }
    }

    protected void setNavigationBarColor(int color) {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setNavigationBarColor(color);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getMenuLayout() != 0) {
            getMenuInflater().inflate(getMenuLayout(), menu);
            return true;
        } else {
            return super.onCreateOptionsMenu(menu);
        }
    }

    protected int getMenuLayout() {
        return 0;
    }

    /**
     * Override this method and return false if you don't want your activity to have the
     * custom forward animation
     *
     * @return Whether this activity wants custom opening and closing transitioning animation
     */
    protected boolean showForwardsCustomAnimation() {
        return true;
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        if (showForwardsCustomAnimation()) {
            overridePendingTransition(activityEnterAnim, activityExitAnim);
        }
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode, Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
        if (showForwardsCustomAnimation()) {
            overridePendingTransition(activityEnterAnim, activityExitAnim);
        }
    }

    /**
     * Override this method and return false if you don't want your activity to have the
     * custom back animation
     *
     * @return Whether this activity wants custom opening and closing transitioning animation
     */
    protected boolean showBackwardsCustomAnimation() {
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        KeyboardUtils.getInstance().hideKeyboard(this);
        if (showBackwardsCustomAnimation()) {
            overridePendingTransition(backEnterAnim, backExitAnim);
        }
    }

    public void setBackEnterAnim(int backEnterAnim) {
        this.backEnterAnim = backEnterAnim;
    }

    public void setBackExitAnim(int backExitAnim) {
        this.backExitAnim = backExitAnim;
    }

    public void setActivityEnterAnim(int activityEnterAnim) {
        this.activityEnterAnim = activityEnterAnim;
    }

    public void setActivityExitAnim(int activityExitAnim) {
        this.activityExitAnim = activityExitAnim;
    }

    public void setBackAnim(int backEnterAnim, int backExitAnim) {
        this.backEnterAnim = backEnterAnim;
        this.backExitAnim = backExitAnim;
    }

    public void setActivityAnim(int activityEnterAnim, int activityExitAnim) {
        this.activityEnterAnim = activityEnterAnim;
        this.activityExitAnim = activityExitAnim;
    }
}
