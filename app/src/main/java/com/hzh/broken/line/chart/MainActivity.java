package com.hzh.broken.line.chart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BrokenLineChart chart = (BrokenLineChart) findViewById(R.id.chart);
        //配置参数
        BrokenLineChart.Config config = BrokenLineChart.Config
                .newBuilder()
                .setLeftTextArr(mLeftTextArr)
                .setBottomTextArr(mBottomTextArr)
                .setValues(mValues)
                .setMaxValue(mMaxValue)
                .setBottomStressPointIndex(mBottomStressPointIndex)
                .setStressPointColor(mStressPointColor)
                .setStressPointDistanceBottomLineColor(stressPointDistanceBottomLineColor)
                .setNomalPointColor(nomalPointColor)
                .setBrokenLineColor(brokenLineColor)
                .setLeftTextColor(leftTextColor)
                .setBottomStressTextColor(bottomStressTextColor)
                .setBottomNomalTextColor(bottomNomalTextColor)
                .setDividerColor(dividerColor)
                .setBrokenCloseRegionStartColor(brokenCloseRegionStartColor)
                .setBrokenCloseRegionEndColor(brokenCloseRegionEndColor)
                .build();
        chart.configAndDrawView(config);
    }
}
