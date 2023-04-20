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
    private LinkedList<LinkedList<HashMap<String, Float>>> lines = new LinkedList<>();
    public SignView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBackgroundColor(Color.GREEN);
        int painStrokeWidth = 5;
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(painStrokeWidth);
        for(LinkedList<HashMap<String, Float>>line:lines){
            for(int i=1;i<line.size();i++){
                HashMap<String, Float> po = line.get(i-1);
                HashMap<String, Float> p1 = line.get(i);
                canvas.drawLine(po.get("x"), po.get("y"), p1.get("x"), p1.get("y"), paint);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LinkedList<HashMap<String, Float>> line;
        HashMap<String, Float> point = new HashMap<>();
        float x = event.getX(), y = event.getY();
        point.put("x", x); point.put("y", y); //record user touch coordinate x and y and save to HashMap
        if(event.getAction() == MotionEvent.ACTION_DOWN){//Draw new line
            Log.d(tag, "Draw new line in position x:" + x + "y:" + y);
            line = new LinkedList<>();
            line.add(point);
            lines.add(line);
        }else if(event.getAction() == MotionEvent.ACTION_MOVE){//user touch and move in last line
            lines.getLast().add(point);
        }
        invalidate(); //reload view for onDraw
        return true;
    }
}