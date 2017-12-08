package com.hzh.broken.line.chart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
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
    private Paint mBrokenLineCirclePaint;
    /**
     * 圆的半径
     */
    private float circlePointRadius = dp2px(3);
    /**
     * 图表距离View左边的距离
     */
    private float mBrokenLineLeft;
    /**
     * 边框的上边距
     */
    private float mBrokenLineTop = dp2px(10);
    /**
     * 边框的下边距
     */
    private float mBrokenLineBottom = dp2px(20);
    /**
     * 边框的右边距
     */
    private float mBrokenLinerRight = dp2px(0);
    /**
     * 图表绘制的宽度
     */
    private float mChartRegionWidth;
    /**
     * 图表绘制的高度
     */
    private float mChartRegionHeight;
    /**
     * 左边刻度文本数组
     */
    private String[] mLeftTextArr = new String[]{"极好", "不错", "还行", "平平", "不佳", ""};
    /**
     * 底部文本数组
     */
    private String[] mBottomTextArr = new String[]{"一", "二", "三", "四", "五", "六", "七"};
    /**
     * 数据值
     */
    private int[] mValues = new int[]{20, 80, 60, 80, 80, 40, 60};
    /**
     * 图表的最大值
     */
    private int mMaxValue = 100;
    /**
     * 折线表起点距离左边偏移
     */
    private final int mChartLeftOffset = dp2px(15);
    /**
     * 折现表终点距离右边的偏移
     */
    private final int mChartRightOffset = dp2px(22);
    /**
     * 底部文字距离表格的距离
     */
    private final int mBottomTextDistanceChart = dp2px(18);
    /**
     * 着重色原点角标
     */
    private int mBottomStressPointIndex = 1;
    /**
     * 着重色原点颜色
     */
    private int mStressPointColor = Color.parseColor("#8943C9");
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
     * 左边文字的颜色
     */
    private int leftTextColor = Color.parseColor("#5F5F5F");
    /**
     * 左边文字距离图表的距离
     */
    private int leftDistanceChart = dp2px(10);
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
    /**
     * 是否处理了padding
     */
    private boolean isHandlePadding = false;
    /**
     * 折线绘制范围宽度，图表的宽度减去起点偏移和终点偏移
     */
    private float mBrokenLineDrawRegionWidth;
    /**
     * 折线绘制范围高度，就是图表的高度
     */
    private float mBrokenLineDrawRegionHeight;
    /**
     * 行高，每个分隔线之间的距离
     */
    private float singleRowHeight;

    public BrokenLineChart(Context context) {
        super(context);
    }

    public BrokenLineChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        //左边文本画笔
        if (mLeftTextPaint == null) {
            mLeftTextPaint = new Paint();
            mLeftTextPaint.setColor(leftTextColor);
            mLeftTextPaint.setTextSize(sp2px(16));
            mLeftTextPaint.setTextAlign(Paint.Align.RIGHT);
            mLeftTextPaint.setStyle(Paint.Style.STROKE);
            mLeftTextPaint.setAntiAlias(true);
            mLeftTextPaint.setTextAlign(Paint.Align.RIGHT);
        }

        //底部文本画笔
        if (mBottomTextPaint == null) {
            mBottomTextPaint = new Paint();
            //底部文字
            mBottomTextPaint.setColor(bottomNomalTextColor);
            mBottomTextPaint.setTextSize(sp2px(15));
            mBottomTextPaint.setTextAlign(Paint.Align.RIGHT);
            mBottomTextPaint.setStyle(Paint.Style.STROKE);
            mBottomTextPaint.setAntiAlias(true);
        }

        //初始化边框线画笔
        if (mBorderLinePaint == null) {
            mBorderLinePaint = new Paint();
            mBorderLinePaint.setTextSize(20);
            mBorderLinePaint.setColor(leftTextColor);
            mBorderLinePaint.setStrokeWidth(dp2px(0.7f));
            mBorderLinePaint.setColor(dividerColor);
            mBorderLinePaint.setTextAlign(Paint.Align.RIGHT);
            mBorderLinePaint.setStyle(Paint.Style.STROKE);
            mBorderLinePaint.setAntiAlias(true);
        }

        //初始化折线画笔
        if (mBrokenLinePaint == null) {
            mBrokenLinePaint = new Paint();
            mBrokenLinePaint.setColor(brokenLineColor);
            mBrokenLinePaint.setStrokeWidth(2);
            mBrokenLinePaint.setStyle(Paint.Style.STROKE);
        }

        //折线之间的原点画笔
        if (mBrokenLineCirclePaint == null) {
            mBrokenLineCirclePaint = new Paint();
            mBrokenLineCirclePaint.setStyle(Paint.Style.STROKE);
            mBrokenLineCirclePaint.setAntiAlias(true);
        }

        //着重点到底部横坐标的竖线的画笔
        if (mStressPointDistanceBottomLinePaint == null) {
            mStressPointDistanceBottomLinePaint = new Paint();
            mStressPointDistanceBottomLinePaint.setStyle(Paint.Style.STROKE);
            mStressPointDistanceBottomLinePaint.setAntiAlias(true);
            mStressPointDistanceBottomLinePaint.setColor(stressPointDistanceBottomLineColor);
            mStressPointDistanceBottomLinePaint.setStrokeWidth(1.5f);
        }
    }

    /**
     * 初始化图表绘制范围
     */
    private void initNeedDrawWidthAndHeight() {
        //图表左侧距离View左边界的距离
        Rect leftTextRect = new Rect();
        mLeftTextPaint.getTextBounds(mLeftTextArr[0], 0, mLeftTextArr[0].length(), leftTextRect);
        mBrokenLineLeft = leftTextRect.right + dp2px(16);
        //图表的宽度，总宽度减去左边文字刻度到屏幕左侧和图表距离屏幕右侧的范围
        mChartRegionWidth = mViewWidth - mBrokenLineLeft - mBrokenLinerRight;
        //图表的高度，总高度减去
        mChartRegionHeight = mViewHeight - mBrokenLineTop - mBrokenLineBottom;

        //折线绘制范围宽度，图表的宽度减去起点偏移和终点偏移
        mBrokenLineDrawRegionWidth = mChartRegionWidth - mChartLeftOffset - mChartRightOffset;
        //折线绘制范围高度，就是图表的高度
        mBrokenLineDrawRegionHeight = mChartRegionHeight;
        //行高，每个刻度分隔线之间的距离
        singleRowHeight = mChartRegionHeight / mLeftTextArr.length - 1;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mViewHeight = getMeasuredHeight();
        mViewWidth = getMeasuredWidth();
        initPaint();
        initNeedDrawWidthAndHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isHandlePadding) {
            int paddingTop = getPaddingTop();
            int paddingBottom = getPaddingBottom();
            int paddingLeft = getPaddingLeft();
            int paddingRight = getPaddingRight();
            //处理padding
            mBrokenLineLeft += paddingLeft;
            mBrokenLinerRight -= paddingRight;
            isHandlePadding = !isHandlePadding;
        }

        //测试用，画了整个图标的区域
