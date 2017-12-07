package com.hzh.broken.line.chart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

public class BrokenLineChart extends View {
    /**
     * View宽度
     */
    private int mViewWidth;
    /**
     * View高度
     */
    private int mViewHeight;
    /**
     * 边框线画笔
     */
    private Paint mBorderLinePaint;
    /**
     * 左边文本画笔
     */
    private Paint mLeftTextPaint;
    /**
     * 底部文本画笔
     */
    private Paint mBottomTextPaint;
    /**
     * 要绘制的折线线画笔
     */
    private Paint mBrokenLinePaint;
    /**
     * 着重点到底部的线的画笔
     */
    private Paint mStressPointDistanceBottomLinePaint;
    /**
     * 圆画笔
     */
    private Paint mCirclePaint;
    /**
     * 圆的半径
     */
    private float circlePointRadius = dp2px(3);
    /**
     * 边框的左边距
     */
    private float mBrokenLineLeft = dp2px(50);
    /**
     * 边框的上边距
     */
    private float mBrokenLineTop = 40;
    /**
     * 边框的下边距
     */
    private float mBrokenLineBottom = 40;
    /**
     * 边框的右边距
     */
    private float mBrokenLinerRight = 20;
    /**
     * 需要绘制的宽度
     */
    private float mNeedDrawWidth;
    /**
     * 需要绘制的高度
     */
    private float mNeedDrawHeight;
    /**
     * 左边刻度文本数组
     */
    private String[] valueTextArr = new String[]{"极好", "不错", "还行", "平平", "不佳", ""};
    /**
     * 底部文本数组
     */
    private String[] bottomTextArr = new String[]{"一", "二", "三", "四", "五", "六", "七"};
    /**
     * 数据值
     */
    private int[] value = new int[]{20, 80, 60, 80, 80, 40, 60};

    /**
     * 图表的最大值
     */
    private int maxVlaue = 100;

    /**
     * 折现起点偏移
     */
    private final int offset = dp2px(15);
    /**
     * 着重色原点角标
     */
    private int bottomStressPointIndex = 1;
    /**
     * 着重色原点颜色
     */
    private int stressPointColor = Color.parseColor("#8943C9");
    /**
     * 着重色原点距离底部的画的线的颜色
     */
    private int stressPointDistanceBottomLineColor = Color.parseColor("#8943C9");
    /**
     * 普通点的颜色
     */
    private int nomalPointColor = Color.parseColor("#C17DFF");
    /**
     * 折线颜色
     */
    private int brokenLineColor = Color.parseColor("#8943C9");
    /**
     * 底部普通日期字颜色
     */
    private int leftTextColor = Color.parseColor("#5F5F5F");
    /**
     * 底部日期着重字颜色
     */
    private int bottomStressTextColor = Color.parseColor("#8943C9");
    /**
     * 底部普通日期字颜色
     */
    private int bottomNomalTextColor = Color.parseColor("#B7B7B7");
    /**
     * 横线的颜色
     */
    private int dividerColor = Color.parseColor("#B7B7B7");
    /**
     * 封闭折线区域的图形渐变开始颜色
     */
    private int brokenCloseRegionStartColor = Color.parseColor("#8943c9");
    /**
     * 封闭折线区域的图形渐变结束颜色
     */
    private int brokenCloseRegionEndColor = Color.parseColor("#4DD1B6EA");

    private int paddingLeft;
    private int paddingRight;

    public BrokenLineChart(Context context) {
        super(context);
    }

    public BrokenLineChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mViewHeight = getMeasuredHeight() - dp2px(7);
        mViewWidth = getMeasuredWidth();
        initNeedDrawWidthAndHeight();
        initPaint();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        /**初始化左边文本画笔*/
        if (mLeftTextPaint == null) {
            mLeftTextPaint = new Paint();
        }
        initPaint(mLeftTextPaint);
        /**
         * 初始化底部文本画笔
         */
        if (mBottomTextPaint == null) {
            mBottomTextPaint = new Paint();
        }
        initPaint(mBottomTextPaint);

        /**初始化边框线画笔*/
        if (mBorderLinePaint == null) {
            mBorderLinePaint = new Paint();
            mBorderLinePaint.setTextSize(20);
        }
        initPaint(mBorderLinePaint);

        /**初始化折线画笔*/
        if (mBrokenLinePaint == null) {
            mBrokenLinePaint = new Paint();
        }
        initPaint(mBrokenLinePaint);

