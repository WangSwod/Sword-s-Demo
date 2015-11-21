package com.example.sword_wang.compassdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.io.PushbackInputStream;

/**
 * Created by sword-wang on 11/19/15.
 */
public class CompassView extends View {

    static  final String TAG = "CompassView";

    private float degree = 15;
    private  float angle = 0 ;

    private  String north, south, east, west;

    Paint paintCircle,paintHead,paintText,paintMark;

    public CompassView(Context context) {
        super(context);
        init();
    }

    public CompassView(Context context, AttributeSet attrs) {
        super(context, attrs);
         init();
    }

    public CompassView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
         init();
    }
    private  void init(){

        setFocusable(true);
        paintCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintHead = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintMark = new Paint();
        paintText = new Paint();

        paintCircle.setColor(getResources().getColor(R.color.circle));
        paintHead.setColor(getResources().getColor(R.color.head));
        paintMark.setColor(getResources().getColor(R.color.mark));
        paintText.setColor(getResources().getColor(R.color.text));

        paintCircle.setStrokeWidth(5);
        paintCircle.setStyle(Paint.Style.STROKE);

        paintText.setTextSize(50);

        north = getResources().getString(R.string.north);
        south = getResources().getString(R.string.south);
        east = getResources().getString(R.string.east);
        west = getResources().getString(R.string.west);


    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);
        int height = getMeasuredHeight();
        int width = getMeasuredWidth();
        Log.i(TAG, "height:" + height + "/width:" + width);

        float radius = (float)(0.5 * height)-paintCircle.getStrokeWidth() ;
        float lenght = (float)(0.2 * radius);

        //float textX  = radius - paintText.getTextSize() / 2;
        //float textY  = lenght + paintText.getTextSize();

        float x  = height / 2;

        canvas.drawCircle(x, x, radius, paintCircle);


        while(angle <= 360){
            canvas.drawLine(radius,0,radius,lenght,paintMark);
            if(angle == 0 ){
                 float textY = getTextY(lenght,north);
                  canvas.drawText(north,getTextX(radius,north),getTextY(lenght,north),paintText);
                drawMark(canvas,radius,textY + radius/10, radius/20,radius/10,paintHead);
            }
            if(angle == 90 ){

                  canvas.drawText(east,getTextX(radius,east),getTextY(lenght,east),paintText);

            }
              if(angle == 180 ){
                    canvas.drawText(south,getTextX(radius,south),getTextY(lenght,south),paintText);
              }
              if(angle == 270 ){

                    canvas.drawText(west,getTextX(radius,west),getTextY(lenght,west),paintText);

              }
            canvas.rotate(degree,x,x);
            angle = angle + degree;

        }
        reset(); //绘制完成后重置angle，方便下次重绘

    }

    private void reset(){
        angle = 0;
    }

    private void drawMark(Canvas canvas, float centerX, float centerY, float offsetX, float offsetY,Paint p){
        canvas.drawLine(centerX,centerY,centerX+ offsetX,centerY + offsetY,p);
        canvas.drawLine(centerX,centerY,centerX-offsetX,centerY+offsetY,p);
    }


    private float getTextX (float startX,String text){
        return  startX - paintText.measureText(text)/2;
    }
    private  float getTextY (float startY, String text){
        return  startY + paintText.getTextSize();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = measure(widthMeasureSpec);
        int height = measure(heightMeasureSpec);

        if(width > height){
            setMeasuredDimension(height, height);
        }else {
            setMeasuredDimension(width, width);
        }

    }

    private  int measure(int measureSpec) {
        int value = MeasureSpec.getSize(measureSpec);
        int mode = MeasureSpec.getMode(measureSpec);

        if (mode == MeasureSpec.UNSPECIFIED) {
            return 200;
        }
        return value;
    }


}
