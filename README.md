# TrapezoidPartsView
梯形控件，the Trapezoid Parts View which draw Trapezoid image. and event on it.

![demo1](https://github.com/LightSun/TrapezoidPartsView/blob/master/TrapezoidPartsViewApp/res/TrapezoidPartsView.gif)
![demo2](https://github.com/LightSun/TrapezoidPartsView/blob/master/TrapezoidPartsViewApp/res/TrapezoidPartsView2.gif)


## 使用
--------------
- xml中
```java
  <com.heaven7.android.trapezoid.TrapezoidPartsView
        android:id="@+id/tpv"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:paddingStart="16dp"
        android:paddingEnd="16dp"
        app:tpv_angle="68"
        app:tpv_image_aspect_ratio="1.848485"
        app:tpv_text_color="@android:color/white"
        app:tpv_text_margin_top="15dp"
        app:tpv_text_margin_bottom="5dp"
        app:tpv_space="16dp"
        app:tpv_text_size="18sp"
        app:tpv_touch_alpha="0.7"
        app:tpv_debug="false"
        />

```
- 调用
```java
 @OnClick(R.id.bt1)
    public void onClick(View view){
        List<TrapezoidPartsView.TrapezoidPart> list = createTrapezoidParts();
        mTpv.setParts(list);
    }
    //构造每个梯形部分。  
    private List<TrapezoidPartsView.TrapezoidPart> createTrapezoidParts() {
        List<TrapezoidPartsView.TrapezoidPart> list = new ArrayList<>();
        for (int i = 0; i < mBgIds.length ; i ++){
            TrapezoidPartsView.TrapezoidPart part = new TrapezoidPartsView.TrapezoidPart();
            //设置3个属性，依次是背景，小图标，文本。还有一个方法可设置类型。setType.
            part.setBgIcon(getDrawableBitmap(mBgIds[i]));
            part.setIcon(getResources().getDrawable(mIconIds[i]));
            part.setText(mStrs[i]);
            list.add(part);
        }
        return list;
    }
```
- 序列化支持(用于onSaveInstanceState 和onRestoreInstanceState)
```java

    private int[] mBgIds = {
            R.drawable.plan_database_bg,
            R.drawable.plan_music_bg,
            R.drawable.plan_editorz_bg,
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
    //因为drawable和bitmap本身不支持序列化。所以需要处理
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
```
