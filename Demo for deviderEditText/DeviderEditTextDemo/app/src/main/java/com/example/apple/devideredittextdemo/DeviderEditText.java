package com.example.apple.devideredittextdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

/**
 * Created by apple on 11/30/15.
 */
public class DeviderEditText extends EditText {
    static final String TAG = "DeviderEditText";

    Paint line_Paint;
    Rect rect;

    private int padding = 20;
    private int line_Count = 0;

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

    private void init() {
        rect = new Rect();
        line_Paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        line_Paint.setColor(getResources().getColor(R.color.edit_line_color));
        line_Paint.setStyle(Paint.Style.STROKE);
        line_Paint.setStrokeWidth(0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int lines = getLineCount();
        int line_height = 0;
        int textY = 0;
        int lineY = 0 ;



        for (int i = 0; i < lines; i++) {
            textY = getLineBounds(i, rect);
            Log.i(TAG, i + "/" + textY + "");
            lineY = textY +padding;
            canvas.drawLine(rect.left, lineY,rect.right, lineY, line_Paint);
        }

        int each_Line_Height = lineY / lines;

        float edit_height = getMeasuredHeight();

        line_Count = (int )(edit_height / each_Line_Height) - lines;
        line_height = lineY;
//        Log.i(TAG, lineY + "");
        for(int i = 0 ; i< line_Count ;i++){

            line_height = line_height+ each_Line_Height;
            canvas.drawLine(rect.left,line_height ,rect.right,line_height ,line_Paint);
        }

    }
}
