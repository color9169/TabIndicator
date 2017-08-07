package com.demo.tabindicator.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * 绘制指示器的控件
 */
public class TabIndicator extends View {

    private Paint mPaint;
    private TabIndicator tab;

    private int colorRes = Color.BLACK;
    private int paintWidth = 5;


    private float left = 0f;
    private float top = 0f;
    private float right = 0f;
    private float bottom = 0f;

    private float start = -1;
    private float end = -1;

    private Paint.Style paintStyle = Paint.Style.STROKE;

    public TabIndicator(Context context) {
        this(context, null);
        tab = this;
    }

    public TabIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setColor(colorRes);
        mPaint.setStrokeWidth(paintWidth);
        mPaint.setStyle(paintStyle);
    }

    /**
     * 设置指示器的大小
     *
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    public void setBounds(float left, float top, float right, float bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        invalidate();
    }

    public void setWidth(float width) {
        this.right = left + width;
    }

    public void setHeight(float height) {
        this.bottom = height + bottom;
    }

    public float getHeightValue() {
        return 0f;
    }

    /**
     * 开始执行动画
     *
     * @param start
     * @param end
     */
    public void setTranslate(float start, float end) {
        this.start = start;
        this.end = end;
        startTranslateAnim();
    }

    private int position = 0;

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF r = new RectF(left, top, right, bottom);
        canvas.drawRect(r, mPaint);
    }


    private void startTranslateAnim() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(tab, "translationX", start, end);
        animator.setDuration(200);
        animator.start();
    }

    /**
     * @Override /**
     * 宽度始终保持跟父容器一直，为了适应滑动
     */
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(
                ((ViewGroup) getParent()).getMeasuredWidth(),
                (int) mPaint.getStrokeWidth()
        );
    }

    public interface OnAnimEndListener {
        void animEnd();
    }
}
