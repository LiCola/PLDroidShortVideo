package com.qiniu.pili.droid.shortvideo.demo.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;

/**
 * Created by 李可乐 on 2016/10/16 0016.
 * 能够设置宽高比的ImageView
 * rate=width/height
 * 重写onMeasure方法 calculatedHeight = (int) (originalWidth * rate)
 */

public class SquareImageView extends android.support.v7.widget.AppCompatImageView {

  private float rate = 1.7777f;

  public SquareImageView(@NonNull Context context) {
    super(context);
  }

  public SquareImageView(@NonNull Context context, AttributeSet attrs) {
    super(context, attrs);
    setAttrs(context, attrs);
  }

  public SquareImageView(@NonNull Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    setAttrs(context, attrs);
  }

  private void setAttrs(@NonNull Context context, AttributeSet attrs) {

  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

    int originalWidth = MeasureSpec.getSize(widthMeasureSpec);

    int calculatedHeight = (int) (originalWidth * rate);

    int finalWidth, finalHeight;

    finalWidth = originalWidth;

    finalHeight = calculatedHeight;

    super.onMeasure(MeasureSpec.makeMeasureSpec(finalWidth, MeasureSpec.EXACTLY),
        MeasureSpec.makeMeasureSpec(finalHeight, MeasureSpec.EXACTLY));
  }
}
