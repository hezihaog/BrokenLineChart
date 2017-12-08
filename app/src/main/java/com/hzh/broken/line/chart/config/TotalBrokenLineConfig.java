package com.hzh.broken.line.chart.config;

import android.graphics.Color;

/**
 * Package: com.hzh.broken.line.chart.config
 * FileName: totalBrokenConfig
 * Date: on 2017/12/8  下午4:57
 * Auther: zihe
 * Descirbe: 全部类型
 * Email: hezihao@linghit.com
 */

public abstract class TotalBrokenLineConfig extends BaseBrokenLineConfig {
    /**
     * 着重色原点角标
     */
    @Override
    public int onGetBottomStressPointIndex() {
        return 1;
    }

    /**
     * 着重色圆点颜色
     */
    @Override
    public int onGetStressPointColor() {
        return Color.parseColor("#8943C9");
    }

    /**
     * 普通点的颜色
     */
    @Override
    public int onGetNomalPointColor() {
        return Color.parseColor("#C17DFF");
    }

    /**
     * 着重色原点距离底部的画的线的颜色
     */
    @Override
    public int onGetStressPointDistanceBottomLineColor() {
        return Color.parseColor("#8943C9");
    }

    /**
     * 折线颜色
     */
    @Override
    public int onGetBrokenLineColor() {
        return Color.parseColor("#8943C9");
    }

    /**
     * 底部日期着重字颜色
     */
    @Override
    public int onGetBottomStressTextColor() {
        return Color.parseColor("#8943C9");
    }

    /**
     * 封闭折线区域的图形渐变开始颜色
     */
    @Override
    public int onGetBrokenCloseRegionStartColor() {
        return Color.parseColor("#8943c9");
    }

    /**
     * 封闭折线区域的图形渐变结束颜色
     */
    @Override
    public int onGetBrokenCloseRegionEndColor() {
        return Color.parseColor("#D1B6EA");
    }
}
