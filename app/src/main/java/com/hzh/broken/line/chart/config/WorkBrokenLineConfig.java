package com.hzh.broken.line.chart.config;

import android.graphics.Color;

/**
 * Package: com.hzh.broken.line.chart.config
 * FileName: WorkBrokenLineConfig
 * Date: on 2017/12/8  下午5:40
 * Auther: zihe
 * Descirbe:工作类型
 * Email: hezihao@linghit.com
 */

public abstract class WorkBrokenLineConfig extends BaseBrokenLineConfig {
    @Override
    public int onGetBottomStressPointIndex() {
        return 2;
    }

    @Override
    public int onGetStressPointColor() {
        return Color.parseColor("#098FDB");
    }

    @Override
    public int onGetStressPointDistanceBottomLineColor() {
        return Color.parseColor("#098FDB");
    }

    @Override
    public int onGetNomalPointColor() {
        return Color.parseColor("#C4F6FF");
    }

    @Override
    public int onGetBrokenLineColor() {
        return Color.parseColor("#098FDB");
    }

    @Override
    public int onGetBottomStressTextColor() {
        return Color.parseColor("#098FDB");
    }

    @Override
    public int onGetBrokenCloseRegionStartColor() {
        return Color.parseColor("#4ABBEA");
    }

    @Override
    public int onGetBrokenCloseRegionEndColor() {
        return Color.parseColor("#B6E8EA");
    }
}
