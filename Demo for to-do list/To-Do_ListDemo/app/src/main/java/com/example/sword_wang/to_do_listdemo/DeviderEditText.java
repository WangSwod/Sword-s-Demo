package com.example.sword_wang.to_do_listdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

/**
 * Created by sword-wang on 11/29/15.
 */
public class DeviderEditText extends EditText {

    static final String TAG = "DeviderEditText";

    Paint line_Paint;
    Rect rect;

    private  int padding = 10;
    private  int line_count = 0;

    int i = 0;
    public DeviderEditText(Context context) {
        super(context);
        init();
    }

    public DeviderEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DeviderEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private  void init(){
        line_Paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        rect = new Rect();

        line_Paint.setStyle(Paint.Style.STROKE);
        line_Paint.setColor(getResources().getColor(R.color.edit_line_color));
//        line_Paint.setStrokeWidth(1);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        int lines = getLineCount();
              Log.i(TAG," "+ i + "/" +getMeasuredHeight() + " /"+getMeasuredWidth());
        int textY  ;
        int lineY = 0 ;

        for(int i  = 0 ; i < lines ; i ++){

            textY = getLineBounds(i,rect);
            lineY = textY +padding;

            canvas.drawLine(rect.left,lineY,rect.right,lineY,line_Paint);
        }

        int each_line_height = lineY / lines;
        int next_line = lineY;

        line_count = (getMeasuredHeight() / each_line_height) - lines;

        for(int j = 0 ; j < line_count ; j++){
            next_line = next_line + each_line_height;
            canvas.drawLine(rect.left,next_line,rect.right,next_line,line_Paint);
        }




        super.onDraw(canvas);
    }
}
