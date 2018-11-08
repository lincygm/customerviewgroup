package com.jinda.app.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jinda.app.R;

import java.util.ArrayList;
import java.util.List;

public class IndicateView extends LinearLayout {

    ImageView view;
    ImageView view2;
    ImageView view3;
    ImageView view4;
    ImageView view5;
    List<ImageView> list = new ArrayList<>();
    public IndicateView(Context context) {
        super(context);
    }

    public IndicateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context,attrs);
    }

    public IndicateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context,attrs);
    }

    private void initView(Context context, AttributeSet attributes){
        view = new ImageView(context);
        view.setImageResource(R.drawable.dot_2);
        view2 = new ImageView(context);
        view2.setImageResource(R.drawable.dot_1);
        view3 = new ImageView(context);
        view3.setImageResource(R.drawable.dot_1);
        view4 = new ImageView(context);
        view4.setImageResource(R.drawable.dot_1);


        this.setOrientation(HORIZONTAL);
        this.addView(view);
        this.addView(view2);
        this.addView(view3);
        this.addView(view4);

        list.add(view);
        list.add(view2);
        list.add(view3);
        list.add(view4);
    }
    int width_f =0;
    int height_f =0;
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = measureSize(0, heightMeasureSpec);
        int width = measureSize(0, widthMeasureSpec);
        width_f = width;
        height_f = height;
        int count = getChildCount();
        for(int i=0;i<count;i++){
            View child = getChildAt(i);
            measureChild(child,width,height);
            Log.d("DDS","child =1= "+child.getMeasuredWidth());
            Log.d("DDS","child =2= "+child.getMeasuredHeight());
        }
        setMeasuredDimension(width, height);

    }

    private int measureSize(int defaultSize,int measureSpec) {
        int result = defaultSize;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else if (specMode == MeasureSpec.AT_MOST) {
            result = Math.min(result, specSize);
        }
        return result;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        int count = getChildCount();
        int left_ = (width_f-(count*90))/2+90;//计算 左边margin 让他居中
        int margin ;
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            margin=left_+50*i;
            child.layout(margin, 10,margin+20, height_f-child.getHeight());
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void setPosition(int position){
        for(int i=0;i<list.size();i++){
            if(position == i){
                list.get(i).setImageDrawable(getResources().getDrawable(R.drawable.dot_2));
                invalidate();
                continue;
            }
            list.get(i).setImageDrawable(getResources().getDrawable(R.drawable.dot_1));
            invalidate();
        }
        invalidate();
    }

}
