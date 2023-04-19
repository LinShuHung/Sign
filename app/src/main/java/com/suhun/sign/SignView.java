package com.suhun.sign;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.LinkedList;

public class SignView extends View {
    public String tag = SignView.class.getSimpleName();
    private LinkedList<HashMap<String, Float>> line = new LinkedList();
    public SignView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(tag, "---onDraw---");
        setBackgroundColor(Color.GREEN);
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(4);
        for(int i=1;i<line.size();i++){
            HashMap<String, Float> p0 = line.get(i-1);
            HashMap<String, Float> p1 = line.get(i);
            canvas.drawLine(p0.get("x"), p0.get("y"), p1.get("x"), p1.get("y"), paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX(), y = event.getY();
        Log.d(tag, "---touch---"+"x:"+ x+"Y"+y);
        HashMap<String, Float> point = new HashMap<>();
        point.put("x",x);point.put("y",y);
        line.add(point);
        invalidate();
        return true;
//        return super.onTouchEvent(event);
    }
}
