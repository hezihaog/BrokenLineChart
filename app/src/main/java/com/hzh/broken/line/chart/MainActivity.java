package com.hzh.broken.line.chart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hzh.broken.line.chart.config.TotalBrokenLineConfig;
import com.hzh.broken.line.chart.widget.BrokenLineChart;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BrokenLineChart chart = (BrokenLineChart) findViewById(R.id.chart);
        //配置参数
        chart.configAndDrawView(new TotalBrokenLineConfig() {
            @Override
            public int[] onGetValues() {
                return new int[]{20, 80, 60, 80, 80, 40, 60};
            }
        });
    }
}
