package easeui.hyphenate.com.testrecord;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;

import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class NinePhotoView extends ViewGroup{

    static  final int MAX = 9;
    int [] pictures = {R.drawable.anim001,R.drawable.anim002,R.drawable.anim003,R.drawable.anim004};
    int hspace =0;//横向间距
    int vspace=0;//纵向间距
    Context context;
    int childwidth = 0;
    int childheight = 0;
    View addView;

    public NinePhotoView(Context context) {
        super(context);
        init(context);
    }

    public NinePhotoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NinePhotoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    void init(Context context){
        hspace= 10;
        vspace= 10;
        //Log.d("DDS",">>"+(2%3));
        //Log.d("DDS",">>"+(4/3));
        ImageView v = new ImageView(context);
        v.setBackgroundResource(R.drawable.dance_icon);
        addView(v);
        ImageView v2 = new ImageView(context);
        v2.setBackgroundResource(R.drawable.dance_icon);
        addView(v2);
        ImageView v3 = new ImageView(context);
        v3.setBackgroundResource(R.drawable.dance_icon);
        addView(v3);
        ImageView v4 = new ImageView(context);
        v4.setBackgroundResource(R.drawable.dance_icon);
        addView(v4);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int rw = MeasureSpec.getSize(widthMeasureSpec);
        int rh = MeasureSpec.getSize(heightMeasureSpec);

        childwidth = (rw-2*hspace)/3;//屏幕总宽度-2个横向间距/3 =每个图片的宽度
        //childwidth=200;
        childheight = childwidth;//宽高相等
        int childcount = getChildCount();//得出总的子view
        int vw = rw;
        int vh = rh;
        if(childcount<3){
            vw = childcount*(childwidth+hspace);
        }
        vh = ((childcount+3)/3)*(childwidth+vspace);
        setMeasuredDimension(vw,vh);
    }

    @Override
    protected void onLayout(boolean b, int i0, int i1, int i2, int i3) {
        int left ;
        int top ;
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            left = (i % 3) * (childwidth + hspace);
            top = (i / 3) * (childheight + hspace);
            Log.d("onlayout",left+"");
            Log.d("onlayout",top+"");
            child.layout(left, top, left + childwidth, top + childheight);
        }
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
