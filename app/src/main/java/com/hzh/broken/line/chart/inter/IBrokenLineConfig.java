package com.hzh.broken.line.chart.inter;

/**
 * Package: com.hzh.broken.line.chart.inter
 * FileName: IBrokenConfig
 * Date: on 2017/12/8  下午4:40
 * Auther: zihe
 * Descirbe: 折线图配置接口
 * Email: hezihao@linghit.com
 */

public interface IBrokenLineConfig {
    //***************************** 子类需要重写的配置方法 *******************************
    /**
     * 左边刻度文本数组
     */
    String[] onGetLeftTextArr();

    /**
     * 底部文本数组
     */
    String[] onGetBottomTextArr();

    /**
     * 点数据值数组
     */
    int[] onGetValues();

    /**
     * 点坐标数值最大值
     */
    int onGetMaxValue();

    /**
     * 着色点角标
     */
    int onGetBottomStressPointIndex();

    /**
     * 着色点颜色
     */
    int onGetStressPointColor();

    /**
     * 着重色原点距离底部的画的线的颜色
     */
    int onGetStressPointDistanceBottomLineColor();

    /**
     * 普通点的颜色
     */
    int onGetNomalPointColor();

    /**
     * 折线颜色
     */
    int onGetBrokenLineColor();

    /**
     * 左边文字的颜色
     */
    int onGetLeftTextColor();

    /**
     * 底部日期着重字颜色
     */
    int onGetBottomStressTextColor();

    /**
     * 底部普通日期字颜色
     */
    int onGetBottomNomalTextColor();

    /**
     * 横线的颜色
     */
    int onGetDividerColor();

    /**
     * 封闭折线区域的图形渐变开始颜色
     */
    int onGetBrokenCloseRegionStartColor();

    /**
     * 封闭折线区域的图形渐变结束颜色
     */
    int onGetBrokenCloseRegionEndColor();

    //***************************** get方法 *******************************

    String[] getLeftTextArr();

    String[] getBottomTextArr();

    int[] getValues();

    int getMaxValue();

    int getBottomStressPointIndex();

    int getStressPointColor();

    int getStressPointDistanceBottomLineColor();

    int getNomalPointColor();

    int getBrokenLineColor();

    int getLeftTextColor();

    int getBottomStressTextColor();

    int getBottomNomalTextColor();

    int getDividerColor();

    int getBrokenCloseRegionStartColor();

    int getBrokenCloseRegionEndColor();
}
