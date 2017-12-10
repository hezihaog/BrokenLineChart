package com.hzh.broken.line.chart.config;

import android.graphics.Color;

import com.hzh.broken.line.chart.inter.IBrokenLineConfig;

/**
 * Package: com.hzh.broken.line.chart.config
 * FileName: BaseBrokenConfig
 * Date: on 2017/12/8  下午4:37
 * Auther: zihe
 * Descirbe: 基础折线图配置抽象类，只定义基础的，子类去定义具体的折线颜色等属性
 * Email: hezihao@linghit.com
 */

public abstract class BaseBrokenLineConfig implements IBrokenLineConfig {
    /**
     * 左边刻度文本数组
     */
    private String[] leftTextArr;
    /**
     * 底部文本数组
     */
    private String[] bottomTextArr;
    /**
     * 图表的最大值
     */
    private int maxValue;
    /**
     * 着重色原点角标
     */
    private int bottomStressPointIndex;
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

    /**
     * 构造方法进行调用接口中的方法，将所有属性赋值到本地
     */
    public BaseBrokenLineConfig() {
        this.leftTextArr = onGetLeftTextArr();
        this.bottomTextArr = onGetBottomTextArr();
        this.maxValue = onGetMaxValue();
        this.bottomStressPointIndex = onGetBottomStressPointIndex();
        this.stressPointColor = onGetStressPointColor();
        this.stressPointDistanceBottomLineColor = onGetStressPointDistanceBottomLineColor();
        this.nomalPointColor = onGetNomalPointColor();
        this.brokenLineColor = onGetBrokenLineColor();
        this.leftTextColor = onGetLeftTextColor();
        this.bottomStressTextColor = onGetBottomStressTextColor();
        this.bottomNomalTextColor = onGetBottomNomalTextColor();
        this.dividerColor = onGetDividerColor();
        this.brokenCloseRegionStartColor = onGetBrokenCloseRegionStartColor();
        this.brokenCloseRegionEndColor = onGetBrokenCloseRegionEndColor();
    }

    //***************************** 抽象父类重写公用方法 *******************************

    /**
     * 左边刻度文本数组
     */
    @Override
    public String[] onGetLeftTextArr() {
        return new String[]{"极好", "不错", "还行", "平平", "不佳", ""};
    }

    /**
     * 底部文本数组
     */
    @Override
    public String[] onGetBottomTextArr() {
        return new String[]{"一", "二", "三", "四", "五", "六", "七"};
    }

    /**
     * 图表的最大值
     */
    @Override
    public int onGetMaxValue() {
        return 100;
    }

    /**
     * 左边文字的颜色
     */
    @Override
    public int onGetLeftTextColor() {
        return Color.parseColor("#5F5F5F");
    }

    /**
     * 底部普通日期字颜色
     */
    @Override
    public int onGetBottomNomalTextColor() {
        return Color.parseColor("#B7B7B7");
    }

    /**
     * 横线的颜色
     */
    @Override
    public int onGetDividerColor() {
        return Color.parseColor("#B7B7B7");
    }

    //***************************** 抽象父类统一重写 get方法 *******************************

    @Override
    public String[] getLeftTextArr() {
        return leftTextArr;
    }

    @Override
    public String[] getBottomTextArr() {
        return bottomTextArr;
    }

    @Override
    public int getMaxValue() {
        return maxValue;
    }

    @Override
    public int getBottomStressPointIndex() {
        return bottomStressPointIndex;
    }

    @Override
    public int getStressPointColor() {
        return stressPointColor;
    }

    @Override
    public int getStressPointDistanceBottomLineColor() {
        return stressPointDistanceBottomLineColor;
    }

    @Override
    public int getNomalPointColor() {
        return nomalPointColor;
    }

    @Override
    public int getBrokenLineColor() {
        return brokenLineColor;
    }

    @Override
    public int getLeftTextColor() {
        return leftTextColor;
    }

    @Override
    public int getBottomStressTextColor() {
        return bottomStressTextColor;
    }

    @Override
    public int getBottomNomalTextColor() {
        return bottomNomalTextColor;
    }

    @Override
    public int getDividerColor() {
        return dividerColor;
    }

    @Override
    public int getBrokenCloseRegionStartColor() {
        return brokenCloseRegionStartColor;
    }

    @Override
    public int getBrokenCloseRegionEndColor() {
        return brokenCloseRegionEndColor;
    }
}