//        Paint paint = new Paint();
//        paint.setColor(Color.parseColor("#6600FF00"));
//        Rect rect = new Rect((int) mBrokenLineLeft + mChartLeftOffset, 0, (int) (mChartRegionWidth + mBrokenLineLeft + mChartLeftOffset), (int) mChartRegionHeight);
//        canvas.drawRect(rect, paint);

        //绘制图表横坐标每个刻度之间的横线
        DrawBorderLine(canvas);
        //绘制左边的刻度文字和刻度横线
        DrawLeftTextAndLine(canvas);
        //绘制底部刻度文字
        DrawBottomText(canvas);
        //根据数据点绘制折线
        DrawBrokenLine(canvas);
        //绘制折线之间的原点
        DrawLineCircle(canvas);
    }

    /**
     * 绘制图表横坐标每个刻度之间的横线
     */
    private void DrawBorderLine(Canvas canvas) {
        /**
         * 绘制边框竖线，暂不需要
         */
        //canvas.drawLine(mBrokenLineLeft, mBrokenLineTop - leftDistanceChart, mBrokenLineLeft, mViewHeight - mBrokenLineBottom, mBorderLinePaint);
        /**
         * 绘制边框横线
         */
        canvas.drawLine(mBrokenLineLeft, mViewHeight - mBrokenLineBottom, mViewWidth, mViewHeight - mBrokenLineBottom, mBorderLinePaint);
    }

    /**
     * 绘制左边的刻度文字和刻度横线
     *
     * @param canvas
     */
    private void DrawLeftTextAndLine(Canvas canvas) {
        /**对应的属性
         * drawLine(float startX, float startY, float stopX, float stopY, Paint paint);
         * startX   开始的x坐标
         * startY   开始的y坐标
         * stopX    结束的x坐标
         * stopY    结束的y坐标
         * paint    绘制该线的画笔
         * */
        /**
         * 绘制边框分段横线与分段文本
         */
        float averageHeight = mChartRegionHeight / (mLeftTextArr.length - 1);
        for (int i = 0; i < mLeftTextArr.length; i++) {
            float currentWidth = averageHeight * i;
            //画分隔横线
            canvas.drawLine(mBrokenLineLeft, currentWidth + mBrokenLineTop, mViewWidth - mBrokenLinerRight, currentWidth + mBrokenLineTop, mBorderLinePaint);
            Rect rect = new Rect();
            mLeftTextPaint.getTextBounds(mLeftTextArr[i], 0, mLeftTextArr[i].length(), rect);
            //画左边的纵坐标文字
            canvas.drawText(mLeftTextArr[i], mBrokenLineLeft - leftDistanceChart, currentWidth + mBrokenLineTop + (rect.height() / 3f), mLeftTextPaint);
        }
    }

    /**
     * 绘制底部刻度文字
     */
    private void DrawBottomText(Canvas canvas) {
        //测量文字宽度
        Rect bottomTextRect = new Rect();
        mBottomTextPaint.getTextBounds(mBottomTextArr[0], 0, mBottomTextArr[0].length(), bottomTextRect);
        //第一个文字距离View左边的距离,总宽度减去图标的宽度，再减去起点偏移的一半
        int firstToLeftDistance = (int) (mViewWidth - mChartRegionWidth - (mChartLeftOffset / 2));
        //平均宽度，表总宽度减去左边的偏移的一半和右边偏移的一半，再除以全部文字，求出每个文字需要的宽度
        float averageWidth = (mChartRegionWidth - mChartLeftOffset / 2 - mChartRightOffset / 2) / (mBottomTextArr.length);
        for (int i = 0; i < mBottomTextArr.length; i++) {
            float currentWidth = averageWidth * (i + 1);
            if (i == mBottomStressPointIndex) {
                mBottomTextPaint.setColor(bottomStressTextColor);
            } else {
                mBottomTextPaint.setColor(bottomNomalTextColor);
            }
            canvas.drawText(mBottomTextArr[i], currentWidth + firstToLeftDistance, mBrokenLineTop + mChartRegionHeight + mBottomTextDistanceChart + bottomTextRect.height(), mBottomTextPaint);
        }
    }

    /**
     * 根据值绘制折线
     */
    private void DrawBrokenLine(Canvas canvas) {
        //获取全部分值在图表上的坐标点数组
        Point[] points = getPoints();
        Path mPath = new Path();
        //设置成填充，才能后面设置渐变填充整个图形
        mBrokenLinePaint.setStyle(Paint.Style.FILL);
        float endY = mChartRegionHeight + mBrokenLineTop;
        //添加渐变
        Shader shader = new LinearGradient(0, 0, 0, endY, brokenCloseRegionStartColor, brokenCloseRegionEndColor, Shader.TileMode.CLAMP);
        mBrokenLinePaint.setShader(shader);
        mBrokenLinePaint.setAntiAlias(true);

        for (int i = 0; i < points.length; i++) {
            Point point = points[i];
            if (i == 0) {
                //第一个点，加画一条竖线到底部，Y轴就是整个图表的高度
                mPath.moveTo(point.x, endY);
                mPath.lineTo(point.x, point.y);
            } else if (i == points.length - 1) {
                //最后一条线，再加画一条线到底部,Y轴就是整个图表的高度
                mPath.lineTo(point.x, point.y);
                mPath.lineTo(point.x, endY);
            } else {
                //其他的线，按点连path。
                mPath.lineTo(point.x, point.y);
            }
        }
        mPath.close();
        //画整个折线图范围的图形
        canvas.drawPath(mPath, mBrokenLinePaint);

        //画着重点到底部的线，同样Y轴就是整个图表的高度
        Point stressPoint = points[mBottomStressPointIndex];
        canvas.drawLine(stressPoint.x, stressPoint.y, stressPoint.x
                , endY, mStressPointDistanceBottomLinePaint);
    }

    /**
     * 计算点坐标到底部横跨的高度
     *
     * @param pointY 点坐标的Y坐标
     * @return 跨越了多少个行间距
     */
    private float calculatePointStepHeight(float pointY) {
        //计算横跨的多少个的行高
        float lineNumber = mChartRegionHeight / pointY;
        //最后计算全部行高的距离
        return singleRowHeight * lineNumber;
    }

    /**
     * 绘制线上的圆
     */
    private void DrawLineCircle(Canvas canvas) {
        Point[] points = getPoints();
        for (int i = 0; i < points.length; i++) {
            //画圆点
            Point point = points[i];
            if (i == mBottomStressPointIndex) {
                mBrokenLineCirclePaint.setColor(mStressPointColor);
            } else {
                mBrokenLineCirclePaint.setColor(nomalPointColor);
            }
            mBrokenLineCirclePaint.setStyle(Paint.Style.FILL);
            /**
             * drawCircle(float cx, float cy, float radius, Paint paint)
             * cx 中间x坐标
             * xy 中间y坐标
             * radius 圆的半径
             * paint 绘制圆的画笔
             * */
            canvas.drawCircle(point.x, point.y, circlePointRadius, mBrokenLineCirclePaint);
        }
    }

    /**
     * 根据值计算在该值的 x，y坐标
     *
     * @return 该点在图表上的坐标
     */
    public Point[] getPoints() {
        //全部数值数组
        int[] valueList = mValues;
        //图表纵坐标的最大值
        int max = mMaxValue;
        //折线绘制范围的高度
        float height = mBrokenLineDrawRegionHeight;
        //折线绘制范围的宽度，就是图表宽度减去左偏移和右偏移
        float width = mBrokenLineDrawRegionWidth;

        //每个点在图表宽度上的平均距离
        float meanPointDistance = width / (valueList.length - 1);
        Point[] points = new Point[valueList.length];
        for (int i = 0; i < valueList.length; i++) {
            float value = valueList[i];
            //计算出左边的刻度，在高度上平均分的刻度高度，例如一共是100个刻度，10米总高度，100除以10。就是10个刻度，每个刻度1米。
            double meanHeight = (double) max / height;
            //获取要绘制的高度，刻度值除以平均高度
            float drawHeight = (float) (value / meanHeight);
            int pointY = (int) (height - drawHeight + mBrokenLineTop);
            //横坐标的坐标，就是每个点的距离乘以位置，（每个点之间的距离是固定的）
            //mBrokenLineLeft + mChartLeftOffset的意思是让点在图表偏移后的范围开始画点，因为我们的View绘制的坐标轴是以整个View的左上角开始
            int pointX = (int) (meanPointDistance * i) + (int) (mBrokenLineLeft + mChartLeftOffset);
            Point point = new Point(pointX, pointY);
            points[i] = point;
        }
        return points;
    }

    /**
     * dp 2 px
     *
     * @param dpVal 需要转换的dp值
     * @return 转换出来的px值
     */
    protected int dp2px(float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, getResources().getDisplayMetrics());
    }

    /**
     * sp 2 px
     *
     * @param spVal 需要转换的sp值
     * @return 转换出来的px值
     */
    protected int sp2px(float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, getResources().getDisplayMetrics());
    }

    /**
     * 配置类
     */
    public static class Config {
        /**
         * 左边刻度文本数组
         */
        private String[] leftTextArr;
        /**
         * 底部文本数组
         */
        private String[] bottomTextArr;
        /**
         * 数据值
         */
        private int[] values;
        /**
         * 图表的最大值
         */
        private int maxValue;
        /**
         * 着重色原点角标
         */
        private int bottomStressPointIndex = 0;
        /**
         * 着重色原点颜色
         */
        private int stressPointColor;
        /**
         * 着重色原点距离底部的画的线的颜色
         */
        private int stressPointDistanceBottomLineColor;
        /**
         * 普通点的颜色
         */
        private int nomalPointColor;
        /**
         * 折线颜色
         */
        private int brokenLineColor;
        /**
         * 左边文字的颜色
         */
        private int leftTextColor;
        /**
         * 底部日期着重字颜色
         */
        private int bottomStressTextColor;
        /**
         * 底部普通日期字颜色
         */
        private int bottomNomalTextColor;
        /**
         * 横线的颜色
         */
        private int dividerColor;
        /**
         * 封闭折线区域的图形渐变开始颜色
         */
        private int brokenCloseRegionStartColor;
        /**
         * 封闭折线区域的图形渐变结束颜色
         */
        private int brokenCloseRegionEndColor;

        private Config() {
        }

        public Config(Build build) {
            this.leftTextArr = build.leftTextArr;
            this.bottomTextArr = build.bottomTextArr;
            this.values = build.values;
            this.maxValue = build.maxValue;
            this.bottomStressPointIndex = build.bottomStressPointIndex;
            this.stressPointColor = build.stressPointColor;
            this.stressPointDistanceBottomLineColor = build.stressPointDistanceBottomLineColor;
            this.nomalPointColor = build.nomalPointColor;
            this.brokenLineColor = build.brokenLineColor;
            this.leftTextColor = build.leftTextColor;
            this.bottomStressTextColor = build.bottomStressTextColor;
            this.bottomNomalTextColor = build.bottomNomalTextColor;
            this.dividerColor = build.dividerColor;
            this.brokenCloseRegionStartColor = build.brokenCloseRegionStartColor;
            this.brokenCloseRegionEndColor = build.brokenCloseRegionEndColor;
        }

        public static Build newBuilder() {
            return new Build();
        }

        public String[] getLeftTextArr() {
            return leftTextArr;
        }

        public String[] getBottomTextArr() {
            return bottomTextArr;
        }

        public int[] getValues() {
            return values;
        }

        public int getMaxValue() {
            return maxValue;
        }

        public int getBottomStressPointIndex() {
            return bottomStressPointIndex;
        }

        public int getStressPointColor() {
            return stressPointColor;
        }

        public int getStressPointDistanceBottomLineColor() {
            return stressPointDistanceBottomLineColor;
        }

        public int getNomalPointColor() {
            return nomalPointColor;
        }

        public int getBrokenLineColor() {
            return brokenLineColor;
        }

        public int getLeftTextColor() {
            return leftTextColor;
        }

        public int getBottomStressTextColor() {
            return bottomStressTextColor;
        }

        public int getBottomNomalTextColor() {
            return bottomNomalTextColor;
        }

        public int getDividerColor() {
            return dividerColor;
        }

        public int getBrokenCloseRegionStartColor() {
            return brokenCloseRegionStartColor;
        }

        public int getBrokenCloseRegionEndColor() {
            return brokenCloseRegionEndColor;
        }

        public static class Build {
            private String[] leftTextArr;
            private String[] bottomTextArr;
            private int[] values;
            private int maxValue;
            private int bottomStressPointIndex = 0;
            private int stressPointColor;
            private int stressPointDistanceBottomLineColor;
            private int nomalPointColor;
            private int brokenLineColor;
            private int leftTextColor;
            private int bottomStressTextColor;
            private int bottomNomalTextColor;
            private int dividerColor;
            private int brokenCloseRegionStartColor;
            private int brokenCloseRegionEndColor;

            public Build setLeftTextArr(String[] leftTextArr) {
                this.leftTextArr = leftTextArr;
                return this;
            }

            public Build setBottomTextArr(String[] bottomTextArr) {
                this.bottomTextArr = bottomTextArr;
                return this;
            }

            public Build setValues(int[] values) {
                this.values = values;
                return this;
            }

            public Build setMaxValue(int maxValue) {
                this.maxValue = maxValue;
                return this;
            }

            public Build setBottomStressPointIndex(int bottomStressPointIndex) {
                this.bottomStressPointIndex = bottomStressPointIndex;
                return this;
            }

            public Build setStressPointColor(int stressPointColor) {
                this.stressPointColor = stressPointColor;
                return this;
            }

            public Build setStressPointDistanceBottomLineColor(int stressPointDistanceBottomLineColor) {
                this.stressPointDistanceBottomLineColor = stressPointDistanceBottomLineColor;
                return this;
            }

            public Build setNomalPointColor(int nomalPointColor) {
                this.nomalPointColor = nomalPointColor;
                return this;
            }

            public Build setBrokenLineColor(int brokenLineColor) {
                this.brokenLineColor = brokenLineColor;
                return this;
            }

            public Build setLeftTextColor(int leftTextColor) {
                this.leftTextColor = leftTextColor;
                return this;
            }

            public Build setBottomStressTextColor(int bottomStressTextColor) {
                this.bottomStressTextColor = bottomStressTextColor;
                return this;
            }

            public Build setBottomNomalTextColor(int bottomNomalTextColor) {
                this.bottomNomalTextColor = bottomNomalTextColor;
                return this;
            }

            public Build setDividerColor(int dividerColor) {
                this.dividerColor = dividerColor;
                return this;
            }

            public Build setBrokenCloseRegionStartColor(int brokenCloseRegionStartColor) {
                this.brokenCloseRegionStartColor = brokenCloseRegionStartColor;
                return this;
            }

            public Build setBrokenCloseRegionEndColor(int brokenCloseRegionEndColor) {
                this.brokenCloseRegionEndColor = brokenCloseRegionEndColor;
                return this;
            }

            public Config build() {
                return new Config(this);
            }
        }
    }

    /**
     * 设置配置，并且重新绘制
     *
     * @param config 配置类
     */
    public void configAndDrawView(Config config) {
        this.mLeftTextArr = config.getLeftTextArr();
        this.mBottomTextArr = config.getBottomTextArr();
        this.mValues = config.values;
        this.mMaxValue = config.getMaxValue();
        this.mBottomStressPointIndex = config.getBottomStressPointIndex();
        this.mStressPointColor = config.getStressPointColor();
        this.stressPointDistanceBottomLineColor = config.getStressPointDistanceBottomLineColor();
        this.nomalPointColor = config.getNomalPointColor();
        this.brokenLineColor = config.getBrokenLineColor();
        this.leftTextColor = config.getLeftTextColor();
        this.bottomStressTextColor = config.getBottomStressTextColor();
        this.bottomNomalTextColor = config.getBottomNomalTextColor();
        this.dividerColor = config.getDividerColor();
        this.brokenCloseRegionStartColor = config.getBrokenCloseRegionStartColor();
        this.brokenCloseRegionEndColor = config.getBrokenCloseRegionEndColor();
        postInvalidate();
    }
}