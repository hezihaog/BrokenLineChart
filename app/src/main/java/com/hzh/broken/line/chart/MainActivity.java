package com.hzh.broken.line.chart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hzh.broken.line.chart.config.LoveBrokenLineConfig;
import com.hzh.broken.line.chart.config.MoneyBrokenLineConfig;
import com.hzh.broken.line.chart.config.TotalBrokenLineConfig;
import com.hzh.broken.line.chart.config.WorkBrokenLineConfig;
import com.hzh.broken.line.chart.inter.IBrokenLineConfig;
import com.hzh.broken.line.chart.widget.BrokenLineChart;

public class MainActivity extends AppCompatActivity {

    private BrokenLineChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        onBindContent();
    }

    private void initView() {
        chart = (BrokenLineChart) findViewById(R.id.chart);
    }

    private void onBindContent() {
        //全部
        IBrokenLineConfig totalType = new TotalBrokenLineConfig() {
            @Override
            public int[] onGetValues() {
                return new int[]{20, 80, 60, 80, 80, 40, 60};
            }
        };
        //工作
        IBrokenLineConfig workType = new WorkBrokenLineConfig() {
            @Override
            public int[] onGetValues() {
                return new int[]{20, 60, 80, 60, 30, 40, 80};
            }
        };
        //财富
        IBrokenLineConfig moneyType = new MoneyBrokenLineConfig() {
            @Override
            public int[] onGetValues() {
                return new int[]{20, 50, 30, 30, 50, 40, 90};
            }
        };
        //情感
        IBrokenLineConfig loveType = new LoveBrokenLineConfig() {
            @Override
            public int[] onGetValues() {
                return new int[]{20, 80, 60, 80, 80, 40, 60};
            }
        };
        //配置参数
        chart.configAndDrawView(loveType);
    }
}
