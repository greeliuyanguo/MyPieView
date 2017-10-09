package com.bigdata.gree.pieview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.bigdata.gree.pieview.model.PieData;

import java.util.List;

/**
 * Author:liuyanguo
 * Date:2017/10/8
 * Time:9:19
 * Description: Followed by the PieView of author GcsSloop
 */

public class PieView extends View {

    private int mWidth;
    private int mHeight;

    private float mStartAngle;

    private int[] mColors = new int[]{
            0xFFCCFF00,
            0xFF6495ED,
            0xFFE32636,
            0xFF800000,
            0xFF808000,
            0xFFFF8C69,
            0xFF808080,
            0xFFE6B800,
            0xFF7CFC00
    };

    private Paint mPaint = new Paint();

    private List<PieData> mData;

    public PieView(Context context) {
        this(context, null);
    }

    public PieView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initPaint();
    }

    private void initPaint() {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (null == mData) {
            return;
        }

        float currentAngle = mStartAngle;
        float r = (float) (Math.min(mHeight, mWidth) / 2 * 0.8);
        canvas.translate(mWidth / 2, mHeight / 2);
        RectF rect = new RectF(-r, -r, r, r);

        for (int i = 0; i < mData.size(); i++) {
            mPaint.setColor(mData.get(i).getColor());
            canvas.drawArc(rect, currentAngle, mData.get(i).getAngle(), true, mPaint);
            currentAngle += mData.get(i).getAngle();
        }
    }

    /**
     * Supplying interface for setting start angle of invalidate.
     *
     * @param startAngle
     */
    public void setStartAngle(int startAngle) {
        this.mStartAngle = startAngle;
        invalidate(); //refresh
    }

    /**
     * Supplying interface for setting list data of invalidate.
     *
     * @param data
     */
    public void setData(List<PieData> data) {
        this.mData = data;
        initPieValue();
        invalidate(); //refresh
    }

    /**
     * Initialize data.
     */
    private void initPieValue() {
        if (null == mData || mData.size() == 0) {
            return;
        }

        float sumValue = 0f;
        for (int i = 0; i < mData.size(); i++) {
            sumValue += mData.get(i).getValue();

        }

        for (int i = 0; i < mData.size(); i++) {
            PieData pieData = mData.get(i);
            float percentage = pieData.getValue() / sumValue;
            float angle = percentage * 360;
            int colorIndex = i % mColors.length;

            pieData.setPercentage(percentage);
            pieData.setAngle(angle);
            pieData.setColor(mColors[colorIndex]);
        }
    }
}