        if (mCirclePaint == null) {
            mCirclePaint = new Paint();
        }
        initPaint(mCirclePaint);

        if (mStressPointDistanceBottomLinePaint == null) {
            mStressPointDistanceBottomLinePaint = new Paint();
        }
        initPaint(mStressPointDistanceBottomLinePaint);
    }

    /**
     * 初始化画笔默认属性
     */
    private void initPaint(Paint paint) {
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
    }

    /**
     * 初始化绘制折线图的宽高
     */
    private void initNeedDrawWidthAndHeight() {
        mNeedDrawWidth = mViewWidth - mBrokenLineLeft - mBrokenLinerRight;
        mNeedDrawHeight = mViewHeight - mBrokenLineTop - mBrokenLineBottom;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (paddingLeft == 0 && paddingRight == 0) {
            int paddingTop = getPaddingTop();
            int paddingBottom = getPaddingBottom();
            paddingLeft = getPaddingLeft();
            paddingRight = getPaddingRight();
            mBrokenLineLeft += paddingLeft;
            mBrokenLinerRight -= paddingRight;
            mNeedDrawWidth += paddingTop;
            mNeedDrawHeight -= paddingBottom;
        }

        /**绘制边框线和边框文本*/
        DrawBorderLineAndText(canvas);

        /**根据数据绘制线*/
        DrawBrokenLine(canvas);
        DrawLineCircle(canvas);
    }

    /**
     * 绘制边框线和边框文本
     */
    private void DrawBorderLineAndText(Canvas canvas) {
        /**对应的属性
         * drawLine(float startX, float startY, float stopX, float stopY, Paint paint);
         * startX   开始的x坐标
         * startY   开始的y坐标
         * stopX    结束的x坐标
         * stopY    结束的y坐标
         * paint    绘制该线的画笔
         * */
        mBorderLinePaint.setColor(leftTextColor);
        //线宽
        mBorderLinePaint.setStrokeWidth(dp2px(0.7f));
        mBorderLinePaint.setColor(dividerColor);
        mBorderLinePaint.setTextAlign(Paint.Align.RIGHT);
        //左边文字
        mLeftTextPaint.setColor(leftTextColor);
        mLeftTextPaint.setTextSize(sp2px(16));
        mLeftTextPaint.setTextAlign(Paint.Align.RIGHT);
        //底部文字
        mBottomTextPaint.setColor(bottomNomalTextColor);
        mBottomTextPaint.setTextSize(sp2px(15));
        mBottomTextPaint.setTextAlign(Paint.Align.RIGHT);

        /**绘制边框竖线*/
        //canvas.drawLine(mBrokenLineLeft, mBrokenLineTop - 10, mBrokenLineLeft, mViewHeight - mBrokenLineBottom, mBorderLinePaint);
        /**绘制边框横线*/
        canvas.drawLine(mBrokenLineLeft, mViewHeight - mBrokenLineBottom, mViewWidth, mViewHeight - mBrokenLineBottom, mBorderLinePaint);

        /**绘制边框分段横线与分段文本*/
        float averageHeight = mNeedDrawHeight / (valueTextArr.length - 1);
        for (int i = 0; i < valueTextArr.length; i++) {
            float nowadayHeight = averageHeight * i;
            //画横线
            canvas.drawLine(mBrokenLineLeft, nowadayHeight + mBrokenLineTop, mViewWidth - mBrokenLinerRight, nowadayHeight + mBrokenLineTop, mBorderLinePaint);
            //画左边的文字
            canvas.drawText(valueTextArr[i], mBrokenLineLeft - dp2px(10), nowadayHeight + mBrokenLineTop + dp2px(12), mLeftTextPaint);
        }
        float averageWidth = mNeedDrawWidth / (bottomTextArr.length - 1);
        for (int i = 0; i < bottomTextArr.length; i++) {
            float nowadayWidght = averageWidth * i;
            if (i == bottomStressPointIndex) {
                mBottomTextPaint.setColor(bottomStressTextColor);
            } else {
                mBottomTextPaint.setColor(bottomNomalTextColor);
            }
            canvas.drawText(bottomTextArr[i], nowadayWidght + mBrokenLineLeft + dp2px(12) + offset, mNeedDrawHeight + mBrokenLineTop + dp2px(20), mBottomTextPaint);
        }
    }

    /**
     * 根据值绘制折线
     */
    private void DrawBrokenLine(Canvas canvas) {
        Point[] points = getPoints(value, mNeedDrawHeight, mNeedDrawWidth, maxVlaue, mBrokenLineLeft, mBrokenLineTop);
        Path mPath = new Path();
        mBrokenLinePaint.setColor(brokenLineColor);
        mBrokenLinePaint.setStrokeWidth(2);
        //设置成填充，才能后面设置渐变填充整个图形
        mBrokenLinePaint.setStyle(Paint.Style.FILL);
        //画上渐变
        Shader shader = new LinearGradient(0, 0, 0, mNeedDrawHeight, brokenCloseRegionStartColor, brokenCloseRegionEndColor, Shader.TileMode.CLAMP);
        mBrokenLinePaint.setShader(shader);

        float singleDividerHeight = mNeedDrawHeight / valueTextArr.length - 1;
        for (int i = 0; i < points.length; i++) {
            Point point = points[i];
            if (i == 0) {
                //第一个点，加画一条竖线到底部
                mPath.moveTo(point.x, (int) (point.y + mNeedDrawHeight / valueTextArr.length) + dp2px(7));
                mPath.lineTo(point.x, point.y);
            } else if (i == points.length - 1) {
                //最后一条线，再加画一条线到底部
                mPath.lineTo(point.x, point.y);
                mPath.lineTo(point.x, (int) (point.y + (mNeedDrawHeight - (calculateSingleDividerHeight(point.y) * singleDividerHeight)) - dp2px(8.7f)));
            } else {
                //其他的线，按点连path。
                mPath.lineTo(point.x, point.y);
            }
        }
        //画整个折线图范围的图形
        canvas.drawPath(mPath, mBrokenLinePaint);

        //画着重点到底部的线
        mStressPointDistanceBottomLinePaint.setColor(stressPointDistanceBottomLineColor);
        Point stressPoint = points[bottomStressPointIndex];
        float v = mViewHeight - calculateSingleDividerHeight(stressPoint.y) * singleDividerHeight + dp2px(10.5f);
        int stopY = (int) (stressPoint.y + v);
        canvas.drawLine(stressPoint.x, stressPoint.y, stressPoint.x
                , stopY, mStressPointDistanceBottomLinePaint);
    }

    /**
     * 计算点坐标到整个图标高度距离跨越的行间距
     *
     * @param pointY 点坐标的Y坐标
     * @return 跨越了多少个行间距
     */
    private float calculateSingleDividerHeight(float pointY) {
        return mViewHeight / pointY;
    }

    /**
     * 绘制线上的圆
     */
    private void DrawLineCircle(Canvas canvas) {
        Point[] points = getPoints(value, mNeedDrawHeight, mNeedDrawWidth, maxVlaue, mBrokenLineLeft, mBrokenLineTop);
        for (int i = 0; i < points.length; i++) {
            //画圆点
            Point point = points[i];
            if (i == bottomStressPointIndex) {
                mCirclePaint.setColor(stressPointColor);
            } else {
                mCirclePaint.setColor(nomalPointColor);
            }
            mCirclePaint.setStyle(Paint.Style.FILL);
            /**
             * drawCircle(float cx, float cy, float radius, Paint paint)
             * cx 中间x坐标
             * xy 中间y坐标
             * radius 圆的半径
             * paint 绘制圆的画笔
             * */
            canvas.drawCircle(point.x, point.y, circlePointRadius, mCirclePaint);
        }
    }

    /**
     * 根据值计算在该值的 x，y坐标
     */
    public Point[] getPoints(int[] values, float height, float width, int max, float left, float top) {
        float leftPading = (width - offset * 2) / (values.length - 1);//绘制边距
        Point[] points = new Point[values.length];
        for (int i = 0; i < values.length; i++) {
            float value = values[i];
            //计算每点高度所以对应的值
            double mean = (double) max / height;
            //获取要绘制的高度
            float drawHeight = (float) (value / mean);
            int pointY = (int) (height + top - drawHeight);
            int pointX = (int) (leftPading * i + left) + offset;
            Point point = new Point(pointX, pointY);
            points[i] = point;
        }
        return points;
    }

    /**
     * dp 2 px
     *
     * @param dpVal
     */
    protected int dp2px(float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, getResources().getDisplayMetrics());
    }

    /**
     * sp 2 px
     *
     * @param spVal
     * @return
     */
    protected int sp2px(float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, getResources().getDisplayMetrics());
    }
}