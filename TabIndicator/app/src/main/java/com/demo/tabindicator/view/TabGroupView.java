package com.demo.tabindicator.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by color on 17/2/28.
 */

public class TabGroupView extends LinearLayout implements TabIndicator.OnAnimEndListener {

    private Context mContext;
    private TabIndicator topIndicator;
    private TabIndicator bottomIndicator;
    private List<TabText> texts;

    private LinearLayout parentLayout;
    private LinearLayout contentLayout;

    private int padding = 5;
    private int gravity = Gravity.CENTER;
    private float textSize = 14;
    private int indicatorHeight = 1;
    private int selectPosition = 0;
    private int currentPosition = 0;

    private OnTabChangeListener onTabChangeListener;
    private ViewPager viewPager;

    public TabGroupView(Context context) {
        this(context, null);
    }

    public TabGroupView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabGroupView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        texts = new ArrayList<>();
        init();

    }

    public void setOnTabChangeListener(OnTabChangeListener onTabChangeListener) {
        this.onTabChangeListener = onTabChangeListener;
    }

    public void setPadding(int padding) {
        this.padding = padding;
    }

    public void setTabGravity(int gravity) {
        this.gravity = gravity;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public void setIndicatorHeight(int indicatorHeight) {
        this.indicatorHeight = indicatorHeight;
    }

    public void setViewPager(ViewPager vp) {
        this.viewPager = vp;
    }

    /**
     * 初始化
     */
    private void init() {
        this.setGravity(Gravity.CENTER);
        this.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        topIndicator = new TabIndicator(mContext);
        bottomIndicator = new TabIndicator(mContext);


        parentLayout = this;
        parentLayout.setOrientation(LinearLayout.VERTICAL);
        parentLayout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        contentLayout = new LinearLayout(mContext);
        contentLayout.setOrientation(LinearLayout.HORIZONTAL);
        contentLayout.setPadding(0, (int) topIndicator.getHeightValue(), 0, (int) topIndicator.getHeightValue());
        contentLayout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        this.parentLayout.addView(topIndicator);
        this.parentLayout.addView(contentLayout);
        this.parentLayout.addView(bottomIndicator);

    }

    public void addTab(final TabText tab) {
        if (texts == null) {
            texts = new ArrayList<>();
        }
        tab.setPadding(padding, 4, padding, 4);
        tab.setGravity(gravity);
        tab.setTextSize(textSize);
        LinearLayout.LayoutParams tabParams =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        tab.setLayoutParams(tabParams);


        texts.add(tab);
        if (texts.size() == 1) {
            TabText temp = texts.get(0);

            float height = temp.getTextHeight();
            float width = temp.getTextWidth();
            float top = temp.getTopValue();
            float left = temp.getLeftValue();
            float right = temp.getRightValue();


            topIndicator.setBounds(left + temp.getPaddingLeft(), height + top, right, height + top);
            topIndicator.setWidth(width);
            topIndicator.setHeight(indicatorHeight);

            bottomIndicator.setBounds(left + temp.getPaddingLeft(), top + height, right, top + height);
            bottomIndicator.setWidth(width);
            bottomIndicator.setHeight(indicatorHeight);

            topIndicator.setPosition(0);
            topIndicator.setPosition(0);

        }
        tab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                TabText t = (TabText) v;
                int position = Integer.parseInt((String) t.getTag());
                int tabPosition = topIndicator.getPosition();
                selectPosition = position;

                if (position == tabPosition) {
                    return;
                }
                if (position < 0) {
                    position = 0;
                }
                if (viewPager != null) {
                    viewPager.setCurrentItem(position);
                }
                if (onTabChangeListener != null) {
                    onTabChangeListener.onTabChange(position);
                }

                TabText startTab = texts.get(tabPosition);
                TabText currentTab = texts.get(position);
                currentPosition = position;


                float startX = startTab.getX();
                float endX = currentTab.getX();

                topIndicator.setPosition(position);
                topIndicator.setTranslate(startX, endX);

                bottomIndicator.setPosition(position);
                bottomIndicator.setTranslate(startX, endX);


                float height = currentTab.getTextHeight();
                float width = currentTab.getTextWidth();
                float top = currentTab.getTopValue();
                float left = currentTab.getLeftValue();
                float right = currentTab.getRightValue();


                topIndicator.setBounds(left + currentTab.getPaddingLeft(), height + top, right, height + top);
                topIndicator.setWidth(width);
                topIndicator.setHeight(indicatorHeight);

                bottomIndicator.setBounds(left + currentTab.getPaddingLeft(), top + height, right, top + height);
                bottomIndicator.setWidth(width);
                bottomIndicator.setHeight(indicatorHeight);

            }
        });

        topIndicator.setPosition(0);
        topIndicator.setPosition(0);

        tab.setTag((texts.size() - 1) + "");
        contentLayout.addView(tab);
    }

    @Override
    public void animEnd() {
        if (onTabChangeListener != null) {
            onTabChangeListener.onTabChange(currentPosition);
        }
    }

    public TabText getTabAt(int position) {
        return texts.get(position);
    }

    public interface OnTabChangeListener {
        void onTabChange(int position);
    }

}
