# TrapezoidPartsView
梯形控件，the Trapezoid Parts View which draw Trapezoid image. and event on it.

![demo1](https://github.com/LightSun/TrapezoidPartsView/blob/master/TrapezoidPartsViewApp/res/TrapezoidPartsView.gif)
![demo2](https://github.com/LightSun/TrapezoidPartsView/blob/master/TrapezoidPartsViewApp/res/TrapezoidPartsView2.gif)


## 使用
--------------
- Gradle
```java
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

 implementation 'com.github.LightSun:TrapezoidPartsView:<see release>'
```
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
- 设置点击事件监听器
```java
  mTpv.setOnTrapezoidPartClickListener(this);
  
    @Override
    public void onClickTrapezoidPart(TrapezoidPartsView view, TrapezoidPartsView.TrapezoidPart part) {
        DefaultPrinter.getDefault().debug(TAG , "onClickTrapezoidPart", part.getText());
    }
```

- Parcel序列化支持(用于onSaveInstanceState 和onRestoreInstanceState)
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
## 自定义属性详解
------------
```java
<declare-styleable name="TrapezoidPartsView">
        <!-- 各个梯形之间的间隙（水平方向） -->
        <attr name="tpv_space" format="dimension|reference"/>
        <!-- 触摸梯形时的透明度。（用于2态效果） -->
        <attr name="tpv_touch_alpha" format="float|reference"/>
        <!-- 文本大小 --> 
        <attr name="tpv_text_size" format="dimension|reference"/>
        <!-- 文本颜色 --> 
        <attr name="tpv_text_color" format="color|reference"/>
        <!-- 文本距离顶部 --> 
        <attr name="tpv_text_margin_top" format="dimension|reference"/>
         <!-- 文本距离底部 --> 
        <attr name="tpv_text_margin_bottom" format="dimension|reference"/>
        <!-- 梯形的角度。用于动态计算梯形高度。使得可以等比缩放 -->
        <attr name="tpv_angle" format="float|reference"/>
        <!-- 梯形背景图的比例。用于等比缩放 -->
        <attr name="tpv_image_aspect_ratio" format="float|reference"/>
	<!-- 是否全部用平行四边形 --> 
	<attr name="tpv_all_parallelogram" format="boolean|reference"/>
        <!-- 是否开启debug模式。debug下只会绘制边框 -->
        <attr name="tpv_debug" format="boolean|reference"/>
    </declare-styleable>
```

 ## License

    Copyright 2019  
                    heaven7(donshine723@gmail.com)

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
