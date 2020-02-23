package com.men.buttontoggle.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MyToggleView extends View {

    private Button button;
    private int buttonWidth;
    private int buttonHeight;
    private int viewWidth;
    private int viewHeight;
    private static String NAMESPACE = "http://schemas.android.com/apk/res-auto";
    private Bitmap backgroundMap;
    private Bitmap iconMap;
    private int maxDistance;
    private float lefticon;

    public MyToggleView(Context context) {
        this(context, null);
    }

    public MyToggleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyToggleView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public MyToggleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        int background = attrs.getAttributeResourceValue(NAMESPACE, "background", -1);
        int iconid = attrs.getAttributeResourceValue(NAMESPACE, "iconid", -1);
        boolean istoggle = attrs.getAttributeBooleanValue(NAMESPACE, "istoggle", false);
        setBackgroundAndIcon(background, iconid);
    }

    private void setBackgroundAndIcon(int background, int iconid) {
        backgroundMap = BitmapFactory.decodeResource(getResources(), background);
        iconMap = BitmapFactory.decodeResource(getResources(), iconid);
        maxDistance = backgroundMap.getWidth() - iconMap.getWidth();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWidth = w;
        viewHeight = h;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        setMeasuredDimension(backgroundMap.getWidth(), backgroundMap.getHeight());
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(backgroundMap, 0, 0, null);
        if (lefticon < 0) {
            lefticon = 0;
        } else if (lefticon > maxDistance) {
            lefticon = maxDistance;
        }
        canvas.drawBitmap(iconMap, lefticon, 0, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case (MotionEvent.ACTION_DOWN):

                break;
            case (MotionEvent.ACTION_MOVE):
                lefticon = (int) (event.getX() - iconMap.getWidth()/2);

                break;
            case (MotionEvent.ACTION_UP):
                if (event.getX() < backgroundMap.getWidth() / 2) {
                    lefticon = 0;
                } else {
                    lefticon = maxDistance;
                }
                break;
        }
        invalidate();
        return true;
    }
}
