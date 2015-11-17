package com.example.apple.customtextviewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by apple on 11/17/15.
 */
public class CustomTextView extends TextView {


    static final String TAG = "CustomTextView";

    float x, y , width, height;
    int margin = 20;

    Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
       // x = getX();
       // y = getY();
        height =getMeasuredHeight();
        width = getMeasuredWidth();
        p.setColor(Color.BLUE);
        p.setStrokeWidth(2);
        p.setStyle(Paint.Style.STROKE);

       // Log.i(TAG, x + "/" + y);
        Log.i(TAG, height + "/" + width);
        canvas.drawRect(0, 0,  width,  height, p);
        canvas.drawRect(margin,margin,width-margin,height-margin,p);



        super.onDraw(canvas);
        canvas.drawText("where",x,y,p);

    }
}
