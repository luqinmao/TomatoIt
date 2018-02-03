package com.lqm.tomatoit.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.util.AttributeSet;
import android.view.View;

import com.lqm.tomatoit.R;
import com.lqm.tomatoit.util.UIUtils;


/**
 * autour: lqm
 * desc: 水波纹效果View
 *  y = Asin(wx+b)+h：
 *  A:振幅两个山峰最大的高度.如果A越大两个山峰越高和越低
 *  h:你正弦曲线和y轴相交点.(影响正弦图初始高度的位置)
 *  b:初相会让你图片向x轴平移
 *  src: http://blog.csdn.net/qfanmingyiq/article/details/53038262
 */
public class DynamicWave extends View {
    // y = Asin(wx+b)+h
    private static final float STRETCH_FACTOR_A = 20; //振幅高度
    private static final int OFFSET_Y = -50;
    // 第一条水波移动速度
    private static final int TRANSLATE_X_SPEED_ONE = 7;
    // 第二条水波移动速度
    private static final int TRANSLATE_X_SPEED_TWO = 5;
    // 第三条水波移动速度
    private static final int TRANSLATE_X_SPEED_THREE= 3;
    /**
     * 周期
     */
    private float mCycleFactorW;

    private int mTotalWidth, mTotalHeight;

    /**
     * 原始波纹的y值
     */
    private float[] mYPositions;
    /**
     * 第一个波纹移动速度的像素值
     */
    private int mXOffsetSpeedOne;
    private int mXOffsetSpeedTwo;
    private int mXOffsetSpeedThree;
    /**
     *  第一个波纹当前移动的距离
     */
    private int mXOneOffset;
    private int mXTwoOffset;
    private int mXThreeOffset;


    private Paint mWavePaint;
    private DrawFilter mDrawFilter;

    public DynamicWave(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 将dp转化为px，用于控制不同分辨率上移动速度基本一致
        mXOffsetSpeedOne = UIUtils.dp2px(TRANSLATE_X_SPEED_ONE);
        mXOffsetSpeedTwo = UIUtils.dp2px(TRANSLATE_X_SPEED_TWO);
        mXOffsetSpeedThree = UIUtils.dp2px(TRANSLATE_X_SPEED_THREE);

        // 初始绘制波纹的画笔
        mWavePaint = new Paint();
        // 去除画笔锯齿
        mWavePaint.setAntiAlias(true);
        // 设置风格为实线
        mWavePaint.setStyle(Paint.Style.FILL);
        // 设置画笔颜色
        mWavePaint.setColor(getResources().getColor(R.color.white));
        mDrawFilter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 从canvas层面去除绘制时锯齿
        canvas.setDrawFilter(mDrawFilter);
        int yHeight = 100; //控制波纹绘制的y在屏幕的位置,动态改变这个变量，可以形成波纹上升下降效果
        for(int i=0,j=0,k=0,f=0;i<mTotalWidth;i++){
            if(i+mXOneOffset<mTotalWidth){
                canvas.drawLine(i,mTotalHeight-mYPositions[mXOneOffset+i]-yHeight,i,mTotalHeight,mWavePaint);
            }else {
                canvas.drawLine(i,mTotalHeight-mYPositions[j]-yHeight,i,mTotalHeight,mWavePaint);
                j++;
            }

            if(i+mXTwoOffset<mTotalWidth){
                canvas.drawLine(i,mTotalHeight-mYPositions[mXTwoOffset+i]-yHeight,i,mTotalHeight,mWavePaint);
            }else {
                canvas.drawLine(i,mTotalHeight-mYPositions[k]-yHeight,i,mTotalHeight,mWavePaint);
                k++;
            }

            if(i+mXThreeOffset<mTotalWidth){
                canvas.drawLine(i,mTotalHeight-mYPositions[mXThreeOffset+i]-yHeight,i,mTotalHeight,mWavePaint);
            }else {
                canvas.drawLine(i,mTotalHeight-mYPositions[f]-yHeight,i,mTotalHeight,mWavePaint);
                f++;
            }
        }

        // 改变两条波纹的移动点
        mXOneOffset += mXOffsetSpeedOne;
        mXTwoOffset += mXOffsetSpeedTwo;
        mXThreeOffset += mXOffsetSpeedThree;


        // 如果已经移动到结尾处，则重头记录
        if (mXOneOffset >= mTotalWidth) {
            mXOneOffset = 0;
        }
        if (mXTwoOffset > mTotalWidth) {
            mXTwoOffset = 0;
        }

        if (mXThreeOffset > mTotalWidth) {
            mXThreeOffset = 0;
        }

        // 引发view重绘，一般可以考虑延迟20-30ms重绘，空出时间片
        postInvalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // 记录下view的宽高
        mTotalWidth = w;
        mTotalHeight = h;
        // 用于保存原始波纹的y值
        mYPositions = new float[mTotalWidth];
        // 将周期定为view总宽度
        mCycleFactorW = (float) (2 * Math.PI / mTotalWidth);

        // 根据view总宽度得出所有对应的y值
        for (int i = 0; i < mTotalWidth; i++) {
            mYPositions[i] = (float) (STRETCH_FACTOR_A * Math.sin(mCycleFactorW * i) + OFFSET_Y);
        }
    }
}
