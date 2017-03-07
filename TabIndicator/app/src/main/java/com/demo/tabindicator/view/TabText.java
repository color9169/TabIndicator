package com.demo.tabindicator.view;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Tab控件
 * Created by color on 17/2/28.
 */

public class TabText extends TextView {
    public TabText(Context context) {
        super(context);
    }

    public TabText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TabText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public float getTextWidth() {
        String text = getText().toString();
        TextPaint textPaint = getPaint();
        float textPaintWidth = textPaint.measureText(text);
        return textPaintWidth;
    }

    public float getTextHeight() {
        TextPaint paint = getPaint();
        paint.setTextSize(paint.getTextSize());
        Paint.FontMetrics fm = paint.getFontMetrics();

        float topY = fm.top;        //获取当前绘制最顶线
        float ascentY = fm.ascent;   // 获取当前绘制顶线
        float descentY = fm.descent; // 获取当前绘制底线
        float bottomY = fm.bottom; // 获取当前绘制最底线
        float result = descentY - ascentY;
        return result;
    }

    public float getTopValue() {
        TextPaint paint = getPaint();
        paint.setTextSize(paint.getTextSize());
        Paint.FontMetrics fm = paint.getFontMetrics();
        float topY = fm.top;        //获取当前绘制最顶线
        return topY;
    }

    public float getBottomValue() {
        TextPaint paint = getPaint();
        paint.setTextSize(paint.getTextSize());
        Paint.FontMetrics fm = paint.getFontMetrics();
        float bottomY = fm.bottom; // 获取当前绘制最底线
        return bottomY;
    }


    public float getLeftValue() {
        String str = getText().toString();
        Rect rect = new Rect();
        getPaint().getTextBounds(str, 0, str.length(), rect);
        return rect.left;
    }

    public float getRightValue() {
        String str = getText().toString();
        Rect rect = new Rect();
        getPaint().getTextBounds(str, 0, str.length(), rect);
        return rect.right;
    }

}
