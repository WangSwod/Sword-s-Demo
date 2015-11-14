package com.example.sword_wang.mydemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Switch;

/**
 * Created by sword-wang on 11/4/15.
 */
public class MyFirstVIew extends View implements View.OnTouchListener{

    float width, height;

    float circleX , circleY , circleRadius;
    float rectLeft,rectRight, rectUp,rectBottom;

    boolean isDown = true;

    static final String TAG = "MyFirstView";

    public MyFirstVIew(Context context) {
        super(context);
        setOnTouchListener(this);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        inti();

        Log.i(TAG, "width: " + width + "/height: " + height);

        Paint p = new Paint();
        p.setFlags(Paint.ANTI_ALIAS_FLAG);
        canvas.drawColor(Color.BLACK);
        p.setColor(Color.WHITE);
        canvas.drawRect(rectLeft, rectUp, rectRight, rectBottom, p);


        p.setColor(Color.YELLOW);
        if(isDown) {
            canvas.drawCircle(circleX, circleY, circleRadius, p);
            p.setColor(Color.WHITE);
            canvas.drawCircle(circleX,rectUp,circleRadius,p);
        }else {
            canvas.drawCircle(circleX, rectUp, circleRadius, p);
            p.setColor(Color.WHITE);
            canvas.drawCircle(circleX,circleY,circleRadius,p);
        }


    }

    private void inti() {

        width = getWidth();
        height = getHeight();

        circleX  = width /2 ;
        circleY = height /2;

        circleRadius = width > height ? height / 10 : width / 10 ;

        rectLeft  = circleX - circleRadius;
        rectUp = circleY - 3*circleRadius;
        rectRight = circleX + circleRadius;
        rectBottom  = circleY;

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(inArea(event.getX(),event.getY())){
                    if(isDown){
                        isDown = false;
                    }else {
                        isDown =true;
                    }
                    invalidate();

                }
                break;
        }

        return false;
    }

    private boolean inArea(float x, float y) {

        float line =  (rectBottom - rectUp)/2;

        if( x < rectRight && x > rectLeft && y < rectBottom && y > line && !isDown){
            return  true;


        }else if (x < rectRight && x > rectLeft && y < line && y > rectUp && isDown){
            return  true;
        }

        else{
            return false;

        }

    }
}
