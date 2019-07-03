package com.heaven7.android.trapezoid.app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.heaven7.android.trapezoid.TrapezoidPartsView;
import com.heaven7.java.base.util.DefaultPrinter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by heaven7 on 2019/5/30.
 */
public class TestTrapezoidPartsView2 extends BaseActivity implements TrapezoidPartsView.OnTrapezoidPartClickListener {


    private static final String TAG = "TestTrapezoidPartsView";

    @BindView(R.id.tpv)
    TrapezoidPartsView mTpv;

    private int[] mBgIds = {
            R.drawable.plan_music_bg,
            R.drawable.plan_music_bg,
            R.drawable.plan_music_bg,
    };
    private int[] mIconIds = {
            R.drawable.plan_database,
            R.drawable.plan_music,
            R.drawable.plan_editor,
    };
    private String[] mStrs = {
            "Projects",
            "Music",
            "Edit"
    };

    @Override
    protected int getLayoutId() {
        return R.layout.ac_test_trapezoid_parts2;
    }

    @Override
    protected void onInitialize(Context context, Bundle savedInstanceState) {
        mTpv.setOnTrapezoidPartClickListener(this);
        mTpv.setTrapezoidPartParcelCallback(new TrapezoidPartsView.TrapezoidPartParcelCallback() {
            @Override
            public void writeToBundle(Context context, Bundle out, int index, TrapezoidPartsView.TrapezoidPart part) {
                out.putInt("bg_id", mBgIds[index]);
                out.putInt("icon_id", mIconIds[index]);
            }
            @Override
            public void readFromBundle(Context context, Bundle in, int index, TrapezoidPartsView.TrapezoidPart part) {
                int bgId = in.getInt("bg_id");
                int iconId = in.getInt("icon_id");
                BitmapDrawable bgDrawable = (BitmapDrawable) context.getResources().getDrawable(bgId);
                Drawable icon = context.getResources().getDrawable(iconId);
                part.setBgIcon(bgDrawable.getBitmap());
                part.setIcon(icon);
            }
        });
        List<TrapezoidPartsView.TrapezoidPart> list = createTrapezoidParts();
        mTpv.setParts(list);
    }

    @OnClick(R.id.bt1)
    public void onClick(View view){
        List<TrapezoidPartsView.TrapezoidPart> list = createTrapezoidParts();
        mTpv.setParts(list);
    }

    private List<TrapezoidPartsView.TrapezoidPart> createTrapezoidParts() {
        List<TrapezoidPartsView.TrapezoidPart> list = new ArrayList<>();
        for (int i = 0; i <2 /*mBgIds.length*/ ; i ++){
            TrapezoidPartsView.TrapezoidPart part = new TrapezoidPartsView.TrapezoidPart();
            part.setBgIcon(getDrawableBitmap(mBgIds[i]));
            part.setIcon(getResources().getDrawable(mIconIds[i]));
            part.setText(mStrs[i]);
            list.add(part);
        }
        return list;
    }
    private Bitmap getDrawableBitmap(int resId){
        BitmapDrawable bd = (BitmapDrawable) getResources().getDrawable(resId);
        return bd.getBitmap();
    }

    @Override
    public void onClickTrapezoidPart(TrapezoidPartsView view, TrapezoidPartsView.TrapezoidPart part) {
        DefaultPrinter.getDefault().debug(TAG , "onClickTrapezoidPart", part.getText());
    }

}
