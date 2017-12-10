package com.hzh.broken.line.chart.config;

import android.graphics.Color;

/**
 * Package: com.hzh.broken.line.chart.config
 * FileName: LoveBrokenLineConfig
 * Date: on 2017/12/8  下午5:57
 * Auther: zihe
 * Descirbe: 情感类型
 * Email: hezihao@linghit.com
 */

public class LoveBrokenLineConfig extends BaseBrokenLineConfig {

    @Override
    public int onGetBottomStressPointIndex() {
        return 4;
    }

    @Override
    public int onGetStressPointColor() {
        return Color.parseColor("#E32D67");
    }

    @Override
    public int onGetStressPointDistanceBottomLineColor() {
        return Color.parseColor("#E32D67");
    }

    @Override
    public int onGetNomalPointColor() {
        return Color.parseColor("#FFC4D5");
    }

    @Override
    public int onGetBrokenLineColor() {
        return Color.parseColor("#E32D67");
    }

    @Override
    public int onGetBottomStressTextColor() {
        return Color.parseColor("#E32D67");
    }

    @Override
    public int onGetBrokenCloseRegionStartColor() {
        return Color.parseColor("#FF669B");
    }

    @Override
    public int onGetBrokenCloseRegionEndColor() {
        return Color.parseColor("#FFBCD3");
    }
}