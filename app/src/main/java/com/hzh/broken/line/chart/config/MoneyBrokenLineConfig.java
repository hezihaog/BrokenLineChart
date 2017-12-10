package com.hzh.broken.line.chart.config;

import android.graphics.Color;

/**
 * Package: com.hzh.broken.line.chart.config
 * FileName: MoneyBrokenLineConfig
 * Date: on 2017/12/8  下午5:50
 * Auther: zihe
 * Descirbe: 财富类型
 * Email: hezihao@linghit.com
 */

public class MoneyBrokenLineConfig extends BaseBrokenLineConfig {
    @Override
    public int onGetBottomStressPointIndex() {
        return 3;
    }

    @Override
    public int onGetStressPointColor() {
        return Color.parseColor("#EBC92A");
    }

    @Override
    public int onGetStressPointDistanceBottomLineColor() {
        return Color.parseColor("#EBC92A");
    }

    @Override
    public int onGetNomalPointColor() {
        return Color.parseColor("#FEFFAA");
    }

    @Override
    public int onGetBrokenLineColor() {
        return Color.parseColor("#EBC92A");
    }

    @Override
    public int onGetBottomStressTextColor() {
        return Color.parseColor("#EBC92A");
    }

    @Override
    public int onGetBrokenCloseRegionStartColor() {
        return Color.parseColor("#E0DE3D");
    }

    @Override
    public int onGetBrokenCloseRegionEndColor() {
        return Color.parseColor("#E8EAB6");
    }
}
