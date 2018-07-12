package com.project.ydy.scrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

/**
 * **************************************************
 * 文件名称 : CustomScrollView
 * 作    者 : Created by ydy
 * 创建时间 : 2018/7/12 11:40
 * 文件描述 : 监听ScrollView滑动状态、方向等
 * 注意事项 :
 * 修改历史 : 2018/7/12 1.00 初始版本
 * **************************************************
 */
public class CustomScrollView extends ScrollView {

    public CustomScrollView(Context context) {
        super(context, null);
    }

    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Runnable延迟执行的时间
     */
    private long delayMillis = 100;
    /**
     * 上次滑动的时间
     */
    private long lastScrollMillis = -1;


    private Runnable scrollerTask = new Runnable() {
        @Override
        public void run() {
            long currentTime = System.currentTimeMillis();
            if ((currentTime - lastScrollMillis) > delayMillis) {
                lastScrollMillis = -1;
                if (null != listener) {
                    listener.onScrollEnd();
                }
            } else {
                postDelayed(this, delayMillis);
            }
        }
    };

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        /*
         getScrollY()：表示距离Y轴的滚动距离；
         getHeight()：表示ScrollView的高度；
         getHeight() + getScrollY()最大时：等于它内容的高度
         getChildCount()为ScrollView的子View个数(其实就是1个)；
         getChildAt(getChildCount() - 1) = getChildAt(0)：得到ScrollView的childView
         childView.getMeasuredHeight()：表示子View的高度

         判断滑动到顶部：getScrollY() == 0
         判断滑动到底部：childView.getMeasuredHeight() = getScrollY() + getHeight()
        */
        View childView = (View) getChildAt(getChildCount() - 1);
        int d = childView.getBottom();
        if (d == (getHeight() + getScrollY())) {
            if (null != listener) {
                listener.onScrollBottom();
            }
        }
        if (getScrollY() == 0) {
            if (null != listener) {
                listener.onScrollTop();
            }
        }
        if ((t - oldt) < 0) {
            if (null != listener) {
                listener.onScrollDown();
            }
        } else {
            if (null != listener) {
                listener.onScrollUp();
            }
        }
        if (lastScrollMillis == -1) {
            if (null != listener) {
                listener.onScrollStart();
            }
            postDelayed(scrollerTask, delayMillis);
        }
        // 更新ScrollView的滑动时间
        lastScrollMillis = System.currentTimeMillis();
    }

    private OnScrollStateListener listener = null;

    public void setOnScrollStateListener(OnScrollStateListener listener) {
        this.listener = listener;
    }

    public interface OnScrollStateListener {
        /**
         * 滑动开始
         */
        void onScrollStart();

        /**
         * 滑动结束
         */
        void onScrollEnd();

        /**
         * 向上滑动
         */
        void onScrollUp();

        /**
         * 向下滑动
         */
        void onScrollDown();

        /**
         * 滑动到顶
         */
        void onScrollTop();

        /**
         * 滑动到底
         */
        void onScrollBottom();
    }

}
