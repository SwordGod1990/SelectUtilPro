package com.example.selectutilpro.city_recycler;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * 字母导航控件
 */
public class NavigationView extends View {
    private Paint mPaint;
    private String[] mDate = new String[]{"A", "B", "C", "D", "E", "F",
            "G", "H", "I", "J", "K", "L", "N", "M", "O", "P", "Q", "R",
            "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};
    private int position = -1;
    private int mHeight;

    public NavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(23);
        mPaint.setTypeface(Typeface.DEFAULT_BOLD);
    }

    public NavigationView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mHeight = getHeight();
        int width = getWidth();
        int simpleHeight = mHeight / mDate.length;
        for (int i = 0; i < mDate.length; i++) {
            mPaint.setColor(Color.BLUE);
            if (i == position) {
                mPaint.setColor(Color.RED);
            }
            float x = (width - mPaint.measureText(mDate[i])) / 2.0f;
            float y = simpleHeight * (i + 1);
            canvas.drawText(mDate[i], x, y, mPaint);
        }
    }

    @SuppressLint("NewApi")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y = (int) event.getY();
        int oldPosition = position;
        int c = (y * mDate.length) / mHeight;
        switch (event.getAction()) {

            case MotionEvent.ACTION_UP:
                setBackground(new ColorDrawable(0));
                if (mDialog != null) {
                    mDialog.setVisibility(View.GONE);
                }
                position = -1;
                break;

            default:
                setBackgroundColor(Color.parseColor("#EEE5DE"));
                if (c >= 0 && c < mDate.length && c != oldPosition) {
                    if (mOnTouchItemListener != null) {
                        mOnTouchItemListener.onTouch(mDate[c]);
                        Log.e("------", "onTouchEvent: "+mDate[c] );
                    }
                    if (mDialog != null) {
                        mDialog.setText(mDate[c]);
                        mDialog.setVisibility(View.VISIBLE);
                    }
                }
                position = c;
                invalidate();
                break;
        }

        return true;
    }

    private TextView mDialog;

    public void setDialog(TextView dialog) {
        mDialog = dialog;
    }

    private OnTouchItemListener mOnTouchItemListener;

    public void setOnTouchItemListener(OnTouchItemListener onTouchItemListener) {
        mOnTouchItemListener = onTouchItemListener;
    }

    public interface OnTouchItemListener {
        void onTouch(String var1);
    }

}
