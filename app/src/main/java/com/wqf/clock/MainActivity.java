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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitApp();
//        startPlans();
        //测试ClockActivity和Clock中startWork（）方法
        Clock testclock = new Clock(1638436024000L);
        testclock.mould.name="duoduo";
        testclock.mode="WORK";
        testclock.startWork(MainActivity.this,0);

        Clock testclock2 = new Clock(1638457624000L);
        testclock2.mould.name="hanser";
        testclock2.mode="WORK";
        testclock2.startWork(MainActivity.this,11254345);

        //测试plan的setClock（）方法是否正确
//        Mould mould = new Mould();
//        Plan plan = new Plan("学习数学", "这个计划是为了期末数学考试", 40 * 60 * 1000, 20 * 60 * 1000, 1638417600000L, 1638432000000L, mould);
//        try {
//            plan.setClocks();
//        } catch (ClockException e) {
//            e.printStackTrace();
//        }
//        for (Clock clock:plan.clocks
//             ) {
//            String str = TimeUtil.getStringTime(clock.ringTime);
//            Log.d("debug",str);
//        }
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
        for (Clock clock:plan.clocks
             ) {
            clock.startWork(MainActivity.this,1);
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

    private void InitApp(){
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