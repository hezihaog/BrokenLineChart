package com.hzh.broken.line.chart;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.hzh.broken.line.chart.config.LoveBrokenLineConfig;
import com.hzh.broken.line.chart.config.MoneyBrokenLineConfig;
import com.hzh.broken.line.chart.config.TotalBrokenLineConfig;
import com.hzh.broken.line.chart.config.WorkBrokenLineConfig;
import com.hzh.broken.line.chart.inter.IBrokenLineConfig;
import com.hzh.broken.line.chart.widget.BrokenLineChart;

public class MainActivity extends AppCompatActivity {
    private BrokenLineChart chart;
    private RadioGroup typeRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        onBindContent();
    }

    private void initView() {
        chart = (BrokenLineChart) findViewById(R.id.chart);
        typeRadioGroup = (RadioGroup) findViewById(R.id.typeRadioGroup);
    }

    private void onBindContent() {
        RadioButton totalButton = (RadioButton) findViewById(R.id.totalButton);
        RadioButton workButton = (RadioButton) findViewById(R.id.workButton);
        RadioButton moneyButton = (RadioButton) findViewById(R.id.moneyButton);
        RadioButton loveButton = (RadioButton) findViewById(R.id.loveButton);
        //为每个Button的背景设置ColorFilter设置对应的按钮颜色
        totalButton.getBackground().setColorFilter(Color.parseColor("#8943C9"), PorterDuff.Mode.SRC_ATOP);
        workButton.getBackground().setColorFilter(Color.parseColor("#2EC9FF"), PorterDuff.Mode.SRC_ATOP);
        moneyButton.getBackground().setColorFilter(Color.parseColor("#FFC816"), PorterDuff.Mode.SRC_ATOP);
        loveButton.getBackground().setColorFilter(Color.parseColor("#FF669B"), PorterDuff.Mode.SRC_ATOP);

        //设置切换按钮监听
        typeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                IBrokenLineConfig config = null;
                int[] scoresArr = new int[]{};
                switch (checkedId) {
                    case R.id.totalButton:
                        //全部
                        scoresArr = new int[]{20, 80, 60, 80, 80, 40, 60};
                        config = new TotalBrokenLineConfig();
                        break;
                    case R.id.workButton:
                        //工作
                        scoresArr = new int[]{30, 60, 80, 60, 30, 40, 80};
                        config = new WorkBrokenLineConfig();
                        break;
                    case R.id.moneyButton:
                        //财富
                        scoresArr = new int[]{10, 30, 50, 60, 20, 40, 90};
                        config = new MoneyBrokenLineConfig();
                        break;
                    case R.id.loveButton:
                        //情感
                        scoresArr = new int[]{40, 30, 60, 80, 70, 50, 60};
                        config = new LoveBrokenLineConfig();
                        break;
                }
                if (config != null) {
                    chart.configAndDrawView(config, scoresArr);
                }
            }
        });
    }
}
