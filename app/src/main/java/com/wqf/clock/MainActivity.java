package com.wqf.clock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //组件
    ImageView homeImage;
    TextView homeText;
    Button setPlanList;
    Button save;
    Button test;
    //需要用到的全局变量
    protected static MyDBOpenHelper myDBHelper;
    //当前检测到的Plan
    protected static List<Plan> planList = new ArrayList<>();
    //当前检测到的模板
    protected static List<Mould> mouldList = new ArrayList<>();
    //全局变量requestCode
    protected static int requestCode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化app
        InitApp();

    }

    @Override
    protected void onStart() {
        super.onStart();
        //每次页面显示时都重新设置所有计划的闹钟
        startPlans();
    }

    private void startPlans() {
        //先将planlist中的计划按照时间顺序排好
        if (!planList.isEmpty()) {
            Collections.sort(planList);
            for (Plan plan : planList
            ) {
                startPlan(plan);
            }
        }
    }

    private void startPlan(Plan plan) {
        for (Clock clock : plan.clocks
        ) {
            //一定要保证每次设置一个pendingIntent之后requestCode要自增，否则requestCode一致
            //会造成AlarmManager识别后面的PendingIntent为同一个，于是之前的闹钟被覆盖
            clock.startWork(MainActivity.this, requestCode++);
        }
    }

    private void bindView() {
        homeImage = findViewById(R.id.homeImage);
        homeText = findViewById(R.id.homeText);

        setPlanList = findViewById(R.id.setPlanList);
        save = findViewById(R.id.save);
        test = findViewById(R.id.test);

        setPlanList.setOnClickListener(this);
        save.setOnClickListener(this);
        test.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == setPlanList) {
            Intent intent = new Intent(MainActivity.this, PlanListActivity.class);
            startActivity(intent);
        }
        if (v == save) {
            //保存所有配置

        }
        if (v == test) {
            homeText.setText("haole");
        }
    }

    private void InitApp() {
        //绑定组件，并设置监听器
        bindView();
        //第一次启动活动时首先链接好数据库
        myDBHelper = new MyDBOpenHelper(MainActivity.this, "my.db", null, 1);
        //存入两条默认模板
        SQLUtils.saveMould(new Mould("hanser", "nopath", "nopath"));
        SQLUtils.saveMould(new Mould("duoduo", "nopath", "nopath"));
        //然后加载所有的mould
        mouldList = SQLUtils.loadAllMoulds();
        //然后加载所有的plan
        try {
            planList = SQLUtils.loadAllPlans();
        } catch (ClockException e) {
            e.printStackTrace();
        }
    }
}