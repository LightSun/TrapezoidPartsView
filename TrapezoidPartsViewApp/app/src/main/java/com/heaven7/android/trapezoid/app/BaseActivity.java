package com.heaven7.android.trapezoid.app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by heaven7 on 2018/11/24 0024.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        onInitialize(this, savedInstanceState);
    }

    protected abstract @LayoutRes
    int getLayoutId();
    protected abstract void onInitialize(Context context, Bundle savedInstanceState);
}
